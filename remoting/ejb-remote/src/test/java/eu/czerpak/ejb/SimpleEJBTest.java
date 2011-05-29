package eu.czerpak.ejb;

import eu.czerpak.service.SimpleRemote;
import org.apache.log4j.Logger;
import org.perf4j.StopWatch;
import org.perf4j.log4j.Log4JStopWatch;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.naming.InitialContext;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: lukes
 * Date: 5/28/11
 * Time: 5:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleEJBTest
{
    private SimpleRemote simpleRemote;
    private StopWatch stopWatch;

    @BeforeTest
    public void setUp() throws Exception
    {
        Logger rootLogger = Logger.getRootLogger();
        Properties config = new Properties();
        config.load(this.getClass().getResourceAsStream("/test.properties"));

        Properties p = new Properties();
        p.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
//        p.setProperty("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
//        p.setProperty("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
        p.setProperty("org.omg.CORBA.ORBInitialHost", config.getProperty("glassfish.remote.hostname"));
        p.setProperty("org.omg.CORBA.ORBInitialPort", "3700");

        stopWatch = new Log4JStopWatch("EJB - initialContext");
        InitialContext ctx = new InitialContext(p);
        stopWatch.stop();

        stopWatch.start("EJB - SimpleEJB lookup");
        simpleRemote = (SimpleRemote) ctx.lookup("java:global/" + config.getProperty("module.name") + "/SimpleEJB");
        stopWatch.stop();
    }

    @Test
    public void testSayHello() throws Exception
    {
        for (int i = 0; i < 1000; i++) {
            stopWatch.start("EJB - sayHello");
            String s = simpleRemote.sayHello("Lukes");
            stopWatch.stop();
        }
    }

    @Test
    public void testGetList10() throws Exception
    {
        for (int i = 0; i < 10; i++) {
            stopWatch.start("EJB - getList(10)");
            simpleRemote.getMyObjectList(10);
            stopWatch.stop();
        }
    }

    @Test
    public void testGetList100() throws Exception
    {
        for (int i = 0; i < 10; i++) {
            stopWatch.start("EJB - getList(100)");
            simpleRemote.getMyObjectList(100);
            stopWatch.stop();
        }
    }

    @Test
    public void testGetList500() throws Exception
    {
        for (int i = 0; i < 10; i++) {
            stopWatch.start("EJB - getList(500)");
            simpleRemote.getMyObjectList(500);
            stopWatch.stop();
        }
    }

    @Test
    public void testGetList1000() throws Exception
    {
        for (int i = 0; i < 10; i++) {
            stopWatch.start("EJB - getList(1000)");
            simpleRemote.getMyObjectList(1000);
            stopWatch.stop();
        }
    }
}
