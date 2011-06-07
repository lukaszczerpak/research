package eu.czerpak.servlet;

import com.caucho.hessian.server.HessianServlet;
import com.caucho.services.server.ServiceContext;
import eu.czerpak.bean.AuthBean;
import eu.czerpak.service.Authorization;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by IntelliJ IDEA.
 * User: lukes
 * Date: 5/29/11
 * Time: 8:37 PM
 * To change this template use File | Settings | File Templates.
 *
 */
public class AuthHessian
        extends HessianServlet
        implements Authorization
{
    @Inject
    AuthBean authBean;
//    BeanManager beanManager;


//    private AuthBean getAuthBean()
//    {
//        Bean<?> bean = beanManager.getBeans(AuthBean.class).iterator().next();
//        CreationalContext ctx = beanManager.createCreationalContext(bean);
//        return (AuthBean) beanManager.getReference(bean, AuthBean.class, ctx);
//    }

    @Override
    public void auth(String login, String password)
    {
//        AuthBean authBean = getAuthBean();
//
        if (authBean.isAuthenticated()) {
            throw new IllegalStateException("PROBA PODWOJNEGO LOGOWANIA");
        }

        HttpServletRequest request = (HttpServletRequest) ServiceContext.getContextRequest();
        HttpSession session = request.getSession();

//        session.invalidate();
//        session = request.getSession(true);

//        AuthBean authBean = getAuthBean();
        authBean.auth(login, password, session.getId());
    }

    @Override
    public String getLogin()
    {
//        AuthBean authBean = getAuthBean();
        return authBean.getLogin();
    }

    @Override
    public boolean isAuthenticated()
    {
//        AuthBean authBean = getAuthBean();
        return authBean.isAuthenticated();
    }

    @Override
    public String getSessionId()
    {
        return authBean.getSessionId();
    }
}
