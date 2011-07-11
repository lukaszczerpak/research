package eu.czerpak.hessian.server;

import com.caucho.hessian.server.HessianSkeleton;

import javax.ejb.Remove;
import java.lang.reflect.Method;

public class ExtendedHessianSkeleton extends HessianSkeleton
{
    private Method method;

    public ExtendedHessianSkeleton(Object service, Class<?> apiClass)
    {
        super(service, apiClass);
    }

    public ExtendedHessianSkeleton(Class<?> apiClass)
    {
        super(apiClass);
    }

    @Override
    protected Method getMethod(String mangledName)
    {
        method = super.getMethod(mangledName);
        return method;
    }

    protected boolean isRemoved()
    {
        return method != null && method.isAnnotationPresent(Remove.class);
    }
}
