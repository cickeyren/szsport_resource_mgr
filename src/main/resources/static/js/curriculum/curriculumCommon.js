/**
 * Created by jack on 2017/5/26.
 */

//添加课程和编辑课程公共操作
$(function(){
    $("#teachers").on("dblclick",function () {
        dbClickOper("teachers","Rteachers");
    });
    $("#Rteachers").on("dblclick",function () {
        dbClickOper("Rteachers","teachers");
    });
    $("#addteacher").on("click",function(){
        addOrRemoveOption("teachers","Rteachers");
    });
    $("#delteacher").on("click",function () {
        addOrRemoveOption("Rteachers","teachers");
    });

    $("#enrollment_required").on("dblclick",function () {
        dbClickOper("enrollment_required","Renrollment_required");
    });
    $("#Renrollment_required").on("dblclick",function () {
        dbClickOper("Renrollment_required","enrollment_required");
    });
    $("#add").on("click",function(){
        addOrRemoveOption("enrollment_required","Renrollment_required");
    });
    $("#del").on("click",function(){
        addOrRemoveOption("Renrollment_required","enrollment_required");
    })
});

//添加选项(弃用)
function addOption(content,id,contentValue) {
    $("#"+id).append('<option value="'+contentValue+'">'+content+'</option>');
}

//双击操作
function dbClickOper(sourceId,targetId){
    var $me = $("#" + sourceId);
    var value = $me.val(),
        text = $me.find("option:selected").text();
    $me.find("option:selected").remove();
    if(null != value && null != text){
        $("#" + targetId).append('<option value="'+value+'">'+text+'</option>');
    }
}

//多选操作
function addOrRemoveOption(sourceId,targetId){
    var $source = $("#" + sourceId).find("option:selected"),
        $target = $("#" + targetId).find("option:selected");
    if(null != $source && null != $target){
        var html = '';
        $source.each(function(){
            var me = this;
            var value = $(me).val(),
                text = $(me).text();
            if(null != value && null != text){
                html += '<option value="'+value+'">'+text+'</option>'
            }
        });
        $source.remove();
        if('' !== html){
            $("#" + targetId).append(html);
        }
    }
}