package eu.czerpak.hessian.server;

import com.caucho.hessian.server.HessianServlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author lukes
 */
public class ConversationHessianServlet
    extends HessianServlet
{
    private String conversationId;

    @Override
    public void service(ServletRequest request, ServletResponse response)
            throws IOException, ServletException
    {
        conversationId = request.getParameter("conversationId");
        super.service(request, response);
    }

    public String getConversationId()
    {
        return conversationId;
    }
}
