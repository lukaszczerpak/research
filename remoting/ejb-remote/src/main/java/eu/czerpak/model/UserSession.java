package eu.czerpak.model;

import java.io.Serializable;

/**
 * @author lukes
 */
public class UserSession
        implements Serializable
{
    private String login;
    private String password;
    private String sessionId;

    public UserSession(String login, String password, String sessionId)
    {
        this.login = login;
        this.password = password;
        this.sessionId = sessionId;
    }

    public String getLogin()
    {
        return login;
    }

    public String getPassword()
    {
        return password;
    }

    public String getSessionId()
    {
        return sessionId;
    }
}
