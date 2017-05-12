/**
 * Created by wanggw on 2017/2/27.
 */

$(function () {

    //新增界面保存按钮点击事件 ---  执行保存
    $("#saveMerchant").on("click", function () {
        doUpdate();
    });

    //新增界面保存按钮点击事件 ---  返回主界面
    $("#cancelMerchant").on("click", function () {
        window.history.back();
    });
    $("#addtime").on("click", function () {
        var tt = $("#time").val();
        $("#timess").append('<option value="">' + tt + '</option>');
    })
    //删除时间段（状态变化）
    $("#deltimess").on("click", function () {
        var contentValue = $("#timess").val()[0];
        if (!contentValue) {
            $("#timess").find("option:selected").remove();
        } else {
            delTimess(contentValue)
        }
    })
})
function delTimess(id) {
    $.ajax({
        url: '/curriculumController/delTimess.do',
        type: 'POST', //GET
        data: {id: id},
        dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success: function (result) {
            console.log(result);
            if ("000000" == result.code) {
                if(result.result){
                    layer.msg("删除成功！");
                    $("#timess").find("option:selected").remove();
                }else {
                    layer.msg("删除失败！");
                }
            } else {
                layer.alert(result.result);
            }
        },
        error: function (result) {
            layer.msg("操作失败！");
        }
    })
}
//新增页面添加数据
function doUpdate() {
    var timessOp = $("#timess").find("option");
    var timess = [];
    for (var i = 0; i < timessOp.length; i++) {
        if (!$(timessOp[i]).attr("value")) {
            timess.push($(timessOp[i]).text());
        }
    }
    console.log(timess);
    //准备json数据
    var addJson = {};
    addJson.id = $("#id").val();
    addJson.name = $("#name").val();
    addJson.class_long = $("#class_long").val();
    addJson.class_times = $("#class_times").val();
    addJson.student_num = $("#student_num").val();
    addJson.max_num = $("#max_num").val();
    addJson.lean_time = $("#lean_time").val();
    addJson.leantime_type = $('input[name="leantime_type"]:checked').val();
    addJson.bm_time = $("#bm_time").val();
    addJson.bm_end = $("#bm_end").val();
    addJson.target = $("#target").val();
    addJson.content = $("#content").val();
    addJson.fee_code = $("#fee_code").val();
    addJson.fee = $("#fee").val();
    addJson.fee_mark = $("#fee_mark").val();
    addJson.curriculum_id = $("#curriculumId").val();
    addJson.timess = JSON.stringify(timess);
    // window.introductionEditor.sync();
    // addJson.remark = window.introductionEditor.html();
    $.ajax({
        url: '/curriculumController/doUpdataCurriculumClass.do',
        type: 'POST', //GET
        data: addJson,
        dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success: function (result) {
            if ("000000" == result.code) {
                layer.msg("添加成功！");
                setTimeout(function () {
                    window.location.href = "/curriculumController/curriculumClass.html?curriculumId=" + $('#curriculumId').val();;
                }, 1000);
            } else {
                layer.alert(result.result);
            }
        },
        error: function (result) {
            layer.msg("添加失败！");
        }
    });
}

function updateprovinceList(provinceID) {
    $.ajax({
        url: '/mainStadiumController/getCityByID.do',
        type: 'POST', //GET
        data: {"provinceID": provinceID},
        dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success: function (result) {
            if ("000000" == result.code) {
                var items = result.result;
                $("#city_level").empty();
                $.each(items, function (i, n) {
                    $("#city_level").append("<option value=\"" + n.cityID + "\">" + n.city + "</option>");
                });
                var cityID = $("#city_level").val();
                updatecityList(cityID);
            } else {
                layer.alert(result.result);
            }
        },
    })
}

function updatecityList(cityID) {
    $.ajax({
        url: '/mainStadiumController/getAreaByID.do',
        type: 'POST', //GET
        data: {"cityID": cityID},
        dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success: function (result) {
            if ("000000" == result.code) {
                var items = result.result;
                $("#district_level").empty();
                $.each(items, function (i, n) {
                    $("#district_level").append("<option value=\"" + n.areaID + "\">" + n.area + "</option>");
                });
            } else {
                layer.alert(result.result);
            }
        },
    })

}