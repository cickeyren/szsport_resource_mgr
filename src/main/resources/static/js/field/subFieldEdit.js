$(function () {
    //编辑界面保存按钮点击事件 ---  执行保存
    $("#saveSubField").bind("click", function () {
        doSave();
    });

    //编辑界面保存按钮点击事件 ---  返回主界面
    $("#cancelSubField").bind("click", function () {
        window.location.href = "/FieldController/subField.html?subStadiumid=" + $('#stadium_id').val();
    });

    //初始化富文本编辑器(该代码需放在所有事件初始化最后执行) -- 场地介绍
    KindEditor.options.filterMode = false;
    var fieldDescriptionEditor;
    KindEditor.ready(function (K) {
        fieldDescriptionEditor = K.create('textarea[name="introduction"]', {
            resizeType: 1,
            allowPreviewEmoticons: false,
            filterMode: false
        });

        window.introductionEditor = fieldDescriptionEditor;
    });
});


//新增页面添加数据
function doSave() {
    //准备json数据
    var addJson = {};
    addJson.id = $('input[id="id"]').val();
    addJson.stadiumId = $("#stadium_id").val();
    addJson.fieldName = $("#field_name").val();
    addJson.displayName = $("#display_name").val();
    addJson.status = $('input[name="status"]:checked').val();
    window.introductionEditor.sync();
    addJson.introduction = window.introductionEditor.html();

    $.ajax({
        url: '/FieldController/editSubField.do',
        type: 'POST', //GET
        data: addJson,
        // contentType:'application/json',
        dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success: function (result) {
            if ("000000" == result.code) {
                layer.msg("更新成功！");
                setTimeout(function () {
                    window.location.href = "/FieldController/subField.html?subStadiumid=" + $('#stadium_id').val();
                }, 1000);
            } else {
                layer.msg('更新失败', {
                    time: 1000 //20s后自动关闭
                });
            }
        },
        error: function (result) {
            layer.msg("更新失败！");
        }
    });
}