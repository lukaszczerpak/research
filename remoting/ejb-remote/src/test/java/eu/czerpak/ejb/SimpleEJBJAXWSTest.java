package eu.czerpak.ejb;

import eu.czerpak.service.SimpleRemote;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.log4j.Logger;
import org.perf4j.StopWatch;
import org.perf4j.log4j.Log4JStopWatch;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by IntelliJ IDEA.
 * User: lukes
 * Date: 5/28/11
 * Time: 5:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleEJBJAXWSTest
{
    private SimpleRemote simpleRemote;
    private StopWatch stopWatch;

    @BeforeTest
    public void setUp() throws Exception
    {
        Logger rootLogger = Logger.getRootLogger();
        stopWatch = new Log4JStopWatch("JAXWS - create proxy");
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(SimpleRemote.class);
        factory.setAddress("http://127.0.0.1:8080/SimpleEJB/SimpleEJB");
        simpleRemote = (SimpleRemote) factory.create();
        stopWatch.stop();
    }

    @Test
    public void testSayHello() throws Exception
    {
        for (int i = 0; i < 1000; i++) {
            stopWatch.start("JAXWS - sayHello");
            String s = simpleRemote.sayHello("Lukes");
            stopWatch.stop();
        }
    }

    @Test
    public void testGetList10() throws Exception
    {
        for (int i = 0; i < 10; i++) {
            stopWatch.start("JAXWS - getList(10)");
            simpleRemote.getMyObjectList(10);
            stopWatch.stop();
        }
    }

    @Test
    public void testGetList100() throws Exception
    {
        for (int i = 0; i < 10; i++) {
            stopWatch.start("JAXWS - getList(100)");
            simpleRemote.getMyObjectList(100);
            stopWatch.stop();
        }
    }

    @Test
    public void testGetList500() throws Exception
    {
        for (int i = 0; i < 10; i++) {
            stopWatch.start("JAXWS - getList(500)");
            simpleRemote.getMyObjectList(500);
            stopWatch.stop();
        }
    }

    @Test
    public void testGetList1000() throws Exception
    {
        for (int i = 0; i < 10; i++) {
            stopWatch.start("JAXWS - getList(1000)");
            simpleRemote.getMyObjectList(1000);
            stopWatch.stop();
        }
    }
}
