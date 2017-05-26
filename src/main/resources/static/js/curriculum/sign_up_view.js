/**
 * Created by wanggw on 2017/2/27.
 */
$(function () {

    /**
     * 进入合作商户新增页面
     */
    $("#addMerchant").bind('click', function () {
        // window.location.href = "/curriculumController/addCurriculum.html";
    });
    $("#back").on('click', function () {
        window.history.back();
    });
    $("#query").on('click',function () {
        query();
    })
    query();
});

function query(curr) {
    var pageSize = 10;
    var data = {};
    data.name = $("#name").val();
    data.training_institutions_id = $("#training_institutions_id").val();
    data.class_name = $("#class_name").val();
    data.train_type = $("#train_type").val();
    data.phone = $("#phone").val();
    data.student_name = $("#student_name").val();
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
                        console.log(order[i]);
                        console.log(order[i].name);
                        temp += '<tr>' +
                            '<td>' + order[i].name + '</td>' +
                            '<td>' + order[i].training_institutions_id + '</td>' +
                            '<td>' + order[i].train_name + '</td>' +
                            '<td>' + order[i].class_name + '</td>' +
                            '<td>' + order[i].lean_time + '</td>' +
                            '<td>' + order[i].time + '</td>' +
                            '<td>' + order[i].student_name + '</td>' +
                            '<td>' + order[i].phone + '</td>' +
                            '<td>' + formatDate(order[i].pay_time) + '</td>' +
                            '<td>' + order[i].pay_fee + '</td>' +
                            '</tr>';
                    }
                    $("#tbody").append(temp);
                } else {
                    $("#tbody").append('<tr>没有数据</tr>');
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
                layer.alert(result.result);
            }
        },
        error: function (result) {
            console.log(result);
            layer.msg("添加失败！");
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