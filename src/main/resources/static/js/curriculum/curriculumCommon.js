/**
 * Created by jack on 2017/5/26.
 */

//添加课程和编辑课程公共操作
$(function(){
    $("#teachers").on("dblclick",function () {
        var result = dbClickOper("teachers");
        addOption(result.text,'Rteachers',result.value);
    });
    $("#Rteachers").on("dblclick",function () {
        var result = dbClickOper("Rteachers");
        addOption(result.text,'teachers',result.value);
    });
    $("#addteacher").on("click",function(){
        addOrRemoveOption("teachers","Rteachers");
    });
    $("#delteacher").on("click",function () {
        addOrRemoveOption("Rteachers","teachers");
    });

    $("#enrollment_required").on("dblclick",function () {
        var result = dbClickOper("enrollment_required");
        addOption(result.text,'Renrollment_required',result.value)
    });
    $("#Renrollment_required").on("dblclick",function () {
        var result = dbClickOper("Renrollment_required");
        addOption(result.text,'enrollment_required',result.value);
    });
    $("#add").on("click",function(){
        addOrRemoveOption("enrollment_required","Renrollment_required");
    });
    $("#del").on("click",function(){
        addOrRemoveOption("Renrollment_required","enrollment_required");
    })
});

//添加选项
function addOption(content,id,contentValue) {
    $("#"+id).append('<option value="'+contentValue+'">'+content+'</option>');
}

//双击获取数据
function dbClickOper(id){
    var $me = $("#" + id);
    var value = $me.val(),
        text = $me.find("option:selected").text();
    $($me).find("option:selected").remove();
    return{
        value: value,
        text: text
    }
}

//多选操作
function addOrRemoveOption(sourceId,targetId){
    var $source = $("#" + sourceId).find("option:selected"),
        $target = $("#" + targetId).find("option:selected");
    if(null !== $source && null !== $target){
        var html = '';
        $source.each(function(){
            var me = this;
            var value = $(me).val(),
                text = $(me).text();
            if(null !== value || null !== text){
                html += '<option value="'+value+'">'+text+'</option>'
            }
        });
        $source.remove();
        if('' !== html){
            $("#" + targetId).append(html);
        }
    }
}