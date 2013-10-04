<%@ taglib prefix="common" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<common:mainTemplate>
    <jsp:attribute name="title">Восстановление пароля</jsp:attribute>
    <jsp:attribute name="body">
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12">
                <h1>Запрос на восстановление пароля</h1>
                <form role="form" class="form-inline" action="/passwordRemind" method="post">
                    <div class="form-group col-lg-4 col-md-4 col-sm-4">
                        <input class="form-control"
                               type="email"
                               name="email"
                               placeholder="Введите адрес электронной почты" id="email">
                    </div>
                    <button type="submit" class="btn btn-info">
                        Отправить
                    </button>
                </form>
            </div>
        </div>
    </jsp:attribute>
</common:mainTemplate>
