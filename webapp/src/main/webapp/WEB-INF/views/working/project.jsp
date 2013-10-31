<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--@elvariable id="project" type="ru.kpfu.quantum.spring.entities.Project"--%>

<div id="project-area" class="my" data-calculated="${project.calculated}">
    <div id="functions-container">
    <c:forEach var="function" items="${project.functions}">
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12">
                <textarea spellcheck="false" class="form-control code-area js-function">${function.code}</textarea>
            </div>
        </div>
    </c:forEach>
    </div>
    <div class="my container off">
        <jsp:include page="/WEB-INF/views/editor.jsp"/>
    </div>
    <div class="result">
        <c:if test="${project.calculated}">
            <c:forEach var="function" items="${project.functions}">
                <div class="col-lg-4 col-md-6 cols-sm-12">
                    <div class="thumbnail">
                        <img class="result" src="/media/${function.imageUrl}"/>
                        <div class="caption">
                            <p>
                                <a download class="btn btn-primary" href="/media/${function.imageUrl}">Скачать</a>
                            </p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:if>
    </div>
</div>

