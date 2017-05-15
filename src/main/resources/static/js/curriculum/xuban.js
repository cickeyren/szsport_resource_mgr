/**
 * Created by wanggw on 2017/2/27.
 */

$(function () {

    //新增界面保存按钮点击事件 ---  执行保存
    $("#saveXuban").on("click", function () {
        doAdd();
    });

    //新增界面保存按钮点击事件 ---  返回主界面
    $("#cancelMerchant").on("click", function () {
        window.history.back();
        // window.location.href = "/MerchantController/merchant.html?mainstadium_id=" + $('#mainstadium_id').val();
    });
    $("#curriculum").on("dblclick",function () {
        var contentValue = $(this).val();
        var content = $(this).find("option:selected").text();
        $(this).find("option:selected").remove();
        addOption(content,'Rcurriculum',contentValue)
    })
    $("#Rcurriculum").on("dblclick",function () {
        var contentValue = $(this).val();
        var content = $(this).find("option:selected").text();
        $(this).find("option:selected").remove();
        addOption(content,'curriculum',contentValue)
    })
})
function addOption(content,id,contentValue) {
    $("#"+id).append('<option value="'+contentValue+'">'+content+'</option>');
}
//新增页面添加数据
function doAdd() {
    var RcurriculumOption = $("#Rcurriculum").find("option");
    var curriculum=[];
    for ( var i=0;i<RcurriculumOption.length;i++){
        curriculum.push($(RcurriculumOption[i]).val())
    }
    //准备json数据
    var addJson = {};
    addJson.id = $("#class_id").val();
    addJson.discount_fee = $("#discount_fee").val();
    addJson.xuban_curriculum = JSON.stringify(curriculum);
    $.ajax({
        url: '/curriculumController/xubanCurriculum.do',
        type: 'POST', //GET
        data: addJson,
        dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success: function (result) {
            if ("000000" == result.code) {
                layer.msg("添加成功！");
                setTimeout(function () {
                    window.location.href = "/curriculumController/curriculumClass.html?curriculumId=" + $('#curriculumId').val();
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