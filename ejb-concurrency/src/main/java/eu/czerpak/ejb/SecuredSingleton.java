package eu.czerpak.ejb;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;

@Singleton
@DeclareRoles({"User"})
public class SecuredSingleton
{
    @Resource
    private SessionContext sessionContext;

    /**
     * @return
     */
    @RolesAllowed({"User"})
    public String publicSecuredInternal()
    {
        return "SECURED";
    }

    @RolesAllowed({"User"})
    protected String protectedSecuredInternal()
    {
        return "SECURED";
    }

    @RolesAllowed({"User"})
    private String privateSecuredInternal()
    {
        return "SECURED";
    }

    @RolesAllowed({"User"})
    String packageSecuredInternal()
    {
        return "SECURED";
    }

    // PUBLIC

    /**
     * @return
     */
    public String publicSecured()
    {
        return publicSecuredInternal();
    }

    /**
     * @return
     */
    public String publicSecuredWithReference()
    {
        SecuredSingleton businessObject = sessionContext.getBusinessObject(SecuredSingleton.class);
        return businessObject.publicSecuredInternal();
    }

    /**
     * @return
     */
    @RolesAllowed({"User"})
    public String publicSecuredByAnnotation()
    {
        return publicSecuredInternal();
    }

    // PROTECTED

    /**
     * @return
     */
    public String protectedSecured()
    {
        return protectedSecuredInternal();
    }

    /**
     * @return
     */
    public String protectedSecuredWithReference()
    {
        SecuredSingleton businessObject = sessionContext.getBusinessObject(SecuredSingleton.class);
        return businessObject.protectedSecuredInternal();
    }

    /**
     * @return
     */
    @RolesAllowed({"User"})
    public String protectedSecuredByAnnotation()
    {
        return protectedSecuredInternal();
    }

    // PRIVATE

    /**
     * @return
     */
    public String privateSecured()
    {
        return privateSecuredInternal();
    }

    /**
     * @return
     */
    public String privateSecuredWithReference()
    {
        SecuredSingleton businessObject = sessionContext.getBusinessObject(SecuredSingleton.class);
        return businessObject.privateSecuredInternal();
    }

    /**
     * @return
     */
    @RolesAllowed({"User"})
    public String privateSecuredByAnnotation()
    {
        return privateSecuredInternal();
    }

    // PACKAGE

    /**
     * @return
     */
    public String packageSecured()
    {
        return packageSecuredInternal();
    }

    /**
     * @return
     */
    public String packageSecuredWithReference()
    {
        SecuredSingleton businessObject = sessionContext.getBusinessObject(SecuredSingleton.class);
        return businessObject.packageSecuredInternal();
    }

    /**
     * @return
     */
    @RolesAllowed({"User"})
    public String packageSecuredByAnnotation()
    {
        return packageSecuredInternal();
    }

}
