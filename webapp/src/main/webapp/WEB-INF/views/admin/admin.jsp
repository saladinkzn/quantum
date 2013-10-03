<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Admin page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <%-- Bootstrap --%>
        <link href="/resources/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="/resources/css/bootstrap-theme.min.css" rel="stylesheet" media="screen">
        <%-- Jquery --%>
        <script src="/resources/js/jquery-1.10.2.min.js" type="text/javascript"></script>
        <script src="/resources/js/bootstrap.min.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="container">

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
        </div>
    </body>
</html>