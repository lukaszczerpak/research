package eu.czerpak.ejb;

import eu.czerpak.model.UserSession;
import eu.czerpak.service.SimpleSessionRemote;

import javax.ejb.Stateless;
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
    UserSession authBean;

    @Override
    public String sayHello()
    {
        if (authBean == null) {
            return "";
        }

        return "Hello, " + authBean.getLogin() + " (" + authBean.getSessionId() + ")";
    }
}
