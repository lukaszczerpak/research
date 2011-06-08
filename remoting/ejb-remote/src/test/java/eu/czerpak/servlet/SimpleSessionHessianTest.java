package eu.czerpak.servlet;

import com.caucho.hessian.client.HessianProxyFactory;
import eu.czerpak.service.LogonRemote;
import eu.czerpak.service.SimpleSessionRemote;
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

import static org.testng.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: lukes
 * Date: 6/1/11
 * Time: 10:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleSessionHessianTest
{
    private SimpleSessionRemote simpleRemote;
    private LogonRemote authorization;
    private String urlSimpleSessionHessian;
    private String urlAuthHessian;

    @BeforeTest
    public void initConfig()
            throws IOException, NamingException
    {
        Properties config = new Properties();
        config.load(this.getClass().getResourceAsStream("/test.properties"));

        urlSimpleSessionHessian = "http://" + config.getProperty("glassfish.remote.hostname") + ":8080/" + config.getProperty("module.name") + "/SimpleSessionHessian";
        urlAuthHessian = "http://" + config.getProperty("glassfish.remote.hostname") + ":8080/" + config.getProperty("module.name") + "/LogonHessian";
    }

    @BeforeGroups({"withSession"})
    public void initProxyWithSession()
            throws MalformedURLException
    {
        CookieHandler.setDefault(new CookieManager(null /*=default in-memory store*/, CookiePolicy.ACCEPT_ALL));
        HessianProxyFactory factory = new HessianProxyFactory();

        simpleRemote = (SimpleSessionRemote) factory.create(SimpleSessionRemote.class, urlSimpleSessionHessian);
        assertNotNull(simpleRemote);

        authorization = (LogonRemote) factory.create(LogonRemote.class, urlAuthHessian);
        assertNotNull(authorization);
    }

    @BeforeGroups({"withoutSession"})
    public void initProxyWithoutSession()
            throws MalformedURLException
    {
        CookieHandler.setDefault(null);
        HessianProxyFactory factory = new HessianProxyFactory();

        simpleRemote = (SimpleSessionRemote) factory.create(SimpleSessionRemote.class, urlSimpleSessionHessian);
        assertNotNull(simpleRemote);

        authorization = (LogonRemote) factory.create(LogonRemote.class, urlAuthHessian);
        assertNotNull(authorization);
    }

    @Test(groups = {"withoutSession"})
    public void testSayHello()
            throws Exception
    {
        String value = simpleRemote.sayHello();
        assertNotNull(value);
        assertEquals(value, "");
    }

    @Test(groups = {"withoutSession"})
    public void testAuthWithoutSession()
            throws Exception
    {
        authorization.login("lukes", "dupa");

        String value = simpleRemote.sayHello();
        assertNotNull(value);
        assertEquals(value, "");
    }

    @Test(groups = {"withSession"})
    public void testAuthWithSession()
            throws Exception
    {
        authorization.login("lukes", "dupa");

        String value = simpleRemote.sayHello();
        assertNotNull(value);
        assertNotSame(value, "");
    }
}
