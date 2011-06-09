package eu.czerpak.ejb;

import eu.czerpak.service.SimpleSessionRemote;
import org.apache.commons.lang.StringUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.naming.InitialContext;
import java.util.Properties;

import static org.testng.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: lukes
 * Date: 5/28/11
 * Time: 5:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleSessionEJBTest
{
    private SimpleSessionRemote simpleSessionRemote;

    @BeforeTest
    public void setUp()
            throws Exception
    {
        Properties p = new Properties();
        p.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
        p.setProperty("org.omg.CORBA.ORBInitialHost", "127.0.0.1");
        p.setProperty("org.omg.CORBA.ORBInitialPort", "3700");

        InitialContext ctx = new InitialContext(p);

        simpleSessionRemote = (SimpleSessionRemote) ctx.lookup("java:global/ejb-remote/SimpleSessionEJB");
    }

    @Test
    public void testSayHello()
            throws Exception
    {
        String s = "qwerty";
        String value = simpleSessionRemote.sayHello(s);
        assertTrue(StringUtils.contains(value, s));
    }

}
