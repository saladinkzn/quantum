(function($, undefined) {
$(document).ready(function() {
    function getCode() {
        var functions = [];
        $('.js-function').each(function() {
            functions.push($(this).val());
        });
        return JSON.stringify(functions);
    }

    // Клик по полю ввода в ниспадающих меню групп и проектов не приведёт к закрытию списка
     $('.dropdown-menu').on('click', 'input', function ( e ) {
         e.stopPropagation();
     });

    //Снимаем обработчик события на нажатия клавиши, который повесили ниже
    $('.dropdown-menu').on('focusout', 'input', function(){
        $(this).val("");
        $(this).unbind();
    });

    //Вешаем обработчик события нажатия клавиши enter на поля ввода в ниспадающих меню
    $('#group-list').on('focus', 'input', function(){
        $(this).bind('keypress', function(e){
            if (e.which == 13) {
                var currentInput = $(this);
                var groupListButton = $('#group-list').find('button');
                $(groupListButton).html($(this).val()+' <span class="caret"></span>');
                $.post('/working/create-group', {groupName: $(this).val()}, function(id){ //post добавляем новую группу в базу данных
                    $(this).unbind('keypress');
                    $('#project-list').find('div.data-list').html(""); //удаляем содержимое data-list в проектах
                    $(groupListButton).attr('groupId', id);
                    $.get('/working/group-list', function(data){
                        $('#group-list').find('div.data-list').replaceWith($(data)); //Записываем в список групп обновлённый список
                    });
                    var codeArea = $('#working-area').find('textarea.code-area');
                    $(codeArea).val("");
                    $(codeArea).attr('disabled', 'disabled'); //Делаем поле ввода кода неактивным
                    $('#calculate-button').attr('disabled', 'disabled');
                    $('#save-button').attr('disabled', 'disabled');
                    $('#archive-button').attr('disabled', 'disabled');
                    $(groupListButton).dropdown('toggle');
                    $(currentInput).val("");
                    var projectListButton = $('#project-list').find('button');
                    $(projectListButton).html('Projects'+' <span class="caret"></span>');
                    $(projectListButton).attr('projectId', "");
                    $(projectListButton).removeAttr('disabled');
                });
            }
        });
    });

    //При клике на элемент списка групп
    $('#group-list').on('click', 'a', function(e){
        var groupListButton = $('#group-list').find('button');
        $(groupListButton).html($(this).html()+' <span class="caret"></span>');
        $(this).attr('recordId');
        $(groupListButton).attr('groupId', $(this).attr('recordId'));
        $.get('/working/project-list', {groupId: $(this).attr('recordId'),
            filter: $('#filter-list').find('button').val()}, function(data){
            $('#project-list').find('div.data-list').replaceWith($(data)); //Обновляем список проектов для выбранной группы.
        });
        var projectListButton = $('#project-list').find('button');
        $(projectListButton).html('Projects'+' <span class="caret"></span>');
        $(projectListButton).attr('projectId', "");
        $(projectListButton).removeAttr('disabled');
        var codeArea = $('#working-area').find('textarea.code-area');
        $(codeArea).val("");
        $(codeArea).attr('disabled', 'disabled');
        $('#calculate-button').attr('disabled', 'disabled');
        $('#save-button').attr('disabled', 'disabled');
        $('#archive-button').attr('disabled', 'disabled');
        e.preventDefault();
    });

     //Создание нового проекта по нажатию enter в поле ввода
     $('#project-list').on('focus', 'input', function(){
         $(this).bind('keypress', function(e){
             if(e.which == 13) {
                 var currentInput = $(this);
                 $.post('/working/create-project',
                     {
                         groupId: $('#group-list').find('button').attr('groupId'),
                        projectName: $(this).val()
                     },
                     function(id){
                         $(this).unbind('keypress');
                         var projectListButton = $('#project-list').find('button');
                         $(projectListButton).html($(currentInput).val() + ' <span class="caret"></span>');
                         $(projectListButton).attr('projectId', id);
                         $('#toolPanel').removeClass('off');
                         $.get('/working/project-list', {groupId: $('#group-list').find('button').attr('groupId'),
                             filter: $('#filter-list').find('button').val()}, function(data){
                             $('#project-list').find('div.data-list').replaceWith($(data));
                         })
                         $(projectListButton).dropdown('toggle');
                         $(projectListButton).removeAttr('disabled');
                         $('#calculate-button').removeAttr('disabled');
                         $('#save-button').removeAttr('disabled');
                         $('#archive-button').attr('disabled', 'disabled');
                         $(currentInput).val("");
                         $('#functions-container').empty();
                         $('#project-area').find('div.result').empty();
                     });
             }
         });
     });

     //При клике на элемент списка проектов
     $('#project-list').on('click', 'a', function(e){
         var projectListButton = $('#project-list').find('button');
         $(projectListButton).html($(this).html()+' <span class="caret"></span>');
         $(projectListButton).attr('projectId', $(this).attr('recordId'));
         $.get('/working/get-project', {projectId: $(this).attr('recordId')}, function(data){
             $('#toolPanel').removeClass('off');
             $('#project-area').replaceWith($(data));
             if($('#project-area').data('calculated') == 'true') {
                 $('#archive-button').removeAttr('disabled');
             }
             else {
                 $('#archive-button').attr('disabled', 'disabled');
             }
             if(getCode() != '[]') {
                 $('#save-button').removeAttr('disabled');
                 $('#calculate-button').removeAttr('disabled');
                 $.get('/working/get-circuit', {code: getCode()}, function(circuit){
                     editor.setProject(circuit);
                 });
             } else {
                 $('#save-button').attr('disabled', 'disabled');
                 $('#calculate-button').attr('disabled', 'disabled');
             }
         });
         e.preventDefault();
     })


    //При клике на элемент списка фильтров
    $('#filter-list').on('click', 'a', function(e){
        var filterListButton = $('#filter-list').find('button');
        $(filterListButton).html($(this).html()+' <span class="caret"></span>');
        $(filterListButton).val($(this).html());
        var groupListButton = $('#group-list').find('button');
        if(groupListButton != ""){
            $.get('/working/project-list', {groupId: $(groupListButton).attr('groupId'),
                filter: $(filterListButton).val()}, function(data){
                $('#project-list').find('div.data-list').replaceWith($(data));
            });
        }
        e.preventDefault();
    });

    //Сохраняем проект
    $('#save-button').click(function(){
        var project;
        var typeOfEditor;
        if($('#project-area').find('div.my').hasClass('off')){
            project = getCode();
            typeOfEditor = "text";
        } else {
            project = editor.getProjectAsJson();
            typeOfEditor = "visual";
        }
        $.post('/working/save',
            {
                projectId: $('#project-list').find('button').attr('projectId'),
                code: project,
                typeOfEditor: typeOfEditor
            },
            function(){
                $('#working-area').find('div.result').addClass('off');
                $('#archive-button').attr('disabled', 'disabled');
                if($('#project-area').find('div.my').hasClass('off')){
                    $.get('/working/get-circuit', {code: getCode()}, function(circuit){
                        editor.setProject(circuit);
                    });
                }
                else {
                    $.get('/working/get-code', {projectId: $('#project-list').find('button').attr('projectId')}, function(code){
                        code = JSON.parse(code);
                        $('#functions-container').empty();
                        for(var i = 0; i < code.length; i++) {
                            addFunction().val(code[i]);
                        }
                    });
                }
            });
    });

    //Рассчитываем проект
    $('#calculate-button').click(function(){
        var project;
        var typeOfEditor;
        if($('#project-area').find('div.my').hasClass('off')){
            project = getCode();
            typeOfEditor = "text";
        }
        else {
            project = editor.getProjectAsJson();
            typeOfEditor = "visual";
        }
        $.post('/working/calculate',
            {
                projectId: $('#project-list').find('button').attr('projectId'),
                code: project,
                typeOfEditor: typeOfEditor
            },
            function(data){
                $('#working-area').find('div.result').replaceWith($(data));
                $('#archive-button').removeAttr('disabled');
                if($('#project-area').find('div.my').hasClass('off')){
                    $.get('/working/get-circuit', {code: getCode() }, function(circuit){
                        editor.setProject(circuit);
                    });
                }
                else {
                    $.get('/working/get-code', {projectId: $('#project-list').find('button').attr('projectId')}, function(code){
                        code = JSON.parse(code);
                        $('#functions-container').empty();
                        for(var i = 0; i < code.length; i++) {
                            addFunction().val(code[i]);
                        }
                    });
                }
            }
        ).fail(function() {
            alert('Синтаксическая ошибка')
        });
    });

    //Архивировать проект
    $('#archive-button').click(function(){
        $.post('/working/archive',
            {
                projectId: $('#project-list').find('button').attr('projectId')            },
            function(){

        });
    });

    //Переключение типа редактора
    $('#working-area')
        .on('click', 'button#view-button', function(){
            $('#project-area').find('textarea.code-area').toggleClass('off');
            if($('#project-area').find('div.my').hasClass('off')) {
                $('#save-button').removeAttr('disabled');
                $('#calculate-button').removeAttr('disabled');
            } else {
                if(getCode() == '[]') {
                    $('#save-button').attr('disabled', 'disabled');
                    $('#calculate-button').attr('disabled', 'disabled');
                }
            }
            $('#project-area').find('div.my').toggleClass('off');
            $('#add-function').toggleClass('off');

        })
        .on('click', '#add-function', function() {
            addFunction();
            $('#save-button').removeAttr('disabled');
            $('#calculate-button').removeAttr('disabled');
        });
    });
    function addFunction() {
        //spellcheck="false" class="form-control code-area js-function"
        var $container = $('<div/>').addClass('row');
        var $textarea = $('<textarea/>').addClass('js-function').addClass('code-area').addClass('form-control').attr('spellcheck', false);
        if(!$('#project-area').find('div.my').hasClass('off')) {
            $textarea.addClass('off');
        }
        $textarea.appendTo($container);
        $('#functions-container').append($container);
        return $textarea;
    }
})(jQuery);