$(function () {

    //保存按钮点击事件
    $("#save").bind('click', function () {
        doSave(this);
    });


    //取消按钮
    $("#cancel").bind('click', function () {
        window.location.href = "/TimeFrameController/timeFrame.html?stadium_id=" + $("#stadium_id").val();
    });

});


/**
 * 保存方法
 *
 * @param field
 */
function doSave(field) {

    //取出参数
    var addJson = {};
    addJson.timeName = $("#time_name").val();
    addJson.timeLength = $("#time_length").val();
    addJson.timeStart = $("#time_start").val();
    addJson.num = $("#num").val();
    addJson.timeLag = $("#time_lag").val();
    addJson.id = $("#id").val();

    $.ajax({
        url: '/TimeFrameController/updateTimeFrame.do',
        type: 'POST', //GET
        data: addJson,
        // contentType:'application/json',
        dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success: function (result) {
            if ("000000" == result.code) {
                layer.msg("更新成功！");
                setTimeout(function () {
                    window.location.href = "/TimeFrameController/timeFrame.html?stadium_id=" + $('#stadium_id').val();
                }, 1000);
            }else {
                layer.alert(result.result);
            }
        },
        error: function (result) {
            layer.msg("更新成功！");
        }
    });
}