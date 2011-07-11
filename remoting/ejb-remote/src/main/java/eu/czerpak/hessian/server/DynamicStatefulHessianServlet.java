package eu.czerpak.hessian.server;

import com.caucho.services.server.ServiceContext;
import org.apache.commons.lang.StringUtils;

import javax.ejb.EJBException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author lukes
 */
@WebServlet(urlPatterns = "/dynamic-stateful/*")
public class DynamicStatefulHessianServlet
        extends DynamicHessianServlet
{
    protected Object getReference(String serviceId)
    {
        HttpServletRequest request = (HttpServletRequest) ServiceContext.getContextRequest();
        HttpSession session = request.getSession();
        String cid = request.getParameter("cid");

        // check cid
        if (StringUtils.isBlank(cid)) {
            throw new IllegalArgumentException("Unknown conversationId");
        }

        // unique SFSB instance key
        String sfsbKey = serviceId + "-" + cid;
        System.out.println(sfsbKey);

        // trying to retrieve instance related with cid
        Object o = session.getAttribute(sfsbKey);

        if (o == null) {
            try {
                InitialContext ic = new InitialContext();
                o = ic.lookup(serviceId);
                session.setAttribute(sfsbKey, o);
            }
            catch (NamingException e) {
                throw new EJBException(e);
            }
        }
        return o;
    }

    protected void removeReference(String serviceId)
    {
        HttpServletRequest request = (HttpServletRequest) ServiceContext.getContextRequest();
        HttpSession session = request.getSession();
        String cid = request.getParameter("cid");

        // check cid
        if (StringUtils.isBlank(cid)) {
            throw new IllegalArgumentException("Unknown conversationId");
        }

        // unique SFSB instance key
        String sfsbKey = serviceId + "-" + cid;

        session.removeAttribute(sfsbKey);
    }
}
