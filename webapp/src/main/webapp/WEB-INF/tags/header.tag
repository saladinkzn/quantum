<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag display-name="header" pageEncoding="UTF-8" %>
<%@ attribute name="activePage"%>
<div class="row">
    <ul class="nav pull-right nav-pills">
        <li <c:if test="${activePage == 'working'}">class="active"</c:if>>
            <a href="/">Разработка</a>
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