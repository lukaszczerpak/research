package eu.czerpak.servlet;

import com.caucho.hessian.server.HessianServlet;
import eu.czerpak.service.Authorization;
import eu.czerpak.service.SimpleSessionRemote;

import javax.ejb.EJB;
import javax.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: lukes
 * Date: 5/29/11
 * Time: 8:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class AuthHessian extends HessianServlet implements Authorization
{
    @Inject
    Authorization authorization;

    @Override
    public void auth(String login, String password)
    {
        authorization.auth(login, password);
    }

    @Override
    public String getLogin()
    {
        return authorization.getLogin();
    }

    @Override
    public boolean isAuthenticated()
    {
        return authorization.isAuthenticated();
    }
}
