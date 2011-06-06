package eu.czerpak.servlet;

import com.caucho.hessian.server.HessianServlet;
import com.caucho.services.server.ServiceContext;
import eu.czerpak.ejb.SimpleStatefulRemote;

import javax.ejb.EJB;
import javax.servlet.ServletRequest;
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
    @EJB
    SimpleStatefulRemote simpleStatefulRemote;

    @Override
    public void increment()
    {
        printSessionId();
        simpleStatefulRemote.increment();
    }

    private void printSessionId()
    {
        HttpServletRequest request = (HttpServletRequest) ServiceContext.getContextRequest();
        if (request != null) {
            HttpSession session = request.getSession();

            if(session != null) {
                System.out.println("SESSION: " + session.getId());
            }
            else {
                System.out.println("NULL SESSION OBJECT");
            }
        }
        else {
            System.out.println("NULL REQUEST OBJECT");
        }
    }

    @Override
    public int getValue()
    {
        printSessionId();
        return simpleStatefulRemote.getValue();
    }

    @Override
    public void finish()
    {
        printSessionId();
        simpleStatefulRemote.finish();
    }
}
