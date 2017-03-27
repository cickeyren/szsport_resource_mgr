$(function () {

    /**
     * 进入子场馆场地新增页面
     */
    $("#addSubField").bind('click', function () {
        window.location.href = "/FieldController/add.html?subStadiumid=" + $('#subStadiumid').val();
    });

    $('a[name="operate"]').bind('click', function () {
        doDeleteField(this);
    });
});

/**
 * 删除
 */
function doDeleteField(field) {

    var $this = $(field),
        $parent = $this.parent().parent(),
        id = $parent.find('input[type="hidden"]').val();
    var addJson = {};
    addJson.id = id;
    layer.confirm('请确认是否删除？', {
        btn: ['是', '否'] //按钮
    }, function () {
        $.ajax({
            url: '/FieldController/deleteSubField.do',
            type: 'POST', //GET
            data: addJson,
            dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            success: function (result) {
                if ("000000" == result.code) {
                    layer.msg("删除成功！");
                    setTimeout(function () {
                        window.location.href = "/FieldController/subField.html?subStadiumid=" + $('#subStadiumid').val();
                    }, 1000);
                }else {
                    layer.alert(result.result);
                }
            },
            error: function (result) {
                layer.msg("删除成功！");
            }
        });
    });


}


