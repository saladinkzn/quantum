<%@ page import="ru.kpfu.quantum.spring.entities.ProjectGroup" %>
<%@ page import="ru.kpfu.quantum.spring.entities.Project" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="data-list">
    <c:forEach var="record" items="${records}">
        <li><a recordId="${record.id}" href="#">${record.name}</a></li>
    </c:forEach>
</div>