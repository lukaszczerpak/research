package eu.czerpak.ejb;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import static junit.framework.Assert.assertEquals;

public class LockedSingletonTest
{
    private static EJBContainer container;

    @BeforeClass
    public static void setUpClass() throws Exception
    {
        container = EJBContainer.createEJBContainer();
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
        container.close();
    }

    private LockedSingleton getLockedSingleton() throws NamingException
    {
        InitialContext ctx = new InitialContext();
        return (LockedSingleton) ctx.lookup("java:global/classes/LockedSingleton");
    }

    @Test(threadPoolSize = 10, invocationCount = 100)
    public void incrementTest() throws InterruptedException, NamingException
    {
        System.out.println(Thread.currentThread().getName() + ": incrementTest");
        assertEquals(1, getLockedSingleton().increment());
    }

    @Test(threadPoolSize = 10, invocationCount = 100)
    public void incrementWithReferenceTest() throws InterruptedException, NamingException
    {
        System.out.println(Thread.currentThread().getName() + ": incrementWithReferenceTest");
        assertEquals(1, getLockedSingleton().incrementWithReference());
    }

    @Test(threadPoolSize = 10, invocationCount = 100)
    public void incrementWithAnnotationTest() throws InterruptedException, NamingException
    {
        System.out.println(Thread.currentThread().getName() + ": incrementWithAnnotationTest");
        assertEquals(1, getLockedSingleton().incrementWithAnnotation());
    }
}
