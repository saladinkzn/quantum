<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>

<html>

<head>
    <title>New Page</title>
    <meta name="viewport" content="width=device-width">
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="/resources/css/bootstrap-theme.min.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" href="/resources/css/working.css">
    <script src="/resources/js/jquery-1.10.2.min.js" type="text/javascript"></script>
    <script src="/resources/js/bootstrap.min.js" type="text/javascript"></script>
</head>

<body>
<div class="container">
    <ul class="nav pull-right nav-pills">
        <li class="active">
            <a href="#">Разработка</a>
        </li>
        <li>
            <a href="#">Архив</a>
        </li>
        <li>
            <a href="#">Справка</a>
        </li>
    </ul>
</div>
<div class="container working-area">
    <ul class="pull-left list-inline">
        <li>
            <select class="form-control">
                <option>Option 1</option>
                <option>Option 2</option>
                <option>Option 3</option>
            </select>
        </li>
        <li>
            <select class="form-control">
                <option>Option 1</option>
                <option>Option 2</option>
                <option>Option 3</option>
            </select>
        </li>
        <li>
            <select class="form-control">
                <option>Option 1</option>
                <option>Option 2</option>
                <option>Option 3</option>
            </select>
        </li>
    </ul>
    <ul class="pull-right list-inline">
        <li>
            <a class="btn btn-primary">Настройка режима отображения</a>
        </li>
        <li>
            <a class="btn btn-primary">В архив</a>
        </li>
        <li>
            <a class="btn btn-primary">Рассчитать</a>
        </li>
    </ul>
    <textarea class="form-control code-area"></textarea>
    <div class="result">
        <a class="btn btn-primary">Скачать</a>
        <img class="result" src="/resources/images/test.jpg">
    </div>
</div>
</body>

</html>