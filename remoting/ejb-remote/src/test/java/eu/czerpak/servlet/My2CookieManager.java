package eu.czerpak.servlet;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * @author lukes
 */
public class My2CookieManager
        extends CookieHandler
{
    static ThreadLocal<CookieManager> threadLocal = new ThreadLocal<CookieManager>()
    {
        @Override
        protected CookieManager initialValue()
        {
            return new CookieManager();
        }
    };

    public static CookieManager getThreadInstance()
    {
        return threadLocal.get();
    }


    @Override
    public Map<String, List<String>> get(URI uri, Map<String, List<String>> stringListMap)
            throws IOException
    {
        return getThreadInstance().get(uri, stringListMap);
    }

    @Override
    public void put(URI uri, Map<String, List<String>> stringListMap)
            throws IOException
    {
        getThreadInstance().put(uri, stringListMap);
    }
}
