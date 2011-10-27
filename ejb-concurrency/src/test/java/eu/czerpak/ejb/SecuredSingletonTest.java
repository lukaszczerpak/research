package eu.czerpak.ejb;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.ejb.EJBAccessException;
import javax.ejb.EJBException;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class SecuredSingletonTest
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

    private SecuredSingleton getSimpleSingleton() throws NamingException
    {
        InitialContext ctx = new InitialContext();
        return (SecuredSingleton) ctx.lookup("java:global/classes/SecuredSingleton");
    }

    // PUBLIC

    @Test
    public void publicSecuredTest() throws NamingException
    {
        System.out.println(getSimpleSingleton().publicSecured());
    }

    @Test(expectedExceptions = EJBAccessException.class)
    public void publicSecuredWithReferenceTest() throws NamingException
    {
        System.out.println(getSimpleSingleton().publicSecuredWithReference());
    }

    @Test(expectedExceptions = EJBAccessException.class)
    public void publicSecuredByAnnotationTest() throws NamingException
    {
        System.out.println(getSimpleSingleton().publicSecuredByAnnotation());
    }

    // PACKAGE

    @Test
    public void packageSecuredTest() throws NamingException
    {
        System.out.println(getSimpleSingleton().packageSecured());
    }

    // !!!
    @Test
    public void packageSecuredWithReferenceTest() throws NamingException
    {
        System.out.println(getSimpleSingleton().packageSecuredWithReference());
    }

    @Test(expectedExceptions = EJBAccessException.class)
    public void packageSecuredByAnnotationTest() throws NamingException
    {
        System.out.println(getSimpleSingleton().packageSecuredByAnnotation());
    }

    // PROTECTED

    @Test
    public void protectedSecuredTest() throws NamingException
    {
        System.out.println(getSimpleSingleton().protectedSecured());
    }

    // !!!
    @Test(expectedExceptions = EJBException.class)
    public void protectedSecuredWithReferenceTest() throws NamingException
    {
        System.out.println(getSimpleSingleton().protectedSecuredWithReference());
    }

    @Test(expectedExceptions = EJBAccessException.class)
    public void protectedSecuredByAnnotationTest() throws NamingException
    {
        System.out.println(getSimpleSingleton().protectedSecuredByAnnotation());
    }

    // PRIVATE

    @Test
    public void privateSecuredTest() throws NamingException
    {
        System.out.println(getSimpleSingleton().privateSecured());
    }

    // !!!
    @Test
    public void privateSecuredWithReferenceTest() throws NamingException
    {
        System.out.println(getSimpleSingleton().privateSecuredWithReference());
    }

    @Test(expectedExceptions = EJBAccessException.class)
    public void privateSecuredByAnnotationTest() throws NamingException
    {
        System.out.println(getSimpleSingleton().privateSecuredByAnnotation());
    }
}
