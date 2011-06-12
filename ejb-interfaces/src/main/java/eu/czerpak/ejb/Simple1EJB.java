package eu.czerpak.ejb;

import javax.ejb.Stateless;

@Stateless
public class Simple1EJB
{
    public String sayHello(String name)
    {
        return "Hello, " + name;
    }
}
