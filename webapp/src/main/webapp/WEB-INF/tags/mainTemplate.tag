<%@ tag display-name="mainTemplate" %>
<%@ attribute name="title" fragment="true" %>
<%@ attribute name="body" fragment="true" %>
<%@ attribute name="customHead" fragment="true" %>
<!DOCTYPE html>
<html>
    <head>
        <title>
            <jsp:invoke fragment="title"/>
        </title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <%-- Bootstrap --%>
        <link href="/resources/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="/resources/css/bootstrap-theme.min.css" rel="stylesheet" media="screen">
        <%-- Jquery --%>
        <script src="/resources/js/jquery-1.10.2.min.js" type="text/javascript"></script>
        <script src="/resources/js/bootstrap.min.js" type="text/javascript"></script>
        <jsp:invoke fragment="customHead"/>
    </head>
    <body>
        <div class="container">
            <jsp:include page="/WEB-INF/views/common/header.jsp"/>
            <jsp:invoke fragment="body"/>
        </div>
    </body>
</html>