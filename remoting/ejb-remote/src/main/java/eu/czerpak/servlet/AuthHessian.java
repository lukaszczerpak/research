package eu.czerpak.servlet;

import com.caucho.hessian.server.HessianServlet;
import eu.czerpak.bean.AuthBean;
import eu.czerpak.service.Authorization;

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
    AuthBean authBean;

    @Override
    public void auth(String login, String password)
    {
        authBean.auth(login, password);
    }

    @Override
    public String getLogin()
    {
        return authBean.getLogin();
    }

    @Override
    public boolean isAuthenticated()
    {
        return authBean.isAuthenticated();
    }
}
