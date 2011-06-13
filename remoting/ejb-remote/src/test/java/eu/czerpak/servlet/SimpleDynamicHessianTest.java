package eu.czerpak.servlet;

import com.caucho.hessian.client.HessianProxyFactory;
import eu.czerpak.service.SimpleRemote;
import org.apache.log4j.Logger;
import org.perf4j.StopWatch;
import org.perf4j.log4j.Log4JStopWatch;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertNotNull;

public class SimpleDynamicHessianTest
{
    private SimpleRemote simpleRemote;
    private StopWatch stopWatch;

    @BeforeTest
    public void initProxy() throws IOException
    {
        Logger rootLogger = Logger.getRootLogger();
        stopWatch = new Log4JStopWatch("DynamicHessian - create proxy");
        String url = "http://127.0.0.1:8080/ejb-remote/dynamic/eu.czerpak.service.SimpleRemote";
        HessianProxyFactory factory = new HessianProxyFactory();
        simpleRemote = (SimpleRemote) factory.create(SimpleRemote.class, url);
        stopWatch.stop();
        assertNotNull(simpleRemote);
    }

    @Test
    public void testSayHello()
    {
        for (int i = 0; i < 1000; i++) {
            stopWatch.start("DynamicHessian - sayHello");
            String s = simpleRemote.sayHello("Lukes");
            stopWatch.stop();
        }
    }

    @Test
    public void testGetList10() throws Exception
    {
        for (int i = 0; i < 10; i++) {
            stopWatch.start("DynamicHessian - getList(10)");
            simpleRemote.getMyObjectList(10);
            stopWatch.stop();
        }
    }

    @Test
    public void testGetList100() throws Exception
    {
        for (int i = 0; i < 10; i++) {
            stopWatch.start("DynamicHessian - getList(100)");
            simpleRemote.getMyObjectList(100);
            stopWatch.stop();
        }
    }

    @Test
    public void testGetList500() throws Exception
    {
        for (int i = 0; i < 10; i++) {
            stopWatch.start("DynamicHessian - getList(500)");
            simpleRemote.getMyObjectList(500);
            stopWatch.stop();
        }
    }

    @Test
    public void testGetList1000() throws Exception
    {
        for (int i = 0; i < 10; i++) {
            stopWatch.start("DynamicHessian - getList(1000)");
            simpleRemote.getMyObjectList(1000);
            stopWatch.stop();
        }
    }
}
