package eu.czerpak.servlet;

import com.caucho.hessian.server.HessianServlet;
import eu.czerpak.bean.AuthBean;
import eu.czerpak.service.Authorization;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

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

    private static AtomicInteger instanceCount = new AtomicInteger(0);

    @PostConstruct
    public void onNewSession(){
        System.out.println("On new session(" + instanceCount + "): " + new Date());
        instanceCount.incrementAndGet();
    }

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
