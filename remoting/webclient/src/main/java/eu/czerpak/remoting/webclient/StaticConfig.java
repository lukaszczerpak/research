package eu.czerpak.remoting.webclient;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.Properties;

public class StaticConfig
{
    static {
//        CookieHandler.setDefault(new CookieManager(null /*=default in-memory store*/, CookiePolicy.ACCEPT_ALL));
        CookieHandler.setDefault(new My2CookieManager());

        Properties cfg = new Properties();
        try {
            cfg.load(StaticConfig.class.getResourceAsStream("/webclient.properties"));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("http://").append(cfg.getProperty("glassfish.remote.hostname")).append(":8080/");
        sb.append(cfg.getProperty("module.name")).append("-").append(cfg.getProperty("module.version"));
        sb.append("/SimpleSessionHessian");
        urlSimpleSessionHessian = sb.toString();

        sb = new StringBuilder();
        sb.append("http://").append(cfg.getProperty("glassfish.remote.hostname")).append(":8080/");
        sb.append(cfg.getProperty("module.name")).append("-").append(cfg.getProperty("module.version"));
        sb.append("/AuthHessian");
        urlAuthHessian = sb.toString();
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
