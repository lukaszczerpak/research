package eu.czerpak.ejb;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;

@Singleton
@Lock(LockType.READ)
public class LockedSingleton
{
    private int value;
    @Resource
    private SessionContext sessionContext;

    @PostConstruct
    void postConstruct()
    {
        value = 0;
    }

    /**
     * @return
     * @throws InterruptedException
     */
    @Lock(LockType.WRITE)
    int incrementInternal() throws InterruptedException
    {
        int curValue = value;
        Thread.sleep(new Double(Math.random() * 1000).intValue());
        return (++value - curValue);
    }

    /**
     * @return
     * @throws InterruptedException
     */
    public int increment() throws InterruptedException
    {
        return incrementInternal();
    }

    /**
     * @return
     * @throws InterruptedException
     */
    public int incrementWithReference() throws InterruptedException
    {
        LockedSingleton businessObject = sessionContext.getBusinessObject(LockedSingleton.class);
        return businessObject.incrementInternal();
    }

    /**
     * @return
     * @throws InterruptedException
     */
    @Lock(LockType.WRITE)
    public int incrementWithAnnotation() throws InterruptedException
    {
        return incrementInternal();
    }
}