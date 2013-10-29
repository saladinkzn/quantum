<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags" %>
<%--@elvariable id="groups" type="java.util.List<ru.kpfu.quantum.spring.entities.ProjectGroup>"--%>


<common:mainTemplate activePage="archive">

    <jsp:attribute name="title">
        Архив
    </jsp:attribute>
    <jsp:attribute name="customHead">
        <link  rel="stylesheet" href="/resources/css/jquery.treeview.css">
        <link rel="stylesheet" href="/resources/css/archive.css">
        <script src="/resources/js/archive.js" type="text/javascript"></script>
        <script src="/resources/js/jquery.treeview.js" type="text/javascript"></script>
    </jsp:attribute>
    <jsp:attribute name="body">
        <script type="text/javascript">
        $(document).ready(function(){
        $(".my_group_list").treeview();
        });
        </script>

<div id='archive-area' class="container archive-area">
    <div class="tree-list container">
            <ul class="my_group_list">
                <c:forEach var="record" items="${groups}">
                    <li class="closed" >
                        <span recordId="${record.id}" class="group_item">
                            ${record.name}
                        </span>
                        <ul class="my_arch_proj_list">
                            <div class="proj-list">

                            </div>
                        </ul>
                    </li>
                </c:forEach>
            </ul>
    </div>

    <div id="project-area" class="project container">

    </div>

</div>
    </jsp:attribute>
</common:mainTemplate>