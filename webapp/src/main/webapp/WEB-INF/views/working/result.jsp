<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"%>
<%--@elvariable id="functions" type="java.util.List<ru.kpfu.quantum.spring.entities.Function>"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <div class="result">
        <div class="row">
            <c:forEach var="function" items="${functions}">
                <div class="col-lg-3 col-md-4 cols-sm-2">
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
        </div>
    </div>

