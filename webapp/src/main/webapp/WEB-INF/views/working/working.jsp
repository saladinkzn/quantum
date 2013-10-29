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
        <div class="my container">
            <ul class="pull-left list-inline">
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
            <textarea  spellcheck="false" class="form-control code-area" disabled></textarea>
            <div class="my container off">
                <div id="form" style="width:1100px; height:600px">
                    <script type="text/javascript" src="/resources/js/editor/painter.js"></script>
                    <script type="text/javascript" src="/resources/js/editor/function.js"></script>

                    <div id="menu" style="background-color:#f6f6fd;height:inherit; width:200px;float:left;">
                        <b>Functions</b><br>
                        <form>
                            Function name:<br/>
                            <input type="text" id='fnname' name="fnname" size = "24" maxlength="24"/>
                            <br/>
                            Args Count: <input type="text" onkeyup="this.value=this.value.replace(/[^\d]/,'')"
                                               id='argC' name="argC" size = "4" maxlength="4"/>
                            <input type="button" id='addFnButton' onclick="fnList.add()" value="Add" disabled = "true" />
                        </form>
                        <form>
                            <br/>
                            Arg name:<br/>
                            <input type="text" id='argName' name="argName" size = "24" maxlength="24"/>
                            <br/>
                        </form>
                        <canvas id='fnList' style="background-color:#FFFFFF; width:inherit; height:inherit;" >
                            ...
                        </canvas>
                        <script type="text/javascript" src="/resources/js/editor/fnList.js"></script>
                    </div>

                    <div id="content"
                         style="width:700px; height:inherit; float:left;">
                        <canvas id='editor' style="background-color:#FFFFFF; width:inherit; height:inherit;" >
                            Loading...
                        </canvas>
                        <script type="text/javascript" src="/resources/js/editor/editor.js"></script>
                    </div>

                    <div id="menu" style="background-color:#f6f6fd;height:inherit; width:200px;float:right;">
                        <b>Gates</b><br>
                        <canvas id='gates' style="background-color:#FFFFFF; width:inherit; height:inherit;" >
                            ...
                        </canvas>
                        <script type="text/javascript" src="/resources/js/editor/gates.js"></script>
                    </div>

                </div>
            </div>
            <div class="result off">

            </div>
        </div>
    </div>
    </jsp:attribute>
</common:mainTemplate>