<%@ taglib prefix="common" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                        <div class="form-group <c:if test="${fieldErrors['login']}">has-error</c:if>">
                            <label for="login" class="control-label col-lg-2 col-md-2 col-sm-2">Логин</label>
                            <div class="col-lg-4 col-md-4 col-sm-4">
                                <spring:input path="login" id="login" cssClass="form-control"/>
                            </div>
                            <div class="col-lg-3 col-md-3 col-sm-3">
                                <spring:errors path="login" cssClass="help-block"/>
                            </div>
                        </div>
                        <div class="form-group <c:if test="${fieldErrors['password']}">has-error</c:if>">
                            <label for="password" class="control-label col-lg-2 col-md-2 col-sm-2">Пароль</label>
                            <div class="col-lg-4 col-md-4 col-sm-4">
                                <spring:password path="password" id="password" cssClass="form-control"/>
                            </div>
                            <div class="col-lg-3 col-md-3 col-sm-3">
                                <spring:errors path="password" cssClass="help-block"/>
                            </div>

                        </div>
                        <div class="form-group">
                            <div class="col-lg-offset-2 col-md-offset-2 col-sm-offset-2 col-lg-3 col-md-3 col-sm-3">
                                <spring:button class="btn btn-info">Войти</spring:button>
                            </div>
                        </div>
                    </spring:form>
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-offset-2 col-md-offset-2 col-sm-offset-2">
                                <a href="/registration">Зарегистрироваться</a>
                            </div>
                            <div class="col-lg-offset-2 col-md-offset-2 col-sm-offset-2">
                                <a href="/passwordRemind">Восстановление пароля</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
</common:mainTemplate>