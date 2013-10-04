<%@ taglib prefix="common" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--@elvariable name="code" type="java.lang.String" --%>
<common:mainTemplate>
    <jsp:attribute name="title">Смена пароля</jsp:attribute>
    <jsp:attribute name="body">
        <div class="row">
            <form role="form" class="form-horizontal" action="/changePassword" method="post">
                <div class="form-group">
                    <div class="col-lg-4 col-lg-offset-2 col-md-4 col-md-offset-2 col-sm-4 col-sm-offset-2">
                        <h1>Смена пароля</h1>
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="control-label col-lg-3 col-md-3 col-sm-3">Новый пароль</label>
                    <div class="col-lg-4 col-md-4 col-sm-4">
                        <input type="password" name="password" class="form-control" id="password">
                    </div>
                </div>
                <div class="form-group">
                    <label for="passwordRepeat" class="control-label col-lg-3 col-md-3 col-sm-3">Новый пароль (повтор)</label>
                    <div class="col-lg-4 col-md-4 col-sm-4">
                        <input type="password" name="passwordRepeat" class="form-control" id="passwordRepeat">
                    </div>
                </div>
                <input type="hidden" name="code" value="${code}">
                <div class="form-group">
                    <div class="col-lg-offset-3 col-lg-2 col-md-offset-3 col-md-2 col-sm-offset-3 col-sm-2">
                        <button type="submit" class="btn btn-info">Сменить</button>
                    </div>
                </div>
            </form>
        </div>
    </jsp:attribute>
</common:mainTemplate>
