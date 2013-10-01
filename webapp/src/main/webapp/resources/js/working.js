(function($, undefined) {
$(document).ready(function() {


    // Клик по полю ввода в ниспадающих меню групп и проектов не приведёт к закрытию списка
     $('.dropdown-menu').on('click', 'input', function ( e ) {
         e.stopPropagation();
     });

    //Снимаем обработчик события на нажатия клавиши, который повесили ниже
    $('.dropdown-menu').on('focusout', 'input', function(){
        $(this).val("");
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
                    $('#calculate-button').attr('disabled', 'disables');
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
        $.get('/working/project-list', {groupId: $(this).attr('recordId')}, function(data){
            $('#project-list').find('div.data-list').replaceWith($(data)); //Обновляем список проектов для выбранной группы.
        });
        var projectListButton = $('#project-list').find('button');
        $(projectListButton).html('Projects'+' <span class="caret"></span>');
        $(projectListButton).attr('projectId', "");
        $(projectListButton).removeAttr('disabled');
        var codeArea = $('#working-area').find('textarea.code-area');
        $(codeArea).val("");
        $(codeArea).attr('disabled', 'disabled');
        $('#calculate-button').attr('disabled', 'disables');
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
                         $('#working-area').find('textarea.code-area').removeAttr('disabled');
                         $.get('/working/project-list', {groupId: $('#group-list').find('button').attr('groupId')}, function(data){
                             $('#project-list').find('div.data-list').replaceWith($(data));
                         })
                         $(projectListButton).dropdown('toggle');
                         $(projectListButton).removeAttr('disabled');
                         $('#calculate-button').removeAttr('disabled');
                         $(currentInput).val("");
                         $('#working-area').find('textarea.code-area').val("");
                     });
             }
         });
     });

     //При клике на элемент списка проектов
     $('#project-list').on('click', 'a', function(e){
         var projectListButton = $('#project-list').find('button');
         $(projectListButton).html($(this).html()+' <span class="caret"></span>');
         $(projectListButton).attr('projectId', $(this).data('recordId'));
         var codeArea = $('#working-area').find('textarea.code-area');
         $(codeArea).removeAttr('disabled');
         $(codeArea).val("");
         $('#calculate-button').removeAttr('disabled');
         $.get('/working/get-сode', {projectId: $(this).attr('recordId')}, function(data){
             $(codeArea).val(data);
         });
         e.preventDefault();
     })

    //Рассчитываем проект
    $('#calculate-button').click(function(){
        $.post('/working/set-code',
            {
                projectId: $('#project-list').find('button').attr('projectId'),
                code: $('#working-area').find('textarea.code-area').val()
            },
            function(){

            });
    });

});
})(jQuery);