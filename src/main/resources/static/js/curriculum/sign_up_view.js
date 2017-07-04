/**
 * Created by wanggw on 2017/2/27.
 */
$(function () {

    $("#back").on('click', function () {
        backListPage();
    });
    $("#training_institutions_id").on('change', function () {
        var id = $(this).val()||"";
        updateCurriculumList(id);
    });
    $("#name").on('change', function () {
        var id = $(this).val()||"";
        updateCurriculumClassList(id);
    });
    $("#query").on('click',function () {
        query();
    })
    query();
});

function query(curr) {
    var data = {};
    data.train_type = $("#train_type").val();
    data.training_institutions_id = $("#training_institutions_id").val();
    data.name = $("#name").val();
    data.class_name = $("#class_name").val();
    data.phone = $("#phone").val();
    data.student_name = $("#student_name").val();

    var pageSize = 10;
    data.pageNum = curr || 1;
    data.pageSize = pageSize;
    $.ajax({
        url: '/curriculumController/getCurriculumOrderHasPay.do',
        type: 'POST', //GET
        data: data,
        dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success: function (result) {
            if ("000000" == result.code) {
                $("#tbody").html("");
                var order = result.result.list;
                var total = result.result.total;
                var temp = "";
                if (order.length > 0) {
                    for (var i = 0; i < order.length; i++) {
                        var lean_time = "";
                        var leantime_type = order[i].leantime_type||"";
                        var lean_time = order[i].lean_time||"";
                        if(leantime_type=="1"){
                            lean_time = "固定："+lean_time;
                        }else if(leantime_type=="2"){
                            lean_time = "常年";
                        }else if(leantime_type=="3"){
                            lean_time = "需预约";
                        }
                        temp += '<tr>' +
                            '<td>' + order[i].name + '</td>' +
                            '<td>' + order[i].org_name + '</td>' +
                            '<td>' + order[i].train_name + '</td>' +
                            '<td>' + order[i].class_name + '</td>' +
                            '<td>' + lean_time + '</td>' +
                            '<td>' + order[i].time + '</td>' +
                            '<td>' + order[i].student_name + '</td>' +
                            '<td>' + order[i].phone + '</td>' +
                            '<td>' + formatDate(order[i].pay_time) + '</td>' +
                            '<td>' + order[i].pay_fee + '</td>' +
                            '</tr>';
                    }
                    $("#tbody").append(temp);
                } else {
                    $("#tbody").append('<tr><td colspan="10">暂无相关数据</td></tr>');
                }
                laypage({
                    cont: 'page', //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>
                    pages: Math.ceil(total / pageSize), //通过后台拿到的总页数
                    curr: curr || 1,
                    first: '首页', //若不显示，设置false即可
                    last: '尾页', //若不显示，设置false即可
                    prev: '上一页', //若不显示，设置false即可
                    next: '下一页', //若不显示，设置false即可
                    jump: function (obj, first) { //触发分页后的回调
                        if (!first) { //点击跳页触发函数自身，并传递当前页：obj.curr
                            query(obj.curr);
                        }
                    }
                });

            } else {
                layer.msg(result.message);
            }
        },
        error: function (result) {
            layer.msg("查询发生了错误！");
        }
    });
}
function formatDate(date) {
    var newDate = new Date();
    if (date) {
        newDate.setTime(date);
        return newDate.format("yyyy-MM-dd hh:mm:ss");
    } else {
        return "-"
    }
}

function updateCurriculumList(institution_id) {
    $.ajax({
        url:'/curriculumController/getSlCurriculumList.do',
        type:'POST', //GET
        data:{"institution_id":institution_id},
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(result){
            if("000000" == result.code) {
                var items = result.result;
                $("#name").empty();
                $("#name").append("<option value=''>全部</option>");
                $.each(items,function(i,n){
                    $("#name").append("<option value=\"" + n.id + "\">"+n.name+"</option>");
                });
            }else {
                layer.msg(result.message);
            }
        },
    });
}

function updateCurriculumClassList(curriculum_id) {
    $.ajax({
        url:'/curriculumController/getSlCurriculumClassList.do',
        type:'POST', //GET
        data:{"curriculum_id":curriculum_id},
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(result){
            if("000000" == result.code) {
                var items = result.result;
                $("#class_name").empty();
                $("#class_name").append("<option value=''>全部</option>");
                $.each(items,function(i,n){
                    $("#class_name").append("<option value=\"" + n.id + "\">"+n.name+"</option>");
                });
            }else {
                layer.msg(result.message);
            }
        },
    });
}

function backListPage(){
    window.location.href = "/curriculumController/curriculum.html";
}