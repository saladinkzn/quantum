<%--
  Created by IntelliJ IDEA.
  User: sala
  Date: 20.09.13
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

    <form action="/test/send" method="post">
        <label for="receiver">Enter e-mail</label>
        <input id="receiver" name="receiver" type="email">
        <input type="submit">
    </form>
</body>
</html>