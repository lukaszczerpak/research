package eu.czerpak.ejb;

import eu.czerpak.service.SimpleStatefulRemote;

import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 * Created by IntelliJ IDEA.
 * User: lukes
 * Date: 6/6/11
 * Time: 7:14 PM
 * To change this template use File | Settings | File Templates.
 */
@Stateful
public class SimpleStatefulEJB implements SimpleStatefulRemote
{
    private int value = 0;

    @Override
    public void increment()
    {
        this.value++;
    }

    @Override
    public int getValue()
    {
        return this.value;
    }

    @Override
    @Remove
    public void finish()
    {
        this.value = -1;
    }
}
