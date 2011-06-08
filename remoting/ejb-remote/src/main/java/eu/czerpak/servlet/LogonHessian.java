package eu.czerpak.servlet;

import com.caucho.hessian.server.HessianServlet;
import com.caucho.services.server.ServiceContext;
import eu.czerpak.bean.LoginBean;
import eu.czerpak.service.LogonRemote;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/LogonHessian")
public class LogonHessian
        extends HessianServlet
        implements LogonRemote
{
    @Inject
    Instance<LoginBean> loginBean;

    @Override
    public void login(String login, String password)
    {
        LoginBean bean = loginBean.get();
        if (bean.isAuthenticated()) {
            throw new IllegalStateException("PROBA PODWOJNEGO LOGOWANIA");
        }

        HttpServletRequest request = (HttpServletRequest) ServiceContext.getContextRequest();
        HttpSession session = request.getSession();

        bean.login(login, password, session.getId());
    }

    @Override
    public void logout()
    {
        loginBean.get().logout();
    }
}
