package eu.czerpak.service;

/**
 * @author lukes
 */
public interface LogonRemote
{
    void login(String login, String password);

    void logout();
}
