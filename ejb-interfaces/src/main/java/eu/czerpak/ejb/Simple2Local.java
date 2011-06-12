package eu.czerpak.ejb;

import javax.ejb.Local;

@Local
public interface Simple2Local
{
    String sayHello(String name);
}
