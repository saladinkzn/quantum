<%@ page import="ru.kpfu.quantum.spring.entities.Project" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="project-area" class="my container">
    <button id="view-button" class="btn btn-primary pull-right"></button>
    <textarea  spellcheck="false" calculated="${project.calculated}" class="form-control code-area">${project.code}</textarea>
    <div class="my container off">

            <div id="form" style="width:1100px; height:600px">
                <script type="text/javascript" src="/resources/js/editor/painter.js"></script>
                <script type="text/javascript" src="/resources/js/editor/function.js"></script>

                <div id="menu" style="background-color:#f6f6fd;height:inherit; width:200px;float:left;">
                    <b>Functions</b><br>
                    <form>
                        Function name:<br/>
                        <input type="text" id='fnname' name="fnname" size = "24" maxlength="24"/>
                        <br/>
                        Args Count: <input type="text" onkeyup="this.value=this.value.replace(/[^\d]/,'')"
                                           id='argC' name="argC" size = "4" maxlength="4"/>
                        <input type="button" id='addFnButton' onclick="fnList.add()" value="Add" disabled = "true" />
                    </form>
                    <form>
                        <br/>
                        Arg name:<br/>
                        <input type="text" id='argName' name="argName" size = "24" maxlength="24"/>
                        <br/>
                    </form>
                    <canvas id='fnList' style="background-color:#FFFFFF; width:inherit; height:inherit;" >
                        ...
                    </canvas>
                    <script type="text/javascript" src="/resources/js/editor/fnList.js"></script>
                </div>

                <div id="content"
                     style="width:700px; height:inherit; float:left;">
                    <canvas id='editor' style="background-color:#FFFFFF; width:inherit; height:inherit;" >
                        Loading...
                    </canvas>
                    <script type="text/javascript" src="/resources/js/editor/editor.js"></script>
                </div>

                <div id="menu" style="background-color:#f6f6fd;height:inherit; width:200px;float:right;">
                    <b>Gates</b><br>
                    <canvas id='gates' style="background-color:#FFFFFF; width:inherit; height:inherit;" >
                        ...
                    </canvas>
                    <script type="text/javascript" src="/resources/js/editor/gates.js"></script>
                </div>

            </div>

    </div>
    <div class="result">
        <c:if test="${project.calculated}">
            <a download class="btn btn-primary" href="/media/${imageName}">Скачать</a>
            <img class="result" src="/media/${imageName}"/>
        </c:if>
    </div>
</div>

