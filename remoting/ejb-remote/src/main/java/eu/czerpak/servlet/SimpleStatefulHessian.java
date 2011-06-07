package eu.czerpak.servlet;

import eu.czerpak.ejb.SimpleStatefulEJB;
import eu.czerpak.ejb.SimpleStatefulRemote;
import eu.czerpak.hessian.server.SfsbHessianServlet;

/**
 * Created by IntelliJ IDEA.
 * User: lukes
 * Date: 5/29/11
 * Time: 8:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleStatefulHessian
        extends SfsbHessianServlet
        implements SimpleStatefulRemote
{
    @Override
    public void increment()
    {
        getReference(SimpleStatefulRemote.class).increment();
    }

    @Override
    public int getValue()
    {
        return getReference(SimpleStatefulRemote.class).getValue();
    }

    @Override
    public void finish()
    {
        getReference(SimpleStatefulRemote.class).finish();
        removeReference(SimpleStatefulRemote.class);
    }
}
