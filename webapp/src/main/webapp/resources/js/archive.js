(function($, undefined) {
    $(document).ready(function() {
        //При клике на элемент списка групп
        $('.my_group_list').on('click', '.group_item', function(){
            var currentProjectList = $(this).closest('li').find('div.proj-list');
            $.get('/archive/my_arch_proj_list', {groupId: $(this).attr('recordId')}, function(data){
                $(currentProjectList).replaceWith($(data)); //Обновляем список проектов для выбранной группы.
            });


        });

        // При клике на элемент списка проектов
        $(document).on('click', '.proj_item', function(){
            $.get('/archive/get-project', {projectId: $(this).attr('recordId')}, function(data){
                $('#project-area').replaceWith($(data));
            });
        });

    });
})(jQuery);
