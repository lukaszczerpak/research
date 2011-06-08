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
    Instance<UserSession> authBean;

    @Override
    public String sayHello()
    {
        if (authBean == null) {
            return "";
        }

        return "Hello, " + authBean.get().getLogin() + " (" + authBean.get().getSessionId() + ")";
    }
}
