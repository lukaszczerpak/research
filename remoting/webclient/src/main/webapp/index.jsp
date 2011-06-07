<%@ page import="eu.czerpak.service.Authorization" %>
<%@ page import="com.caucho.hessian.client.HessianProxyFactory" %>
<%@ page import="eu.czerpak.remoting.webclient.StaticConfig" %>
<%@ page import="eu.czerpak.service.SimpleSessionRemote" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<html>
<body>
<h2>Hello World!</h2>
<%
    HessianProxyFactory factory = new HessianProxyFactory();
    String login = request.getParameter("login");

    out.print("SESSION: " + request.getSession().getId() + "<br>");

    if(!StringUtils.isBlank(login)) {
        session.invalidate();

        String urlAuthHessian = StaticConfig.getUrlAuthHessian();

        out.print("BEFORE LOGIN<br>");
        Authorization authorization = (Authorization) factory.create(Authorization.class, urlAuthHessian);

        out.print("TRYING TO LOGON: " + login + "<br>");
        authorization.auth(login, login);
    }

    String urlSimpleSessionHessian = StaticConfig.getUrlSimpleSessionHessian();
    out.print("BEFORE SAYHELLO<br>");
    SimpleSessionRemote simpleSessionRemote = (SimpleSessionRemote) factory.create(SimpleSessionRemote.class, urlSimpleSessionHessian);

    out.print("'" + simpleSessionRemote.sayHello() + "'<br>");

    out.flush();
%>
</body>
</html>
