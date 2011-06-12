package eu.czerpak.ejb;

import javax.ejb.Stateless;

@Stateless
public class Simple3EJB implements Simple3Remote
{
    @Override
    public String sayHello(String name)
    {
        return "Hello, " + name;
    }
}
