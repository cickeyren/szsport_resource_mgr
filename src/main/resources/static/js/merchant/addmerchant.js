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
            }
        },
        error: function (result) {
            layer.msg("添加失败！");
        }
    });
}