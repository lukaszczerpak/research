package eu.czerpak.ejb;

import javax.ejb.Remote;

@Remote
public interface Simple3Remote
{
    String sayHello(String name);
}
