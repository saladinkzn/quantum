<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="project" type="ru.kpfu.quantum.spring.entities.Project"--%>


<div id="project-area" class="project container">


    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">${project.name}</h3>
        </div>
        <div class="panel-body">
            <p>Дата создания: ${project.created}</p>
            <p>Дата последнего изменения: ${project.lastModified}</p>
            <c:forEach var="function" items="${project.functions}">
            <div class="panel panel-default">
                <div class="panel-body">
                    ${function.code}
                </div>
            </div>

                <div class="panel panel-default">
                    <div class="panel-body">
                        <img src="/media/${function.imageUrl}"/>
                    </div>
                </div>
                <p><a download class="btn btn-primary btn-lg" href="/media/${function.imageUrl}">Скачать</a></p>
            </c:forEach>


            <div class="well"><p>${project.description}</p></div>


        </div>
    </div>


</div>

