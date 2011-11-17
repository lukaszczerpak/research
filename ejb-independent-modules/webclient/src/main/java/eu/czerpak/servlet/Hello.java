package eu.czerpak.servlet;

import eu.czerpak.ejb.SimpleRemote;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Hello", urlPatterns = "/hello")
public class Hello extends HttpServlet
{
    // @Inject annotation doesn't work, only @EJB
    @EJB
    SimpleRemote simpleRemote;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String name = request.getParameter("name");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.write("<h2>");
        out.write(simpleRemote.sayHello(name));
        out.write("</h2>");
        out.close();
    }
}
