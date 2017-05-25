/**
 * Created by wanggw on 2017/2/28.
 */
$(function () {

    //编辑界面保存按钮点击事件 ---  执行保存
    $("#saveMerchant").bind("click", function () {
        doUpdate();
    });
    $("#cancelMerchant").bind("click", function () {
        window.history.back();
    });
    $("#teachers").on("dblclick",function () {
        var contentValue = $(this).val();
        var content = $(this).find("option:selected").text();
        $(this).find("option:selected").remove();
        addOption(content,'Rteachers',contentValue)
    })
    $("#Rteachers").on("dblclick",function () {
        var contentValue = $("#Rteachers").val();
        var content = $("#Rteachers").find("option:selected").text();
        $("#Rteachers").find("option:selected").remove();
        addOption(content,'teachers',contentValue)
    })

    $("#enrollment_required").on("dblclick",function () {
        var contentValue = $(this).val();
        var content = $(this).find("option:selected").text();
        $(this).find("option:selected").remove();
        addOption(content,'Renrollment_required',contentValue)
    })
    $("#Renrollment_required").on("dblclick",function () {
        var contentValue = $(this).val();
        var content = $(this).find("option:selected").text();
        $(this).find("option:selected").remove();
        addOption(content,'enrollment_required',contentValue)
    })
});

function addOption(content,id,contentValue) {
    $("#"+id).append('<option value="'+contentValue+'">'+content+'</option>');
}


//编辑更新数据
function doUpdate() {
    var RteachersOption = $("#Rteachers").find("option");
    var teachers={};
    for ( var i=0;i<RteachersOption.length;i++){
        teachers[$(RteachersOption[i]).val()]=$(RteachersOption[i]).text();
        console.log($(RteachersOption[i]).val());
    }
    var Renrollment_requiredOption = $("#Renrollment_required").find("option");
    var enrollment_required={};
    for ( var i=0;i<Renrollment_requiredOption.length;i++){
        teachers[$(Renrollment_requiredOption[i]).val()]=$(Renrollment_requiredOption[i]).text();
    }
    console.log(JSON.stringify(enrollment_required));

    //准备json数据
    var name = $("#name").val();
    if (!name){
        layer.confirm('课程名称不能为空',{icon: 2,skin: 'layer-ext-moon'});
        return;
    }
    var img_url = $("#img_url").val();
    if (!img_url){
        layer.confirm('课程图片不能为空',{icon: 2,skin: 'layer-ext-moon'});
        return;
    }
    var enrollment_required = $("#enrollment_required").val();
    if (!enrollment_required){
        layer.confirm('报名必填不能为空',{icon: 2,skin: 'layer-ext-moon'});
        return;
    }
    var address = $("#address").val();
    if (!address){
        layer.confirm('地址不能为空',{icon: 2,skin: 'layer-ext-moon'});
        return;
    }
    var phone = $("#phone").val();
    if (!phone){
        layer.confirm('电话不能为空',{icon: 2,skin: 'layer-ext-moon'});
        return;
    }
    if(!isPhone(phone)){
        layer.confirm('请输入正确的联系方式',{icon: 2,skin: 'layer-ext-moon'});
        return;
    }
    var addJson = {};
    addJson.id = $("#id").val();
    addJson.training_institutions_id = $("#training_institutions_id").val();
    addJson.cooperative_merchant_id = $("#cooperative_merchant_id").val();
    addJson.img_url = $("#img_url").val();
    addJson.name = $("#name").val();
    addJson.teachers = JSON.stringify(teachers);
    addJson.enrollment_required = JSON.stringify(enrollment_required);
    addJson.train_type = $("#train_type").val();
    addJson.content = $("#m_content").val();
    addJson.address = $("#address").val();
    addJson.phone = $("#phone").val();
    addJson.enrollment_read = $("#enrollment_read").val();
    // window.introductionEditor.sync();
    // addJson.remark = window.introductionEditor.html();
    $.ajax({
        url: '/curriculumController/doUpdataCurriculum.do',
        type: 'POST', //GET
        data: addJson,
        dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success: function (result) {
            if ("000000" == result.code) {
                layer.msg("添加成功！");
                setTimeout(function () {
                    window.location.href = "/curriculumController/curriculum.html";
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
function isPhone(obj){
    var reg=/^(\d{3,4}\-)?[1-9]\d{6,7}$/;
    var reg2=/^(\+\d{2,3}\-)?\d{11}$/;
    if(reg.test(obj)||reg2.test(obj)){
        return true;
    }else {
        return false;
    }
}