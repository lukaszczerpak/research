package eu.czerpak.servlet;

import com.caucho.hessian.server.HessianServlet;
import eu.czerpak.model.MyObject;
import eu.czerpak.service.SimpleRemote;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: lukes
 * Date: 5/29/11
 * Time: 8:37 PM
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(urlPatterns = "/SimpleHessian")
public class SimpleHessian extends HessianServlet implements SimpleRemote
{
    @EJB
    SimpleRemote simpleRemote;

    @Override
    public String sayHello(String name)
    {
        return simpleRemote.sayHello(name);
    }

    @Override
    public List<MyObject> getMyObjectList(int size)
    {
        return simpleRemote.getMyObjectList(size);
    }
}
