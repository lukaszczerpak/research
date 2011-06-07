package eu.czerpak.servlet;

import com.caucho.hessian.client.HessianProxyFactory;
import eu.czerpak.ejb.SimpleStatefulRemote;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import eu.czerpak.hessian.client.*;
import javax.naming.NamingException;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.Properties;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: lukes
 * Date: 6/6/11
 * Time: 7:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleStatefulHessianTest
{
    private SimpleStatefulRemote simpleStatefulRemote;

    @BeforeTest
    public void initConfig() throws IOException, NamingException
    {
        Properties config = new Properties();
        config.load(this.getClass().getResourceAsStream("/test.properties"));

        CookieHandler.setDefault(new CookieManager(null /*=default in-memory store*/, CookiePolicy.ACCEPT_ALL));
        HessianConversationProxyFactory factory = new HessianConversationProxyFactory();

        String url = "http://" + config.getProperty("glassfish.remote.hostname") + ":8080/" + config.getProperty("module.name") + "/SimpleStatefulHessian";
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
        assertEquals(simpleStatefulRemote.getValue(), 1);


//        assertEquals(simpleStatefulRemote.getValue(), 0);
    }
}
