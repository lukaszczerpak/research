package eu.czerpak.service;

import javax.ejb.Remote;

/**
 * Created by IntelliJ IDEA.
 * User: lukes
 * Date: 5/30/11
 * Time: 10:24 PM
 * To change this template use File | Settings | File Templates.
 */
@Remote
public interface SimpleSessionRemote
{
    String sayHello();

    String sayHello(String name);
}
