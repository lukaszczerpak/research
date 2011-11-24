package eu.czerpak.ejb;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;

@Stateless
@LocalBean
public class Simple4EJB implements Simple4Local, Simple4Remote
{
    @Override
    public String sayHello(String name)
    {
        return "Hello, " + name;
    }
}
