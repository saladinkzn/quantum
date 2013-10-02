<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="tags" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
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
            <span class="text_titles">Регистрация</span><br />
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
                    <label for="firstname" class="col-lg-2 col-md-2 col-sm-2 control-label">Имя</label>
                    <spring:input path="firstname" cssClass="col-lg-2 col-md-2 col-sm-2" id="firstname"/>
                    <spring:errors cssClass="help-block" path="firstname" cssStyle="color:red"/>
                </div>
                <div class="form-group">
                    <label for="lastname" class="col-lg-2 col-md-2 col-sm-2 control-label">Фамилия</label>
                    <spring:input path="lastname" cssClass="col-lg-2 col-md-2 col-sm-2" id="lastname"/>
                    <spring:errors cssClass="help-block" path="lastname" cssStyle="color:red"/>
                </div>
                <div class="form-group">
                    <label for="userLog" class="col-lg-2 col-md-2 col-sm-2 control-label">Логин(имя пользователя):</label>
                    <%--<input type="text" class="col-lg-2" name="userLog" id="userLog">--%>
                    <spring:input path="userLog" cssClass="col-lg-2 col-md-2 col-sm-2" id="userLog"></spring:input>
                    <spring:errors cssClass="help-block" path="userLog" element="p" cssStyle="color:red"/>
                </div>
                <div class="form-group">
                    <label for="userEmail" class="col-lg-2 col-md-2 col-sm-2 control-label">Адрес эл. почты:</label>
                    <spring:input path="userEmail" cssClass="col-lg-2 col-md-2 col-sm-2" id="userEmail"/>
                    <spring:errors cssClass="help-block" path="userEmail" cssStyle="color:red"/>
                </div>
                <div class="form-group">
                    <label for="passw" class="col-lg-2 col-md-2 col-sm-2 control-label">Пароль:&nbsp;&nbsp;</label>
                    <spring:password path="passw" cssClass="col-lg-2 col-md-2 col-sm-2" id="passw"/>
                    <spring:errors cssClass="help-block" path="passw" cssStyle="color:red"/>
                </div>
                <div class="form-group">
                    <label for="checkPassw" class="col-lg-2 col-md-2 col-sm-2 control-label">Подтверждение пароля:&nbsp;&nbsp;</label>
                    <spring:password path="checkPassw" cssClass="col-lg-2 col-md-2 col-sm-2" id="checkPassw"/>
                    <spring:errors path="checkPassw" cssStyle="color:red"/>
                </div>
                <div class="form-group">
                    <label for="registrKey" class="col-lg-2 col-md-2 col-sm-2 control-label">Код регистрации:&nbsp;&nbsp;</label>
                    <spring:input path="registrKey" cssClass="col-lg-2 col-md-2 col-sm-2" id="registrKey"/>
                    <spring:errors cssClass="help-block" path="registrKey" cssStyle="color:red"/>
                </div>
                <div class="form-group">
                    <div class="col-lg-offset-2 col-md-offset-2 ">
                        <button type="submit" name="send" id="button_reg" class="btn btn-info">Регистрация</button>
                    </div>
                </div>
                </spring:form>
                <%--</form>--%>
            </div>
        </div>
    </jsp:attribute>
</custom:mainTemplate>