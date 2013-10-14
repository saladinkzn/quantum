<%@ page import="ru.kpfu.quantum.spring.entities.ProjectGroup" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="groups" type="java.util.List<ru.kpfu.quantum.spring.entities.ProjectGroup>"--%>

<!DOCTYPE html>

<html>

<head>
    <title>New Page</title>
    <meta name="viewport" content="width=device-width">
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="/resources/css/bootstrap-theme.min.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" href="/resources/css/working.css">
    <script src="/resources/js/jquery-1.10.2.min.js" type="text/javascript"></script>
    <script src="/resources/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="/resources/js/working.js" type="text/javascript"></script>
</head>

<body>
<div class="container">
    <ul class="nav pull-right nav-pills">
        <li class="active">
            <a href="#">Разработка</a>
        </li>
        <li>
            <a href="#">Архив</a>
        </li>
        <li>
            <a href="#">Справка</a>
        </li>
    </ul>
</div>
<div id='working-area' class="container working-area">
    <div class="my container">
    <ul class="pull-left list-inline">
        <li id="group-list">
            <div class="btn-group">
                <button groupId="" type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    Groups <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" role="menu">
                    <li><input type="text" placeholder="Новый проект"/></li>
                    <li class="divider"></li>
                    <div class="data-list">
                        <c:forEach var="record" items="${groups}">
                            <li><a recordId="${record.id}" href="#">${record.name}</a></li>
                        </c:forEach>
                    </div>
                </ul>
            </div>
        </li>
        <li id="project-list">
            <div class="btn-group">
                <button disabled projectId="" type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    Projects <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" role="menu">
                    <li><input type="text" placeholder="Новый проект"/></li>
                    <li class="divider"></li>
                    <div class="data-list">
                    </div>
                </ul>
            </div>
        </li>
        <li id="filter-list">
            <div class="btn-group">
                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" value="All">
                    Filters <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" role="menu">
                    <li class="divider"></li>
                    <div class="data-list">
                        <c:forEach var="filter" items="${filters}">
                            <li><a value="${filter}" href="#">${filter}</a></li>
                        </c:forEach>
                    </div>
                </ul>
            </div>
        </li>
    </ul>
    <ul class="pull-right list-inline">
        <li>
            <button class="btn btn-primary">Настройка режима отображения</button>
        </li>
        <li>
            <button id="save-button" disabled class="btn btn-primary">Сохранить</button>
        </li>
        <li>
            <button id='calculate-button' disabled class="btn btn-primary">Рассчитать</button>
        </li>
        <li>
            <button id="archive-button" disabled class="btn btn-primary">В архив</button>
        </li>
    </ul>
    </div>
    <div id="project-area" class="my container">
        <button id="view-button" class="btn btn-primary pull-right"></button>
        <textarea class="form-control code-area" disabled></textarea>
        <div class="my container off"></div>
        <div class="result off">
        </div>
    </div>
</div>
</body>

</html>