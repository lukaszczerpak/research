package eu.czerpak.service;

import javax.ejb.Remote;
import javax.ejb.Remove;

/**
 * Created by IntelliJ IDEA.
 * User: lukes
 * Date: 6/6/11
 * Time: 7:17 PM
 * To change this template use File | Settings | File Templates.
 */
@Remote
public interface SimpleStatefulRemote
{
    void increment();

    int getValue();

    @Remove
    void finish();
}
