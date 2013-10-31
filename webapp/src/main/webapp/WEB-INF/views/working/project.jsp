<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--@elvariable id="project" type="ru.kpfu.quantum.spring.entities.Project"--%>

<div id="project-area" class="my container" data-calculated="${project.calculated}">
    <div class="row">
        <button id="view-button" class="btn btn-primary pull-right">
            <img class="icon" src="/resources/images/gtk-refresh.png"/>
        </button>
        <button id="add-function" class="btn btn-primary pull-right">
            Добавить функцию
        </button>
    </div>
    <div class="row" id="functions-container">
        <c:forEach var="function" items="${project.functions}">
            <textarea spellcheck="false" class="form-control code-area js-function">
                ${function.code}
            </textarea>
        </c:forEach>
    </div>
    <div class="my container off">
        <jsp:include page="/WEB-INF/views/editor.jsp"/>
    </div>
    <div class="result">
        <c:if test="${project.calculated}">
            <c:forEach var="function" items="${project.functions}">
            <a download class="btn btn-primary" href="/media/${function.imageUrl}">Скачать</a>
            <img class="result" src="/media/${function.imageUrl}"/>
            </c:forEach>
        </c:if>
    </div>
</div>

