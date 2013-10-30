<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"%>
<%--@elvariable id="functions" type="java.util.List<ru.kpfu.quantum.spring.entities.Function>"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <div class="result">
        <c:forEach var="function" items="${functions}">
        <a download class="btn btn-primary" href="/media/${function.imageUrl}">Скачать</a>
        <img class="result" src="/media/${function.imageUrl}"/>
        </c:forEach>
    </div>

