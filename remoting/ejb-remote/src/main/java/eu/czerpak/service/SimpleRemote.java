package eu.czerpak.service;

import eu.czerpak.model.MyObject;

import javax.ejb.Remote;
import javax.jws.WebService;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: lukes
 * Date: 5/28/11
 * Time: 2:06 PM
 * To change this template use File | Settings | File Templates.
 */
@Remote
@WebService
public interface SimpleRemote
{
    String sayHello(String name);
    List<MyObject> getMyObjectList(int size);
}
