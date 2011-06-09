package eu.czerpak.bean;

import eu.czerpak.model.UserSession;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import java.io.Serializable;

/**
 * @author lukes
 */
@SessionScoped
public class LoginBean
        implements Serializable
{
    private UserSession userSession;

    public void login(String login, String password, String sessionId)
    {
        this.userSession = new UserSession(login, sessionId);
    }

    public void logout()
    {
        this.userSession = null;
    }

    public boolean isAuthenticated()
    {
        return userSession != null;
    }

    @Produces
    public UserSession getUserSession()
    {
        return userSession;
    }
}
