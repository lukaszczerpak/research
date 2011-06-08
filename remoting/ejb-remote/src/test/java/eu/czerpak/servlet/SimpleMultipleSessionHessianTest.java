package eu.czerpak.servlet;

import com.caucho.hessian.client.HessianProxyFactory;
import eu.czerpak.service.LogonRemote;
import eu.czerpak.service.SimpleSessionRemote;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.naming.NamingException;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URI;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

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

    final ThreadLocal<CookieManager> threadLocal = new ThreadLocal<CookieManager>()
    {
        @Override
        protected CookieManager initialValue()
        {
            return new CookieManager(null, CookiePolicy.ACCEPT_ALL);
        }
    };
    CookieHandler cookieHandler = new CookieHandler()
    {
        CookieManager getCookieManager()
        {
            return threadLocal.get();
        }

        @Override
        public Map<String, List<String>> get(URI uri, Map<String, List<String>> stringListMap)
                throws IOException
        {
            return getCookieManager().get(uri, stringListMap);
        }

        @Override
        public void put(URI uri, Map<String, List<String>> stringListMap)
                throws IOException
        {
            getCookieManager().put(uri, stringListMap);
        }
    };
    private HessianProxyFactory factory;


    @BeforeTest
    public void initConfig()
            throws IOException, NamingException
    {
        urlSimpleSessionHessian = "http://127.0.0.1:8080/ejb-remote/SimpleSessionHessian";
        urlAuthHessian = "http://127.0.0.1:8080/ejb-remote/LogonHessian";

//        CookieHandler.setDefault(new CookieManager(null /*=default in-memory store*/, CookiePolicy.ACCEPT_ALL));
//        CookieHandler.setDefault(new MTCookieManager());
        CookieHandler.setDefault(cookieHandler);
        factory = new HessianProxyFactory();
        System.out.println("INIT");
    }

    @Test(threadPoolSize = 100, invocationCount = 100)
    public void testMultipleSessions()
            throws Exception
    {
        SimpleSessionRemote simpleRemote = (SimpleSessionRemote) factory.create(SimpleSessionRemote.class, urlSimpleSessionHessian);
        LogonRemote authorization = (LogonRemote) factory.create(LogonRemote.class, urlAuthHessian);

        Thread.sleep((int) (Math.random() * 5000));

        String threadName = Thread.currentThread().getName();
        authorization.login(threadName, "password");

        Thread.sleep((int) (Math.random() * 5000));

        String value = simpleRemote.sayHello();
        System.out.println("THREAD NAME: " + threadName + "   VALUE: " + value);
        assertNotNull(value);
        assertTrue(value.contains(threadName));

        Thread.sleep((int) (Math.random() * 5000));
    }
}
