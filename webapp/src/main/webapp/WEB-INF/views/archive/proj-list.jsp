<%@ page import="ru.kpfu.quantum.spring.entities.ProjectGroup" %>
<%@ page import="ru.kpfu.quantum.spring.entities.Project" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="proj-list">
<c:forEach var="record" items="${records}">
    <li>
          <span recordId="${record.id}" class="proj_item">
                  ${record.name}
          </span>
    </li>
</c:forEach>
</div>