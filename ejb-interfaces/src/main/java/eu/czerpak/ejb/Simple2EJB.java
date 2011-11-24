package eu.czerpak.ejb;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;

@Stateless
@LocalBean
public class Simple2EJB implements Simple2Local
{
    @Override
    public String sayHello(String name)
    {
        return "Hello, " + name;
    }
}
