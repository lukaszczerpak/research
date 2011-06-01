package eu.czerpak.bean;

import eu.czerpak.service.Authorization;

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
public class AuthBean implements Authorization, Serializable
{
    private String login;
    private boolean authenticated;

    @Override
    public void auth(String login, String password)
    {
        this.login = login;
        this.authenticated = true;
    }

    @Override
    public String getLogin()
    {
        return login;
    }

    @Override
    public boolean isAuthenticated()
    {
        return authenticated;
    }
}
