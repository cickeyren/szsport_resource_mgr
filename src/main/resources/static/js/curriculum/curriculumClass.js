/**
 * Created by wanggw on 2017/2/27.
 */
$(function () {

    /**
     * 进入合作商户新增页面
     */
    $("#addClass").on('click', function () {
        window.location.href = "/curriculumController/addCurriculumClass.html?curriculumId=" + $('#curriculumId').val();
    });
    $("#back").on('click', function () {
        window.location.href = "/curriculumController/curriculum.html";
    })
});

function xuban() {

    layer.open({
        type: 1,
        skin: 'layui-layer-demo', //样式类名
        shift: 2,
        title: '上课时间段',
        shade: false,
        closeBtn: 0,
        area: ['400px', '290px'], //宽高
        content: $('#xubanDialogs'),
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