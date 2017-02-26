/*
 子场馆时段管理主界面JS
 王功文 2017-02-26
 */
$(function () {

    //新增时段按钮点击事件
    $("#addTimeFrame").bind("click", function () {
        window.location.href = "/TimeFrameController/addTimeFrame.html?stadium_id=" + $("#stadium_id").val();
    });

    $('a[name="invalid"]').bind('click', function () {

        doInvalid(this);

    });

});


/**
 * 删除
 */
function doInvalid(field) {

    var $this = $(field),
        $parent = $this.parent().parent(),
        id = $parent.find('input[type="hidden"]').val();
    var addJson = {};
    addJson.id = id;
    layer.confirm('请确认是否作废？', {
        btn: ['是', '否'] //按钮
    }, function () {
        $.ajax({
            url: '/TimeFrameController/invalid.do',
            type: 'POST', //GET
            data: addJson,
            dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            success: function (result) {
                if ("000000" == result.code) {
                    layer.msg("作废成功！");
                    setTimeout(function () {
                        window.location.href = "/TimeFrameController/timeFrame.html?stadium_id=" + $('#stadium_id').val();
                    }, 1000);
                }
            },
            error: function (result) {
                layer.msg("作废失败！");
            }
        });
    });
}