package eu.czerpak.ejb;

import javax.ejb.Stateless;

@Stateless
public class SimpleEjb
{
    public String sayHello(String name)
    {
        return (name == null ? "Hello, Anonymous!" : "Hello, " + name + "!");
    }
}
