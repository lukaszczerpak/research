package eu.czerpak.hessian.server;

import eu.czerpak.hessian.client.HessianConversationProxyFactory;
import eu.czerpak.service.SimpleStatefulRemote;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.naming.NamingException;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: lukes
 * Date: 6/6/11
 * Time: 7:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleDynamicStatefulHessianTest
{
    private SimpleStatefulRemote simpleStatefulRemote;

    @BeforeTest
    public void initConfig() throws IOException, NamingException
    {
        CookieHandler.setDefault(new CookieManager(null /*=default in-memory store*/, CookiePolicy.ACCEPT_ALL));
        HessianConversationProxyFactory factory = new HessianConversationProxyFactory();

        String url = "http://127.0.0.1:8080/ejb-remote/dynamic-stateful/eu.czerpak.service.SimpleStatefulRemote";
        simpleStatefulRemote = (SimpleStatefulRemote) factory.create(SimpleStatefulRemote.class, url);
        assertNotNull(simpleStatefulRemote);
    }

    @Test(invocationCount = 2)
    public void testStatefulHessian() throws Exception
    {
        assertEquals(simpleStatefulRemote.getValue(), 0);
        simpleStatefulRemote.increment();
        assertEquals(simpleStatefulRemote.getValue(), 1);
        simpleStatefulRemote.finish();

        assertEquals(simpleStatefulRemote.getValue(), 0);
        simpleStatefulRemote.finish();
    }
}
