package eu.czerpak.servlet;

import com.caucho.hessian.client.HessianProxyFactory;
import eu.czerpak.service.SimpleRemote;
import org.apache.log4j.Logger;
import org.perf4j.StopWatch;
import org.perf4j.log4j.Log4JStopWatch;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Properties;

import static org.testng.Assert.assertNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: lukes
 * Date: 5/29/11
 * Time: 8:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleHessianTest
{
    private SimpleRemote simpleRemote;
    private StopWatch stopWatch;

    @BeforeTest
    public void initProxy() throws IOException
    {
        Logger rootLogger = Logger.getRootLogger();
        Properties config = new Properties();
        config.load(this.getClass().getResourceAsStream("/test.properties"));

        stopWatch = new Log4JStopWatch("Hessian - create proxy");
        String url = "http://" + config.getProperty("glassfish.remote.hostname") + ":8080/" + config.getProperty("module.name") + "/SimpleHessian";
        HessianProxyFactory factory = new HessianProxyFactory();
        simpleRemote = (SimpleRemote) factory.create(SimpleRemote.class, url);
        stopWatch.stop();
        assertNotNull(simpleRemote);
    }

    @Test
    public void testSayHello()
    {
        for (int i = 0; i < 1000; i++) {
            stopWatch.start("Hessian - sayHello");
            String s = simpleRemote.sayHello("Lukes");
            stopWatch.stop();
        }
    }

    @Test
    public void testGetList10() throws Exception
    {
        for (int i = 0; i < 10; i++) {
            stopWatch.start("Hessian - getList(10)");
            simpleRemote.getMyObjectList(10);
            stopWatch.stop();
        }
    }

    @Test
    public void testGetList100() throws Exception
    {
        for (int i = 0; i < 10; i++) {
            stopWatch.start("Hessian - getList(100)");
            simpleRemote.getMyObjectList(100);
            stopWatch.stop();
        }
    }

    @Test
    public void testGetList500() throws Exception
    {
        for (int i = 0; i < 10; i++) {
            stopWatch.start("Hessian - getList(500)");
            simpleRemote.getMyObjectList(500);
            stopWatch.stop();
        }
    }

    @Test
    public void testGetList1000() throws Exception
    {
        for (int i = 0; i < 10; i++) {
            stopWatch.start("Hessian - getList(1000)");
            simpleRemote.getMyObjectList(1000);
            stopWatch.stop();
        }
    }
}
