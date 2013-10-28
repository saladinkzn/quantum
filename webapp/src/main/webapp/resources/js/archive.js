(function($, undefined) {
    $(document).ready(function() {
        //При клике на элемент списка групп
        $('#my_group_list').on('click', 'span', function(e){
            $.get('/archive/my_arch_proj_list', {groupId: $(this).attr('recordId')}, function(data){
                $('#my_arch_proj_list').find('div.proj-list').replaceWith($(data)); //Обновляем список проектов для выбранной группы.
            });

            e.preventDefault();
        });

        $('div.proj-list').on('click', 'span', function(){
            $.get('/working/get-project', {projectId: $(this).attr('recordId')}, function(data){
                $('#project-area').replaceWith($(data));
            });
        });

    });
})(jQuery);
