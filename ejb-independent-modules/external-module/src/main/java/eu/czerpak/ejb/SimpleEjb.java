package eu.czerpak.ejb;

import javax.ejb.Stateless;

@Stateless
public class SimpleEjb implements SimpleRemote
{
    @Override
    public String sayHello(String name)
    {
        return "Hello, " + name + "!";
    }
}
