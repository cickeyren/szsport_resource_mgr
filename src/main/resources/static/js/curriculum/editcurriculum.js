/**
 * Created by wanggw on 2017/2/28.
 */
$(function () {
    $("#training_institutions_id").change(function () {
        var id = $("#training_institutions_id").val()||"";
        getMerchantList(id);
    });

    $("#cancelMerchant").bind("click", function () {
        backListPage();
    });

    uploadImgInit();
});

function backListPage(){
    window.location.href = "/curriculumController/curriculum.html";
}

function uploadImgInit(){
    $("#previewImg").on('change', function () {
        uploadImg();
    });
}
// 上传图片
function uploadImg(){
    if(!validUploadImgType()){
        return false;
    }
    $.ajaxFileUpload({
        url: '/uploadImg/uploadImg.json',
        secureuri: false, //是否需要安全协议，一般设置为false
        fileElementId: 'previewImg',
        dataType: 'json',
        success: function (data, status)
        {
            uploadImgInit();
            if('OK' == data.status){
                $("#imgImg").attr('src', data.result);
                $("#img_url").val(data.result);
                $("#img_url").valid();
            } else {
                layer.msg("上传失败："+data.message);
            }
        },
        error: function (data, status, e)
        {
            uploadImgInit();
            alert("error:" + e);
        }
    });
}
// 验证类型和大小
function validUploadImgType(){
    var flag = true;
    var filepath=$('#previewImg').val();
    var extStart=filepath.lastIndexOf('.');
    var ext=filepath.substring(extStart,filepath.length).toUpperCase();
    if(ext != '.BMP' && ext!='.PNG' && ext!='.JPG' &&ext!='.JPEG'){
        alert('图片限于bmp,png,gif,jpeg,jpg格式');
        flag = false;
        return false;
    }
    var fileSize = 0;
    var target = document.getElementById("previewImg");
    var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
    var filemaxsize = 1024;//1M
    if (isIE && !target.files) {
        var fileSystem = new ActiveXObject('Scripting.FileSystemObject');
        if(!fileSystem.FileExists(filepath)){
            alert('图片不存在，请重新输入！');
            return false;
        }
        var file = fileSystem.GetFile (filepath);
        fileSize = file.Size;
    } else {
        fileSize = target.files[0].size;
    }
    var size = fileSize / 1024;
    if(size>filemaxsize){
        alert("附件大小不能大于"+filemaxsize/1024+"M！");
        target.value ="";
        flag = false;
    }
    if(size<=0){
        alert("附件大小不能为0M！");
        target.value ="";
        flag = false;
    }
    return flag;
}


function getMerchantList(institutionId) {
    $.ajax({
        url:'/MerchantController/getMerchantByInstitutionId.json',
        type:'POST',
        data:{"institution_id":institutionId},
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(result){
            if("000000" == result.code) {
                var items = result.result;
                $("#cooperative_merchant_id").empty();
                $.each(items,function(i,n){
                    $("#cooperative_merchant_id").append("<option value=\"" + n.id + "\">"+n.merchant_name+"</option>");
                });
            }else {
                layer.msg(result.result);
            }
        },
    })
}


//编辑更新数据
function doUpdate() {
    // var RteachersOption = $("#Rteachers").find("option");
    // var teachers={};
    // for ( var i=0;i<RteachersOption.length;i++){
    //     teachers[$(RteachersOption[i]).val()]=$(RteachersOption[i]).text();
    //     console.log($(RteachersOption[i]).val());
    // }
    // var Renrollment_requiredOption = $("#Renrollment_required").find("option");
    // var enrollment_required={};
    // for ( var i=0;i<Renrollment_requiredOption.length;i++){
    //     teachers[$(Renrollment_requiredOption[i]).val()]=$(Renrollment_requiredOption[i]).text();
    // }
    // console.log(JSON.stringify(enrollment_required));

    //准备json数据
    var addJson = {};
    addJson.id = $("#id").val();
    var name = $("#name").val();
    var img_url = $("#img_url").val();
    addJson.name = $("#name").val();
    addJson.img_url = $("#img_url").val();

    addJson.training_institutions_id = $("#training_institutions_id").val();
    addJson.cooperative_merchant_id = $("#cooperative_merchant_id").val();

    addJson.train_type = $("#train_type").val();
    addJson.content = $("#m_content").val();

    var address = $("#address").val();
    var phone = $("#phone").val();
    addJson.address = $("#address").val();
    addJson.phone = $("#phone").val();

    addJson.enrollment_read = $("#enrollment_read").val();

    $.ajax({
        url: '/curriculumController/doUpdataCurriculum.do',
        type: 'POST',
        data: addJson,
        dataType: 'json',
        success: function (result) {
            if ("000000" == result.code) {
                layer.msg("添加成功！");
                setTimeout(function () {
                    backListPage();
                }, 1000);
            }else {
                layer.msg(result.result);
            }
        },
        error: function (result) {
            layer.msg("添加失败！");
        }
    });
}