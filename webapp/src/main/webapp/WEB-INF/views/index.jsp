<%@ taglib prefix="common" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"%>
<common:mainTemplate>
    <jsp:attribute name="title">Авторизация</jsp:attribute>
    <jsp:attribute name="body">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12">
                    <spring:form role="form" class="form-horizontal" commandName="loginBean" action="/" method="post">
                        <div class="form-group">
                            <div class="col-lg-offset-2 col-md-offset-2 col-sm-offset-2 col-lg-3 col-md-3 col-sm-3">
                                <h2>Вход</h2>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="login" class="control-label col-lg-2 col-md-2 col-sm-2">Логин</label>
                            <div class="col-lg-3 col-md-3 col-sm-3 ">
                                <spring:input path="login" id="login" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="control-label col-lg-2 col-md-2 col-sm-2">Пароль</label>
                            <div class="col-lg-3 col-md-3 col-sm-3 ">
                                <spring:password path="password" id="password" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-offset-2 col-md-offset-2 col-sm-offset-2 col-lg-3 col-md-3 col-sm-3">
                                <spring:button class="btn btn-info">Войти</spring:button>
                            </div>
                        </div>
                    </spring:form>
                </div>
            </div>
        </div>
    </jsp:attribute>
</common:mainTemplate>