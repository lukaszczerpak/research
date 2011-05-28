package eu.czerpak.ejb;

import javax.ejb.Stateless;

/**
 * Created by IntelliJ IDEA.
 * User: lukes
 * Date: 5/28/11
 * Time: 2:05 PM
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class SimpleEJB implements SimpleRemote
{
    @Override
    public String sayHello(String name)
    {
        return "Hello, " + name + "!";
    }
}
