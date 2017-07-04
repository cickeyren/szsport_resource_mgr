/**
 * Created by wanggw on 2017/2/27.
 */
$(function () {

    var layerOpts = {shift: -1, time: 1000};

    /**
     * 进入合作商户新增页面
     */
    $("#addMerchant").bind('click', function () {
        window.location.href = "/curriculumController/addCurriculum.html";
    });
    $("#back").on('click', function () {
        backListPage();
    })
    $(".online").on('click',function () {
        var id = $(this).attr("data");
        var status = $(this).attr("status");
        $.ajax({
            url: '/curriculumController/curriculumUpline.do',
            type: 'POST', //GET
            data: {
                curriculumId:id,
                status:status
            },
            dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            success: function (result) {
                if ("000000" == result.code) {
                    layer.msg("操作成功", layerOpts, function(){
                        backListPage();
                    });
                }else {
                    layer.msg(result.message);
                }
            },
            error: function (result) {
                layer.msg("添加失败！");
            }
        });
    })
    $(".recommend").on("click",function () {
        var id = $(this).attr("data");
        var recommend = $(this).attr("recommend");
        $.ajax({
            url: '/curriculumController/recommend.do',
            type: 'POST', //GET
            data: {
                curriculumId:id,
                recommend:recommend
            },
            dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            success: function (result) {
                if ("000000" == result.code) {
                    layer.msg("操作成功", layerOpts, function(){
                        backListPage();
                    });
                }else {
                    layer.msg(result.message);
                }
            },
            error: function (result) {
                layer.msg("添加失败！");
            }
        });
    })
});

function backListPage(){
    window.location.href = "/curriculumController/curriculum.html";
}