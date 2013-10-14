<%@ page import="ru.kpfu.quantum.spring.entities.Project" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="project-area" class="my container">
    <button id="view-button" class="btn btn-primary pull-right"></button>
    <textarea calculated="${project.calculated}" class="form-control code-area">${project.code}</textarea>
    <div class="my container off"></div>
    <div class="result <c:if test="${!project.calculated}"> off </c:if>">
        <a download class="btn btn-primary" href="/resources/images/test.jpg">Скачать</a>
        <img class="result" src="/resources/images/test.jpg"/>
    </div>
</div>

