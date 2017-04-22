/**
 * Created by wanggw on 2017/2/27.
 */

$(function () {

    //新增界面保存按钮点击事件 ---  执行保存
    $("#saveMerchant").on("click", function () {
        doAdd();
    });

    //新增界面保存按钮点击事件 ---  返回主界面
    $("#cancelMerchant").on("click", function () {
        window.location.href = "/MerchantController/add.html?mainstadium_id=" + $('#mainstadium_id').val();
    });

    /**
     * 省区发生改变之后
     */
    $("#provincial_level").change(function () {
        updateprovinceList($("#provincial_level").val());
    })

    /**
     * 市区发生改变之后
     */
    $("#city_level").change(function () {
        updatecityList($("#city_level").val());
    })

    //初始化富文本编辑器(该代码需放在所有事件初始化最后执行) -- 场地介绍
    KindEditor.options.filterMode = false;
    var fieldDescriptionEditor;
    KindEditor.ready(function (K) {
        fieldDescriptionEditor = K.create('textarea[name="remark"]', {
            resizeType: 1,
            allowPreviewEmoticons: false,
            filterMode: false
        });

        window.introductionEditor = fieldDescriptionEditor;
    });
})


//新增页面添加数据
function doAdd() {
    //准备json数据
    var addJson = {};
    addJson.merchantName = $("#merchant_name").val();
    addJson.provincialLevel = $("#provincial_level").val();
    addJson.cityLevel = $("#city_level").val();
    addJson.districtLevel = $("#district_level").val();
    addJson.address = $("#address").val();
    addJson.contacts = $("#contacts").val();
    addJson.telephone = $("#telephone").val();
    addJson.settlementTime = $("#settlementTime").val();
    addJson.cooperationWay = $('input[name="cooperation_way"]:checked').val();
    addJson.settlementWay = $('input[name="settlement_way"]:checked').val();
    addJson.status = $('input[name="status"]:checked').val();
    window.introductionEditor.sync();
    addJson.remark = window.introductionEditor.html();

    $.ajax({
        url: '/MerchantController/addMerchant.do?mainstadiumId='+$("#mainstadium_id").val(),
        type: 'POST', //GET
        data: addJson,
        // contentType:'application/json',
        dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success: function (result) {
            if ("000000" == result.code) {
                layer.msg("添加成功！");
                setTimeout(function () {
                    window.location.href = "/MerchantController/merchant.html?mainstadium_id=" + $('#mainstadium_id').val();
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

function updateprovinceList(provinceID) {
    $.ajax({
        url:'/mainStadiumController/getCityByID.do',
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
                layer.alert(result.result);
            }
        },
    })
}

function updatecityList(cityID) {
    $.ajax({
        url:'/mainStadiumController/getAreaByID.do',
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
                layer.alert(result.result);
            }
        },
    })

}