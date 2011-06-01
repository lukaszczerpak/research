package eu.czerpak.service;

/**
 * Created by IntelliJ IDEA.
 * User: lukes
 * Date: 6/1/11
 * Time: 10:43 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Authorization
{
    void auth(String login, String password);

    String getLogin();

    boolean isAuthenticated();
}
