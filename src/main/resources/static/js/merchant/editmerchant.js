/**
 * Created by wanggw on 2017/2/28.
 */
$(function () {

    //编辑界面保存按钮点击事件 ---  执行保存
    $("#saveEditMerchant").bind("click", function () {
        doUpdate();
    });

    //编辑界面保存按钮点击事件 ---  返回主界面
    $("#cancelEditMerchant").bind("click", function () {
        window.location.href = "/MerchantController/merchant.html?mainstadium_id=" + $('#mainstadium_id').val();
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
});


//编辑更新数据
function doUpdate() {
    //准备json数据
    var addJson = {};
    addJson.id = $('input[id="id"]').val();
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
    addJson.mainstadiumId = $("#mainstadium_id").val();
    getHtmlByUrl({
        type: 'POST',
        url: '/MerchantController/updatemerchant.do',
        data: addJson,
        success: function (result) {
            if ("000000" == result.code) {
                layer.msg("编辑数据成功！");
                window.location.href = "/MerchantController/merchant.html?mainstadium_id=" + $('#mainstadium_id').val();
            }
            //console.log(result);
        },
        error: function (result) {
            layer.msg("编辑数据失败！");
        }
    });
}