<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag display-name="header" pageEncoding="UTF-8" %>
<%@ attribute name="activePage"%>
<div class="row">
    <ul class="nav pull-right nav-pills">
        <li <c:if test="${activePage == 'working'}">class="active"</c:if>>
            <c:choose>
                <c:when test="${activePage == 'working'}">
                    <a>Разработка</a>
                </c:when>
                <c:otherwise>
                    <a href="/">Разработка</a>
                </c:otherwise>
            </c:choose>
        </li>
        <li <c:if test="${activePage == 'archive'}">class="active"</c:if>>
            <c:choose>
                <c:when test="${activePage == 'archive'}">
                    <a>Архив</a>
                </c:when>
                <c:otherwise>
                    <a href="/archive">Архив</a>
                </c:otherwise>
            </c:choose>
        </li>
        <li <c:if test="${activePage == 'help'}">class="active"</c:if>>
            <c:choose>
                <c:when test="${activePage == 'help'}">
                    <a>Справка</a>
                </c:when>
                <c:otherwise>
                    <a href="/help">Справка</a>
                </c:otherwise>
            </c:choose>
        </li>
        <c:if test="${user != null}">
            <li>
                <a href="/logout">Выход</a>
            </li>
        </c:if>
    </ul>
</div>