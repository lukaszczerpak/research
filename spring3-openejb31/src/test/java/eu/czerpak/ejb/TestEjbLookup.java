package eu.czerpak.ejb;

import junit.framework.TestCase;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestEjbLookup extends TestCase
{
    public void test() throws Exception
    {
        org.apache.log4j.BasicConfigurator.configure();
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        SimpleEjb simpleEjb = (SimpleEjb) context.getBean("SimpleEjb");
        assertNotNull(simpleEjb);
    }
}