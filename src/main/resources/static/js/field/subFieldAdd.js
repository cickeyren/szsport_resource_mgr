$(function () {

    //新增界面保存按钮点击事件 ---  执行保存
    $("#saveSubField").on("click", function () {
        doAdd();
    });

    //新增界面保存按钮点击事件 ---  返回主界面
    $("#cancelSubField").on("click", function () {
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
function doAdd() {
    //准备json数据
    var addJson = {};
    addJson.stadiumId = $("#stadium_id").val();
    addJson.fieldName = $("#field_name").val();
    addJson.displayName = $("#display_name").val();
    addJson.status = $('input[name="status"]:checked').val();
    window.introductionEditor.sync();
    addJson.introduction = window.introductionEditor.html();

    $.ajax({
        url: '/FieldController/addSubField.do',
        type: 'POST', //GET
        data: addJson,
        // contentType:'application/json',
        dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success: function (result) {
            if ("000000" == result.code) {
                layer.msg("添加成功！");
                setTimeout(function () {
                    window.location.href = "/FieldController/subField.html?subStadiumid=" + $('#stadium_id').val();
                }, 1000);
            }else {
                layer.alert(result.result);
            }
        },
        error: function (result) {
            layer.msg("添加失败！");
        }
    });
}