package eu.czerpak.hessian.server;

import com.caucho.hessian.server.HessianServlet;
import com.caucho.services.server.ServiceContext;
import org.apache.commons.lang.StringUtils;

import javax.ejb.EJBException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author lukes
 */
public class SfsbHessianServlet
        extends HessianServlet
{
    // TODO remove MODULE_NAME or do something with that
    private static final String MODULE_NAME = "ejb-remote-1.0";

    protected <T> T getReference(Class<T> c)
    {
        HttpServletRequest request = (HttpServletRequest) ServiceContext.getContextRequest();
        HttpSession session = request.getSession();
        String cid = request.getParameter("cid");

        // check cid
        if (StringUtils.isBlank(cid)) {
            throw new IllegalArgumentException("Unknown conversationId");
        }

        // unique SFSB instance key
        String sfsbKey = c.getSimpleName() + "-" + cid;
        System.out.println(sfsbKey);

        // trying to retrieve instance related with cid
        Object o = session.getAttribute(sfsbKey);

        if (o == null) {
            try {
                InitialContext ic = new InitialContext();
//                o = ic.lookup("java:global/" + MODULE_NAME + "/" + c.getName());
                o = ic.lookup(c.getName());
                session.setAttribute(sfsbKey, o);
            }
            catch (NamingException e) {
                throw new EJBException(e);
            }
        }
        return (T) o;
    }

    protected void removeReference(Class c)
    {
        HttpServletRequest request = (HttpServletRequest) ServiceContext.getContextRequest();
        HttpSession session = request.getSession();
        String cid = request.getParameter("cid");

        // check cid
        if (StringUtils.isBlank(cid)) {
            throw new IllegalArgumentException("Unknown conversationId");
        }

        // unique SFSB instance key
        String sfsbKey = c.getSimpleName() + "-" + cid;

        session.removeAttribute(sfsbKey);
    }
}
