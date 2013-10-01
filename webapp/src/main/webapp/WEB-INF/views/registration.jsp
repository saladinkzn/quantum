<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/resources/css/registration.css">
    <%-- Bootstrap --%>
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="/resources/css/bootstrap-theme.min.css" rel="stylesheet" media="screen">
    <%-- Jquery --%>
    <script src="/resources/js/jquery-1.10.2.min.js" type="text/javascript"></script>
    <script src="/resources/js/bootstrap.min.js" type="text/javascript"></script>

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
</head>
<body>


    <div id="reg"><br/>
        <span class="text_titles">Регистрация</span><br />
        <form name="reg_form" method="post" action="" onsubmit="return validate_form( );">
        <table width="400" height="400" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td class="text_forms"><div align="right">Имя:&nbsp;&nbsp;</div></td>
                <td><input type="text" name="firstname" id="firstname"></td>
            </tr>
            <tr>
                <td class="text_forms"><div align="right">Фамилия:&nbsp;&nbsp;</div></td>
                <td><input type="text" name="lastname" id="lastname"></td>
            </tr>
            <tr>
                <td class="text_forms"><div align="right">Логин(имя пользователя):&nbsp;&nbsp;</div></td>
                <td><input type="text" name="userLog" id="userLog"></td>
            </tr>
            <tr>
                <td class="text_forms"><div align="right">Адрес эл. почты:&nbsp;&nbsp;</div></td>
                <td><input type="text" name="userEmail" id="userEmail"></td>
            </tr>
            <tr>
                <td class="text_forms"><div align="right">Пароль:&nbsp;&nbsp;</div></td>
                <td><input type="password" name="passw" id="passw"></td>
            </tr>
            <tr>
                <td class="text_forms"><div align="right">Подтверждение пароля:&nbsp;&nbsp;</div></td>
                <td><input type="password" name="checkPassw" id="checkPassw"></td>
            </tr>
            <tr>
                <td class="text_forms"><div align="right">Код регистрации:&nbsp;&nbsp;</div></td>
                <td><input type="text" name="registrKey" id="registrKey"></td>
            </tr>
        </table>
        <br /><br /><input type="submit" name="send" id="button_reg" class="text_buttons" value="Регистрация">
        </form>

     </div>


</body>
</html>