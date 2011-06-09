package eu.czerpak.ejb;

import eu.czerpak.model.UserSession;
import eu.czerpak.service.SimpleSessionRemote;

import javax.ejb.Stateless;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: lukes
 * Date: 5/30/11
 * Time: 10:19 PM
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class SimpleSessionEJB
        implements SimpleSessionRemote
{
    @Inject
    private Instance<UserSession> userSessionSource;

    @Override
    public String sayHello()
    {
        UserSession userSession = userSessionSource.get();

        if (userSession == null) {
            return "";
        }

        return "Hello, " + userSession.getLogin() + " (" + userSession.getSessionId() + ")";
    }

    @Override
    public String sayHello(String name)
    {
        return "Hello, " + name;
    }

}
