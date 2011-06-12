package eu.czerpak.ejb;

import com.googlecode.jeeunit.JeeunitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

import static org.junit.Assert.assertNotNull;

@RunWith(JeeunitRunner.class)
public class InjectEJB1Test
{
    @EJB
    Simple1EJB simple1EJB;

    @EJB
    Simple2Local simple2Local;
//    Simple2EJB simple2EJB;    // illegal

    @EJB
    Simple3Remote simple3Remote;
//    Simple3EJB simple3EJB;    // illegal

    @EJB
    Simple4Local simple4Local;

    @EJB
    Simple4Remote simple4Remote;
//    Simple4EJB simple4EJB;    // illegal


    @Test
    public void testSayHello() throws Exception
    {
        assertNotNull(simple1EJB.sayHello("abc"));
        assertNotNull(simple2Local.sayHello("abc"));
        assertNotNull(simple3Remote.sayHello("abc"));
        assertNotNull(simple4Local.sayHello("abc"));
        assertNotNull(simple4Remote.sayHello("abc"));
    }
}
