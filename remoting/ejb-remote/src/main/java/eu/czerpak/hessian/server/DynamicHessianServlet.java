package eu.czerpak.hessian.server;

import com.caucho.hessian.io.SerializerFactory;
import com.caucho.hessian.server.HessianServlet;
import com.caucho.hessian.server.HessianSkeleton;
import com.caucho.services.server.ServiceContext;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.logging.Logger;

/**
 * @author lukes
 */
public abstract class DynamicHessianServlet
        extends HttpServlet
{
    private Logger _log = Logger.getLogger(HessianServlet.class.getName());
    private boolean _isDebug;
    private HessianSkeleton _homeSkeleton;
    private SerializerFactory _serializerFactory;

    public DynamicHessianServlet()
    {
    }

    public String getServletInfo()
    {
        return "Dynamic Hessian Servlet";
    }

    /**
     * Gets the serializer factory.
     */
    public SerializerFactory getSerializerFactory()
    {
        if (_serializerFactory == null) {
            _serializerFactory = new SerializerFactory();
        }

        return _serializerFactory;
    }

    /**
     * Sets the serializer factory
     */
    public void setSerializerFactory(SerializerFactory serializerFactory)
    {
        this._serializerFactory = serializerFactory;
    }

    /**
     * Sets the serializer send collection java type.
     */
    public void setSendCollectionType(boolean sendType)
    {
        getSerializerFactory().setSendCollectionType(sendType);
    }

    /**
     * Sets the debugging flag.
     */
    public void setDebug(boolean isDebug)
    {
        _isDebug = isDebug;
    }

    /**
     * Sets the debugging log name.
     */
    public void setLogName(String name)
    {
        _log = Logger.getLogger(name);
    }

    private Class loadClass(String className)
            throws ClassNotFoundException
    {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        if (loader != null) {
            return Class.forName(className, false, loader);
        }
        else {
            return Class.forName(className);
        }
    }

    /**
     * Execute a request.  The path-info of the request selects the bean.
     * Once the bean's selected, it will be applied.
     */
    public void service(ServletRequest request, ServletResponse response)
            throws IOException, ServletException
    {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if (!req.getMethod().equals("POST")) {
            res.sendError(500, "Hessian Requires POST");
            PrintWriter out = res.getWriter();
            res.setContentType("text/html");
            out.println("<h1>Hessian Requires POST</h1>");
            return;
        }

        String serviceId = req.getPathInfo().substring(1);
        String objectId = req.getParameter("id");
        if (objectId == null) {
            objectId = req.getParameter("ejbid");
        }

        //dumpDebug(req, serviceId, objectId, request);

        ServiceContext.begin(req, res, serviceId, objectId);

        try {
            InputStream is = request.getInputStream();
            OutputStream os = response.getOutputStream();

            response.setContentType("x-application/hessian");
            SerializerFactory serializerFactory = getSerializerFactory();
            Class clazz = loadClass(serviceId);
            Object service = getReference(serviceId);
            _homeSkeleton = new ExtendedHessianSkeleton(service, clazz);
            _homeSkeleton.invoke(is, os, serializerFactory);
            if (((ExtendedHessianSkeleton) _homeSkeleton).isRemoved()) {
                removeReference(serviceId);
            }
        }
        catch (RuntimeException e) {
            if (((ExtendedHessianSkeleton) _homeSkeleton).isRemoved()) {
                removeReference(serviceId);
            }
            throw e;
        }
        catch (ServletException e) {
            throw e;
        }
        catch (Throwable e) {
            throw new ServletException(e);
        }
        finally {
            ServiceContext.end();
        }
    }

    protected abstract Object getReference(String serviceId)
            throws NamingException;

    protected abstract void removeReference(String serviceId);

    private void dumpDebug(HttpServletRequest req, String serviceId, String objectId, ServletRequest request)
    {
        System.out.println("Context path: " + req.getContextPath());
        System.out.println("ServiceID: " + serviceId + " ObjectID: " + objectId);
        System.out.println("Cookies: " + req.getCookies());
        System.out.println("QueryString: " + req.getQueryString());
        System.out.println("RequestSessionId: " + req.getRequestedSessionId());
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String[] values = request.getParameterValues(name);
            System.out.println("Parameter: " + name + " = {");
            for (String value : values) {
                System.out.println(value);
            }
            System.out.println("}");
        }
        names = req.getHeaderNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String value = req.getHeader(name);
            System.out.println("Header: " + name + " = " + value);
        }
        names = req.getAttributeNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            Object value = req.getAttribute(name);
            System.out.println("Attribute: " + name + " = " + value);
        }
    }
}
