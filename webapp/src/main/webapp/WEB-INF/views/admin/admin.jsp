<%@ taglib prefix="common" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"%>
<common:mainTemplate>
    <jsp:attribute name="title">Кабинет администратора</jsp:attribute>
    <jsp:attribute name="body">
        <form role="form" class="form-horizontal" action="/admin/sendInvite" method="post">
            <div class="form-group">
                <div class="col-lg-offset-2 col-lg-10 col-md-offset-2 col-md-10 col-sm-offset-2 col-sm-10">
                    <h2>Отправка приглашения</h2>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-2 col-md-2 col-sm-2 control-label" for="email">E-mail</label>
                <div class="col-lg-3 col-md-3 col-sm-3">
                    <input class="form-control" type="email" name="email" id="email">
                </div>
            </div>
            <div class="form-group">
                <div class="col-lg-offset-2 col-lg-2 col-md-offset-2 col-md-2 col-sm-offset-2 col-sm-2">
                    <button type="submit" class="btn btn-info">Отправить</button>
                </div>
            </div>
        </form>
    </jsp:attribute>
</common:mainTemplate>