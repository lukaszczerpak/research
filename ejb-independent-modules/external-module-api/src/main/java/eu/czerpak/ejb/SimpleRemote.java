package eu.czerpak.ejb;

import javax.ejb.Remote;

@Remote
public interface SimpleRemote
{
    String sayHello(String name);
}
