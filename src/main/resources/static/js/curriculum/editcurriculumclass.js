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
        layer.open({
            type: 1,
            skin: 'layui-layer-demo', //样式类名
            shift: 2,
            title: '上课时间段',
            shade: false,
            closeBtn: 0,
            area: ['400px', '290px'], //宽高
            content: $('#addTimeDialogs'),
            btn: ['确定', '取消'],
            btn1: function () {
                var temp = "";
                temp += '<tr>' +
                    '<td>' + $("#time").val() + '</td>' +
                    '<td>' + $("#max_people").val() + '</td>' +
                    '<td>' + $("#reserve_people").val() + '</td>' +
                    '<td width="80px"><a onclick="editTime(this)">编辑</a>&nbsp;<a onclick="delTime(this)">删除</a></td>' +
                    '</tr>'
                $("#class_time_table_body").append(temp);
                layer.closeAll();
            }
        });
    })
})

function editTime(a) {
    var tds = $(a).parent().prevAll();
    $("#time").val($(tds[2]).text())
    $("#max_people").val($(tds[1]).text())
    $("#reserve_people").val($(tds[0]).text())
    var id = $(a).parent().parent("tr").attr("value");
    layer.open({
        type: 1,
        skin: 'layui-layer-demo', //样式类名
        shift: 2,
        title: '上课时间段',
        shade: false,
        closeBtn: 0,
        area: ['400px', '290px'], //宽高
        content: $('#addTimeDialogs'),
        btn: ['确定', '取消'],
        btn1: function () {
            var re_time = $("#time").val();
            var re_max_people = $("#max_people").val();
            var re_reserve_people = $("#reserve_people").val();
            $(tds[2]).text(re_time);
            $(tds[1]).text(re_max_people);
            $(tds[0]).text(re_reserve_people);
            $.ajax({
                url: '/curriculumController/doUpdataCurriculumClassTime.do',
                type: 'POST', //GET
                data: {
                    id:id,
                    time:$("#time").val(),
                    max_people: $("#max_people").val(),
                    reserve_people:$("#reserve_people").val()
                },
                dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
                success: function (result) {
                    layer.closeAll();
                    if ("000000" == result.code) {
                        layer.msg("修改成功！");
                    } else {
                        layer.alert(result.result);
                    }
                },
                error: function (result) {
                    layer.msg("添加失败！");
                }
            });
        }
    });
}
function delTime(a) {
    var id =$(a).parent().parent("tr").attr("value")
    if (!id) {
        $(a).parent().parent("tr").remove()
    } else {
        delTimess(id,a)
    }
}
function delTimess(id,a) {
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
                    $(a).parent().parent("tr").remove();
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
    // var timessOp = $("#timess").find("option");
    // var timess = [];
    // for (var i = 0; i < timessOp.length; i++) {
    //     if (!$(timessOp[i]).attr("value")) {
    //         timess.push($(timessOp[i]).text());
    //     }
    // }
    var timessOp = $("#class_time_table_body").find("tr");
    var timess = [];//上课时段
    for (var i = 0; i < timessOp.length; i++) {
        var times = {};
        var tds = $(timessOp[i]).find("td");
        times.id = $(timessOp[i]).attr("value");
        times.time=$(tds[0]).text();//上课时段
        times.max_people=$(tds[1]).text();//时段最大人数
        times.reserve_people=$(tds[2]).text();//时段预留人数
        timess.push(times);
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