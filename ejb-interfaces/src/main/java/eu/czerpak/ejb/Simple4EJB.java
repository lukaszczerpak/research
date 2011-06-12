package eu.czerpak.ejb;

import javax.ejb.Stateless;

@Stateless
public class Simple4EJB implements Simple4Local, Simple4Remote
{
    @Override
    public String sayHello(String name)
    {
        return "Hello, " + name;
    }
}
