<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags" %>
<%--@elvariable id="groups" type="java.util.List<ru.kpfu.quantum.spring.entities.ProjectGroup>"--%>


<common:mainTemplate activePage="working">

    <jsp:attribute name="title">
        Разработка
    </jsp:attribute>
    <jsp:attribute name="customHead">
        <link rel="stylesheet" href="/resources/css/working.css">
        <script src="/resources/js/working.js" type="text/javascript"></script>
    </jsp:attribute>
    <jsp:attribute name="body">
    <div id='working-area' class="container working-area">
        <div class="row">
            <div class="col-md-6 col-sm-6 col-lg-6">
                <ul class="list-inline">
                    <li id="group-list">
                        <div class="btn-group">
                            <button groupId="" type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                Groups <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" role="menu">
                                <li><input type="text" placeholder="Новая группа"/></li>
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
                                <div class="data-list"></div>
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
            </div>
            <div class="col-md-6 col-sm-6 col-lg-6">
                <ul class="pull-right list-inline">
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
        </div>
        <div class="row off" id="toolPanel">
            <div class="col-lg-offset-8 col-md-offset-8 col-sm-offset-8 col-lg-4 col-sm-4 col-md-4 pull-right">
                <ul class="pull-right list-inline">
                    <li>
                        <div class="btn-group">
                            <button id="add-function" class="btn btn-primary">
                                Добавить функцию
                            </button>
                        </div>
                    </li>
                    <li>
                        <div class="btn-group">

                            <button id="view-button" class="btn btn-primary">
                                <img class="icon" src="/resources/images/gtk-refresh.png"/>
                            </button>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
        <div id="project-area" class="my">
            <div class="row" id="functions-container">

            </div>
            <div class="my container off">
                <jsp:include page="/WEB-INF/views/editor.jsp"/>
            </div>
            <div class="result off">

            </div>
        </div>
    </div>
    </jsp:attribute>
</common:mainTemplate>