package eu.czerpak.model;

import java.io.Serializable;

/**
 * @author lukes
 */
public class UserSession
        implements Serializable
{
    private String login;
    private String sessionId;

    public UserSession(String login, String sessionId)
    {
        this.login = login;
        this.sessionId = sessionId;
    }

    public String getLogin()
    {
        return login;
    }

    public String getSessionId()
    {
        return sessionId;
    }
}
