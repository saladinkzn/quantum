<%@ page import="ru.kpfu.quantum.spring.entities.Project" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="project-area" class="project container">


    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">${project.name}</h3>
        </div>
        <div class="panel-body">
            <p>Created date: ${project.created}</p>
            <p>Date of last modified: ${project.lastModified}</p>
            <div class="panel panel-default">
                <div class="panel-body">
                    ${project.code}
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-body">
                    <img src="/resources/images/test.jpg"/>
                </div>
            </div>

            <div class="well"><p>${project.description}</p></div>

        </div>
    </div>


</div>

