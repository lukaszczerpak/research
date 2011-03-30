package eu.czerpak.vaadin.servlet;

import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.AbstractApplicationServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SpringApplicationServlet extends AbstractApplicationServlet {

    private WebApplicationContext applicationContext;
    private Class<? extends Application> applicationClass;
    private String applicationBean;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        applicationBean = servletConfig.getInitParameter("applicationBean");
        if (applicationBean == null) {
            throw new ServletException(
                    "ApplicationBean not specified in servlet parameters");
        }

        applicationContext = WebApplicationContextUtils.getWebApplicationContext(
                servletConfig.getServletContext());

        applicationClass = (Class<? extends Application>) applicationContext.getType(applicationBean);
    }

    @Override
    protected Class<? extends Application> getApplicationClass() throws
            ClassNotFoundException {
        return applicationClass;
    }

    @Override
    protected Application getNewApplication(HttpServletRequest request) {
        return (Application) applicationContext.getBean(applicationBean);
    }
}
