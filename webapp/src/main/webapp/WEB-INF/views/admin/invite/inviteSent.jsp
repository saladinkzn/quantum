<%@ taglib prefix="common" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"%>
<common:mainTemplate>
    <jsp:attribute name="title">Приглашение отправлено</jsp:attribute>
    <jsp:attribute name="body">
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12">
                <p>Приглашение было успешно отправлено</p>
                <p><a href="/admin">Продолжить рассылку приглашений</a></p>
            </div>
        </div>
    </jsp:attribute>
</common:mainTemplate>
