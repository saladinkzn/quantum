<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
    <ul class="nav pull-right nav-pills">
        <li>
            <a href="/working">Разработка</a>
        </li>
        <li>
            <a href="#">Архив</a>
        </li>
        <li>
            <a href="#">Справка</a>
        </li>
        <c:if test="${user != null}">
        <li>
            <a href="/logout">Выход</a>
        </li>
        </c:if>
    </ul>
</div>