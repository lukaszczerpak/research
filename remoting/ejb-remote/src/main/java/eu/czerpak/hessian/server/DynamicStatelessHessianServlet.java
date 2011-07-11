package eu.czerpak.hessian.server;

import eu.czerpak.hessian.server.DynamicHessianServlet;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;

/**
 * @author lukes
 */
@WebServlet(urlPatterns = "/dynamic/*")
public class DynamicStatelessHessianServlet
        extends DynamicHessianServlet
{
    @Override
    protected Object getReference(String serviceId)
            throws NamingException
    {
        InitialContext ctx = new InitialContext();
        return ctx.lookup(serviceId);
    }

    @Override
    protected void removeReference(String serviceId)
    {
    }
}
