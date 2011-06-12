package eu.czerpak.ejb;

import javax.ejb.Remote;

@Remote
public interface Simple4Remote
{
    String sayHello(String name);
}
