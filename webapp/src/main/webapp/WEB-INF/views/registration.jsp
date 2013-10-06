<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="tags" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<custom:mainTemplate>
    <jsp:attribute name="title">
        Регистрация
    </jsp:attribute>
    <jsp:attribute name="customHead">
        <script type="text/javascript">
                <!--
                function validate_form() {
                    var firstname = document.getElementById("firstname");
                    if (firstname.value=="") {
                        window.alert("Введите имя");
                        firstname.focus();
                        return false;
                    }
                    var lastname = document.getElementById("lastname");
                    if (lastname.value=="") {
                        window.alert("Введите фамилию");
                        lastname.focus();
                        return false;
                    }
                    var userLog = document.getElementById("userLog");
                    if (userLog.value=="") {
                        window.alert("Введите логин"); //validation is required!!!
                        userLog.focus();
                        return false;
                    }

                    var userEmail = document.getElementById("userEmail");
                    p = /^[a-z0-9_\.\-]+@([a-z0-9\-]+\.)+[a-z]{2,6}$/i;
                    if (!p.test(userEmail.value)) {
                        window.alert("Неверный адрес E-mail");
                        userEmail.focus();
                        return false;
                    }
                    var passw = document.getElementById("passw");
                    var checkPassw = document.getElementById("checkPassw");
                    if (passw.value=="") {
                        window.alert("Введите пароль");
                        passw.focus();
                        return false;
                    }
                    else if (passw.value != checkPassw.value) {
                        window.alert("Пароли должны совпадать");
                        passw.focus();
                        return false;
                    }
                    var registrKey = document.getElementById("registrKey");
                    if (registrKey.value=="") {
                        window.alert("Введите код регистрации");
                        registrKey.focus();
                        return false;
                    }
                    return true;
                }
                //-->
            </script>
    </jsp:attribute>
    <jsp:attribute name="body">
        <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12" id="reg"><br/>
            <spring:form
                    acceptCharset="UTF-8"
                    cssClass="form-horizontal"
                    name="reg_form"
                    method="post"
                    action="/registration"
                    commandName="registrationBean"
                    role="form"
                    >
                <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10 col-md-offset-2 col-md-10 col-sm-offset-2 col-sm-10">
                        <h2>Регистрация</h2>
                    </div>
                </div>

                <div class="form-group <c:if test="${fieldsWithError['firstname']}">has-error</c:if>">
                    <label for="firstname" class="col-lg-2 col-md-2 col-sm-2 control-label">Имя</label>
                    <div class="col-lg-4 col-md-4 col-sm-4">
                        <spring:input path="firstname" cssClass="form-control" id="firstname"/>
                    </div>
                    <div class="col-lg-3 col-md-3 col-sm-3">
                        <spring:errors cssClass="help-block" path="firstname"/>
                    </div>
                </div>
                <div class="form-group <c:if test="${fieldsWithError['lastname']}">has-error</c:if>">
                    <label for="lastname" class="col-lg-2 col-md-2 col-sm-2 control-label">Фамилия</label>
                    <div class="col-lg-4 col-md-4 col-sm-4">
                        <spring:input path="lastname" cssClass="form-control" id="lastname"/>
                    </div>
                    <div class="col-lg-3 col-md-3 col-sm-3">
                        <spring:errors cssClass="help-block" path="lastname" />
                    </div>
                </div>
                <div class="form-group <c:if test="${fieldsWithError['userLog']}">has-error</c:if>">
                    <label for="userLog" class="col-lg-2 col-md-2 col-sm-2 control-label">Логин<br/>(имя пользователя)</label>
                    <div class="col-lg-4 col-md-4 col-sm-4">
                        <spring:input path="userLog" cssClass="form-control" id="userLog"/>
                    </div>
                    <div class="col-lg-3 col-md-3 col-sm-3">
                        <spring:errors cssClass="help-block" path="userLog" element="p" />
                    </div>
                </div>
                <div class="form-group <c:if test="${fieldsWithError['userEmail']}">has-error</c:if>">
                    <label for="userEmail" class="col-lg-2 col-md-2 col-sm-2 control-label">Адрес эл. почты</label>
                    <div class="col-lg-4 col-md-4 col-sm-4">
                        <spring:input type="email" path="userEmail" cssClass="form-control" id="userEmail"/>
                    </div>
                    <div class="col-lg-3 col-md-3 col-sm-3">
                    <spring:errors cssClass="help-block" path="userEmail" />
                        </div>
                </div>
                <div class="form-group <c:if test="${fieldsWithError['passw']}">has-error</c:if>">
                    <label for="passw" class="col-lg-2 col-md-2 col-sm-2 control-label">Пароль</label>
                    <div class="col-lg-4 col-md-4 col-sm-4">
                        <spring:password path="passw" cssClass="form-control" id="passw"/>
                    </div>
                    <div class="col-lg-3 col-md-3 col-sm-3">
                        <spring:errors cssClass="help-block" path="passw" />
                    </div>
                </div>
                <div class="form-group <c:if test="${fieldsWithError['checkPassw']}">has-error</c:if>">
                    <label for="checkPassw" class="col-lg-2 col-md-2 col-sm-2 control-label">Подтверждение пароля</label>
                    <div class="col-lg-4 col-md-4 col-sm-4">
                        <spring:password path="checkPassw" cssClass="form-control" id="checkPassw"/>
                    </div>
                    <div class="col-lg-3 col-md-3 col-sm-3">
                        <spring:errors cssClass="help-block" path="checkPassw" />
                    </div>
                </div>
                <div class="form-group <c:if test="${fieldsWithError['registrKey']}">has-error</c:if>">
                    <label for="registrKey" class="col-lg-2 col-md-2 col-sm-2 control-label">Код регистрации</label>
                    <div class="col-lg-4 col-md-4 col-sm-4">
                        <spring:input path="registrKey" cssClass="form-control" id="registrKey"/>
                    </div>
                    <div class="col-lg-3 col-md-3 col-sm-3">
                        <spring:errors cssClass="help-block" path="registrKey" />
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-2 col-md-offset-2 col-md-2 col-sm-offset-2 col-sm-2">
                        <button type="submit" name="send" id="button_reg" class="btn btn-info">Регистрация</button>
                    </div>
                </div>
                </spring:form>
                <%--</form>--%>
            </div>
        </div>
    </jsp:attribute>
</custom:mainTemplate>