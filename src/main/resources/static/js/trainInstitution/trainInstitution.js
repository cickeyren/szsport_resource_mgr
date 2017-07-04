$(function () {



    $("#searchBtn").click(function () {
        $("#myPage").val("0");
        $("#searchForm").submit();
    });
    /**
     * 重置查詢條件
     */
    $("#resetBtn").on('click', function () {
        $("#name").val("")
        window.location.href = "/trainInstitutionController/list.html";
    });

    /**
     * 进入新增页面
     */
    $("#addBtn").on('click', function () {
        window.location.href = "/trainInstitutionController/add.html";
    });

    /**
     * 省区发生改变之后
     */
    $("#provincial_level").change(function () {
        updateprovinceList($("#provincial_level").val());
    });

    /**
     * 市区发生改变之后
     */
    $("#city_level").change(function () {
        updatecityList($("#city_level").val());
    });

    uploadImgInit();

    /**
     * 返回列表页面
     */
    $("#cancelBtn").on('click', function () {
        window.location.href = "/trainInstitutionController/list.html";
    });
});
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
                $("#logoImg").attr('src', data.result);
                $("#logo_url").val(data.result)
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

//新增页面添加数据
function doAdd() {
    var addJson = {};
    addJson.name = $("#name").val();
    addJson.provincial_level = $("#provincial_level").val();
    addJson.city_level = $("#city_level").val();
    addJson.district_level = $("#district_level").val();

    addJson.address = $("#address").val();
    addJson.telephone = $("#telephone").val();

    addJson.logo_url = $("#logo_url").val();

    addJson.lng = $("#lng").val();
    addJson.lat = $("#lat").val();

    window.introductionEditor.sync();
    addJson.introduction = window.introductionEditor.html();

    addJson.status = $('input[name="status"]:checked').val();

    getHtmlByUrl({
        type: 'POST',
        url: '/trainInstitutionController/addTrainInstitution.do',
        data: addJson,
        success: function (result) {
            if ("000000" == result.code) {
                layer.msg("添加成功！");
                setTimeout(function () {
                    window.location.href = "/trainInstitutionController/list.html";
                },3000)

            }else {
                layer.msg(result.message);
            }
        },
        error: function (result) {
            layer.msg("添加失败！");
        }
    });
}

//编辑更新数据
function doUpdate() {
    var addJson = {};
    addJson.id = $('input[id="id"]').val();
    addJson.name = $("#name").val();
    addJson.provincial_level = $("#provincial_level").val();
    addJson.city_level = $("#city_level").val();
    addJson.district_level = $("#district_level").val();
    addJson.address = $("#address").val();
    addJson.telephone = $("#telephone").val();

    addJson.img_url = $("#img_url").val();

    addJson.lng = $("#lng").val();
    addJson.lat = $("#lat").val();

    window.introductionEditor.sync();
    addJson.introduction = window.introductionEditor.html();

    addJson.status = $('input:radio[name="status"]:checked').val();
    getHtmlByUrl({
        type: 'POST',
        url: '/trainInstitutionController/updateTrainInstitution.do',
        data: addJson,
        success: function (result) {
            if ("000000" == result.code) {
                layer.msg("编辑数据成功！");
                setTimeout(function () {
                    window.location.href = "/trainInstitutionController/list.html";
                },3000)
            }else {
                layer.msg(result.message);
            }
        },
        error: function (result) {
            layer.msg("编辑数据失败！");
        }
    });
}


function updateprovinceList(provinceID) {
    $.ajax({
        url:'/commonController/getCityByID.do',
        type:'POST', //GET
        data:{"provinceID":provinceID},
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(result){
            if("000000" == result.code) {
                var items = result.result;
                $("#city_level").empty();
                $.each(items,function(i,n){
                    $("#city_level").append("<option value=\"" + n.cityID + "\">"+n.city+"</option>");
                });
                var cityID = $("#city_level").val();
                updatecityList(cityID);
            }else {
                layer.msg(result.message);
            }
        },
    })
}

function updatecityList(cityID) {
    $.ajax({
        url:'/commonController/getAreaByID.do',
        type:'POST', //GET
        data:{"cityID":cityID},
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(result){
            if("000000" == result.code) {
                var items = result.result;
                $("#district_level").empty();
                $.each(items,function(i,n){
                    $("#district_level").append("<option value=\"" + n.areaID + "\">"+n.area+"</option>");
                });
            }else {
                layer.msg(result.message);
            }
        },
    })

}


