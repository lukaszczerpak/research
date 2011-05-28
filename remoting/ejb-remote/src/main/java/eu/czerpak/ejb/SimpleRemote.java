package eu.czerpak.ejb;

import javax.ejb.Remote;

/**
 * Created by IntelliJ IDEA.
 * User: lukes
 * Date: 5/28/11
 * Time: 2:06 PM
 * To change this template use File | Settings | File Templates.
 */
@Remote
public interface SimpleRemote
{
    String sayHello(String name);
}
