package eu.czerpak.servlet;

import com.caucho.hessian.server.HessianServlet;
import eu.czerpak.service.SimpleSessionRemote;

import javax.ejb.EJB;

/**
 * Created by IntelliJ IDEA.
 * User: lukes
 * Date: 5/29/11
 * Time: 8:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleSessionHessian extends HessianServlet implements SimpleSessionRemote
{
    @EJB
    SimpleSessionRemote simpleSessionRemote;

    @Override
    public String sayHello()
    {
        return simpleSessionRemote.sayHello();
    }
}
