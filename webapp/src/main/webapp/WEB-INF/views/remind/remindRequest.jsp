<%@ taglib prefix="common" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<common:mainTemplate>
    <jsp:attribute name="title">Восстановление пароля</jsp:attribute>
    <jsp:attribute name="body">
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12">
                <h1>Запрос на восстановление пароля</h1>
                <spring:form role="form" cssClass="form-horizontal" commandName="remindBean" action="/passwordRemind" method="post">
                    <div class="form-group">
                        <div class="col-lg-4 col-md-4 col-sm-4 <c:if test="${fieldsWithError['email']}">has-error</c:if>">
                        <spring:input class="form-control"
                               type="email"
                               path="email"
                               placeholder="Введите адрес электронной почты" id="email"/>
                        <spring:errors cssClass="help-block" element="span" path="email"/>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2">
                            <spring:button class="btn btn-info">
                                Отправить
                            </spring:button>
                        </div>
                    </div>

                    <div class="form-group">

                    </div>
                </spring:form>
            </div>
        </div>
    </jsp:attribute>
</common:mainTemplate>
