<%@ page import="eu.czerpak.service.Authorization" %>
<%@ page import="com.caucho.hessian.client.HessianProxyFactory" %>
<%@ page import="eu.czerpak.remoting.webclient.StaticConfig" %>
<%@ page import="eu.czerpak.service.SimpleSessionRemote" %>
<html>
<body>
<h2>Hello World!</h2>
<%
    String login = request.getParameter("login");

    String urlAuthHessian = StaticConfig.getUrlAuthHessian();
    String urlSimpleSessionHessian = StaticConfig.getUrlSimpleSessionHessian();
    HessianProxyFactory factory = new HessianProxyFactory();

    out.print("SESSION: " + request.getSession().getId());
    out.print("BEFORE LOGIN<br>");
    Authorization authorization = (Authorization) factory.create(Authorization.class, urlAuthHessian);

    out.print("TRYING TO LOGON: " + login + "<br>");
    authorization.auth(login, login);

    out.print("BEFORE SAYHELLO<br>");
    SimpleSessionRemote simpleSessionRemote = (SimpleSessionRemote) factory.create(SimpleSessionRemote.class, urlSimpleSessionHessian);

    out.print("'" + simpleSessionRemote.sayHello() + "'<br>");

    out.flush();
%>
</body>
</html>
