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

    @EJB
    Simple2EJB simple2EJB;    // illegal without @LocalBean annotation

    @EJB
    Simple3Remote simple3Remote;

    @EJB
    Simple3EJB simple3EJB;    // illegal without @LocalBean annotation

    @EJB
    Simple4Local simple4Local;

    @EJB
    Simple4Remote simple4Remote;

    @EJB
    Simple4EJB simple4EJB;    // illegal without @LocalBean annotation


    @Test
    public void testSayHello() throws Exception
    {
        assertNotNull(simple1EJB.sayHello("abc"));
        assertNotNull(simple2Local.sayHello("abc"));
        assertNotNull(simple2EJB.sayHello("abc"));
        assertNotNull(simple3Remote.sayHello("abc"));
        assertNotNull(simple3EJB.sayHello("abc"));
        assertNotNull(simple4Local.sayHello("abc"));
        assertNotNull(simple4Remote.sayHello("abc"));
        assertNotNull(simple4EJB.sayHello("abc"));
    }
}
