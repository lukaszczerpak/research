package eu.czerpak.servlet;

import com.caucho.hessian.server.HessianServlet;
import com.caucho.services.server.ServiceContext;
import eu.czerpak.ejb.SimpleStatefulEJB;
import eu.czerpak.ejb.SimpleStatefulRemote;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by IntelliJ IDEA.
 * User: lukes
 * Date: 5/29/11
 * Time: 8:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleStatefulHessian extends HessianServlet implements SimpleStatefulRemote
{
    @Override
    public void increment()
    {
        printSessionId();
        getEJB().increment();
    }

    @Override
    public int getValue()
    {
        printSessionId();
        return getEJB().getValue();
    }

    @Override
    public void finish()
    {
        printSessionId();
        getEJB().finish();
        getSession().removeAttribute(SimpleStatefulRemote.class.getName());
    }

    private SimpleStatefulRemote getEJB()
    {


        HttpSession session = getSession();
        Object o = session.getAttribute(SimpleStatefulRemote.class.getName());
        if (o == null) {
            try {
                InitialContext ic = new InitialContext();
                o = ic.lookup("java:global/ejb-remote-1.0/" + SimpleStatefulEJB.class.getSimpleName());
                session.setAttribute(SimpleStatefulRemote.class.getName(), o);
            }
            catch (NamingException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return (SimpleStatefulRemote) o;
    }

    private HttpSession getSession()
    {
        HttpServletRequest request = (HttpServletRequest) ServiceContext.getContextRequest();
        return request.getSession();
    }

    private void printSessionId()
    {
        HttpSession session = getSession();

        if (session != null) {
            System.out.println("SESSION: " + session.getId());
        }
        else {
            System.out.println("NO SESSION OBJECT");
        }
    }
}
