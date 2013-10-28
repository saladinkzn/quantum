<%@ page import="ru.kpfu.quantum.spring.entities.ProjectGroup" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="groups" type="java.util.List<ru.kpfu.quantum.spring.entities.ProjectGroup>"--%>

<!DOCTYPE html>

<html>

<head>
    <title>Archive</title>
    <meta name="viewport" content="width=device-width">
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="/resources/css/bootstrap-theme.min.css" rel="stylesheet" media="screen">
    <link href="/resources/css/jquery.treeview.css" rel="stylesheet">
    <link rel="stylesheet" href="/resources/css/archive.css">
    <script src="/resources/js/jquery-1.10.2.min.js" type="text/javascript"></script>
    <script src="/resources/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="/resources/js/archive.js" type="text/javascript"></script>
    <script type="text/javascript" src="/resources/js/jquery.treeview.js"></script>

</head>

<body>

<script type="text/javascript">
    $(document).ready(function(){
        $("#my_group_list").treeview();
    });
</script>

<div class="container">
    <ul class="nav pull-right nav-pills">
        <li>
            <a href="working">Разработка</a>
        </li>
        <li  class="active">
            <a href="#">Архив</a>
        </li>
        <li>
            <a href="#">Справка</a>
        </li>
    </ul>
</div>
<div id='archive-area' class="container archive-area">
    <div class="my container">
            <ul id="my_group_list">
                <c:forEach var="record" items="${groups}">
                    <li class="closed" >
                        <span>
                            ${record.name}
                        </span>
                        <ul id="my_arch_proj_list">
                            <div class="proj-list">
                            </div>
                        </ul>
                    </li>
                </c:forEach>
            </ul>


    </div>
    <div id="project-area" class="my container">
    </div>

</div>
</body>

</html>