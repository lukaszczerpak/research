package eu.czerpak.servlet;

import com.caucho.hessian.client.HessianProxyFactory;
import eu.czerpak.ejb.SimpleStatefulRemote;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.naming.NamingException;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.MalformedURLException;
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
    private String url;

    @BeforeTest
    public void initConfig() throws IOException, NamingException
    {
        Properties config = new Properties();
        config.load(this.getClass().getResourceAsStream("/test.properties"));

        url = "http://" + config.getProperty("glassfish.remote.hostname") + ":8080/" + config.getProperty("module.name") + "/SimpleStatefulHessian";
    }

    @BeforeGroups({"withSession"})
    public void initProxyWithSession() throws MalformedURLException
    {
        CookieHandler.setDefault(new CookieManager(null /*=default in-memory store*/, CookiePolicy.ACCEPT_ALL));
        HessianProxyFactory factory = new HessianProxyFactory();

        simpleStatefulRemote = (SimpleStatefulRemote) factory.create(SimpleStatefulRemote.class, url);
        assertNotNull(simpleStatefulRemote);
    }

    @Test(groups = {"withSession"})
    public void testAuthWithSession() throws Exception
    {
        assertEquals(simpleStatefulRemote.getValue(), 0);
        simpleStatefulRemote.increment();
        assertEquals(simpleStatefulRemote.getValue(), 1);
        simpleStatefulRemote.finish();
//        assertEquals(simpleStatefulRemote.getValue(), 0);
    }
}