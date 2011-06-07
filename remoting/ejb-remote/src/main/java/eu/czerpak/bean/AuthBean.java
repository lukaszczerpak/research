package eu.czerpak.bean;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: lukes
 * Date: 5/30/11
 * Time: 10:14 PM
 * To change this template use File | Settings | File Templates.
 */
@SessionScoped
public class AuthBean implements Serializable
{
    private String login;
    private String sessionId;
    private boolean authenticated;

    public void auth(String login, String password, String sessionId)
    {
        this.login = login;
        this.sessionId = sessionId;
        this.authenticated = true;
    }

    public String getLogin()
    {
        return login;
    }

    public boolean isAuthenticated()
    {
        return authenticated;
    }

    public String getSessionId()
    {
        return sessionId;
    }
}
