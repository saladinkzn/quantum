<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
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
        <div id="reg"><br/>
            <span class="text_titles">Регистрация</span><br />
            <form role="form" class="form-horizontal" name="reg_form" method="post" action="/registration" onsubmit="return validate_form( );">
                <div class="form-group">
                    <label for="firstname" class="col-lg-2 control-label">Имя</label>
                    <input type="text" class="col-lg-2" name="firstname" id="firstname">
                </div>
                <div class="form-group">
                    <label for="lastname" class="col-lg-2 control-label">Фамилия</label>
                    <input type="text" class="col-lg-2" name="lastname" id="lastname">
                </div>
                <div class="form-group">
                    <label for="userLog" class="col-lg-2 control-label">Логин(имя пользователя):</label>
                    <input type="text" class="col-lg-2" name="userLog" id="userLog">
                </div>
                <div class="form-group">
                    <label for="userEmail" class="col-lg-2 control-label">Адрес эл. почты:</label>
                    <input type="text" class="col-lg-2" name="userEmail" id="userEmail">
                </div>
                <div class="form-group">
                    <label for="passw" class="col-lg-2 control-label">Пароль:&nbsp;&nbsp;</label>
                    <input type="password" class="col-lg-2" name="passw" id="passw">
                </div>
                <div class="form-group">
                    <label for="checkPassw" class="col-lg-2 control-label">Подтверждение пароля:&nbsp;&nbsp;</label>
                    <td><input type="password" class="col-lg-2" name="checkPassw" id="checkPassw"></td>
                </div>
                <div class="form-group">
                    <label for="registrKey" class="col-lg-2 control-label">Код регистрации:&nbsp;&nbsp;</label>
                    <td><input type="text" class="col-lg-2" name="registrKey" id="registrKey"></td>
                </div>
                <div class="form-group">
                    <div class="col-lg-offset-2">
                        <button type="submit" name="send" id="button_reg" class="btn btn-info">Регистрация</button>
                    </div>
                </div>
            </form>
            </div>
        </div>
    </jsp:attribute>
</custom:mainTemplate>