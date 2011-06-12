package eu.czerpak.ejb;

import javax.ejb.Local;

@Local
public interface Simple4Local
{
    String sayHello(String name);
}
