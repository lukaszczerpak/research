package eu.czerpak.servlet;

import com.caucho.hessian.server.HessianServlet;
import eu.czerpak.service.SimpleSessionRemote;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

/**
 * Created by IntelliJ IDEA.
 * User: lukes
 * Date: 5/29/11
 * Time: 8:37 PM
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(urlPatterns = "/SimpleSessionHessian")
public class SimpleSessionHessian
        extends HessianServlet
        implements SimpleSessionRemote
{
    @EJB
    SimpleSessionRemote simpleSessionRemote;

    @Override
    public String sayHello()
    {
        return simpleSessionRemote.sayHello();
    }

    @Override
    public String sayHello(String name)
    {
        return simpleSessionRemote.sayHello(name);
    }
}
