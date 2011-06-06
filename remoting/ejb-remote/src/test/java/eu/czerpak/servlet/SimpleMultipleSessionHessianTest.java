package eu.czerpak.servlet;

import com.caucho.hessian.client.HessianProxyFactory;
import eu.czerpak.service.Authorization;
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
public class SimpleMultipleSessionHessianTest
{
    private String urlSimpleSessionHessian;
    private String urlAuthHessian;
//    private HessianProxyFactory factory;

    @BeforeTest
    public void initConfig() throws IOException, NamingException
    {
        Properties config = new Properties();
        config.load(this.getClass().getResourceAsStream("/test.properties"));

        urlSimpleSessionHessian = "http://" + config.getProperty("glassfish.remote.hostname") + ":8080/" + config.getProperty("module.name") + "/SimpleSessionHessian";
        urlAuthHessian = "http://" + config.getProperty("glassfish.remote.hostname") + ":8080/" + config.getProperty("module.name") + "/AuthHessian";

        CookieHandler.setDefault(new CookieManager(null /*=default in-memory store*/, CookiePolicy.ACCEPT_ALL));
//        factory = new HessianProxyFactory();
        System.out.println("INIT");
    }

    @Test(threadPoolSize = 100, invocationCount = 200)
    public void testMultipleSessions() throws Exception
    {
        HessianProxyFactory factory = new HessianProxyFactory();
        SimpleSessionRemote simpleRemote = (SimpleSessionRemote) factory.create(SimpleSessionRemote.class, urlSimpleSessionHessian);
        Authorization authorization = (Authorization) factory.create(Authorization.class, urlAuthHessian);

        String threadName = Thread.currentThread().getName();
        authorization.auth(threadName, "dupa");

        String value = simpleRemote.sayHello();
        System.out.println("THREAD NAME: " + threadName + "   VALUE: " + value);
        assertNotNull(value);
        assertTrue(value.contains(threadName));
    }
}
