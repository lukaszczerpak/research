package eu.czerpak.hessian.client;

import com.caucho.hessian.client.HessianProxyFactory;
import org.apache.commons.lang.StringUtils;

import java.net.MalformedURLException;
import java.util.UUID;

/**
 * @author lukes
 */
public class HessianConversationProxyFactory
        extends HessianProxyFactory
{
    private String appendUUID(String url)
    {
        return url + (StringUtils.contains(url, "?") ? "&cid=" : "?cid=") + UUID.randomUUID().toString();
    }

    @Override
    public Object create(Class<?> api, String urlName, ClassLoader loader)
            throws MalformedURLException
    {
        return super.create(api, appendUUID(urlName), loader);
    }
}
