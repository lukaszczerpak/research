package eu.czerpak.remoting.webclient;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.Properties;

public class StaticConfig
{
    static {
        CookieHandler.setDefault(new CookieManager(null /*=default in-memory store*/, CookiePolicy.ACCEPT_ALL));

        Properties cfg = new Properties();
        try {
            cfg.load(StaticConfig.class.getResourceAsStream("/webclient.properties"));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        urlSimpleSessionHessian = "http://" + cfg.getProperty("glassfish.remote.hostname") + ":8080/" + cfg.getProperty("module.name") + "/SimpleSessionHessian";
        urlAuthHessian = "http://" + cfg.getProperty("glassfish.remote.hostname") + ":8080/" + cfg.getProperty("module.name") + "/AuthHessian";
    }

    private static String urlSimpleSessionHessian;
    private static String urlAuthHessian;

    public static String getUrlSimpleSessionHessian()
    {
        return urlSimpleSessionHessian;
    }

    public static String getUrlAuthHessian()
    {
        return urlAuthHessian;
    }
}
