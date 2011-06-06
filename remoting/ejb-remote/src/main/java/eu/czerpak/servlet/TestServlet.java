package eu.czerpak.servlet;


import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by IntelliJ IDEA.
 * User: lukes
 * Date: 6/6/11
 * Time: 8:45 PM
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(urlPatterns = "/test")
public class TestServlet extends HttpServlet
{
    public static final String LOGIN_KEY = "login";
    private int counter = 0;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        PrintWriter out = response.getWriter();
        try {
            String login = request.getParameter("login");
            if (!StringUtils.isBlank(login)) {
                request.getSession().invalidate();
                HttpSession session = request.getSession(true);
                session.setAttribute(LOGIN_KEY, login);
            }
            HttpSession session = request.getSession(true);
            out.println("<h1>" + session.getId() + "</h1>");
            out.println("<h1>" + session.getAttribute(LOGIN_KEY) + "</h1>");
            out.println("<h2>" + counter++ + "</h2>");
        }
        finally {
            out.flush();
            out.close();
        }
    }

}
