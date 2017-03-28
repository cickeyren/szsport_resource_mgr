/**
 * Created by wanggw on 2017/3/1
 */

$(function () {

    //编辑界面保存按钮点击事件 ---  执行保存
    $("#saveEdit").bind("click", function () {
        doUpdate();
    });

    //编辑界面保存按钮点击事件 ---  返回主界面
    $("#cancelEdit").bind("click", function () {
        window.location.href = "/MerchantPayController/merchant_pay.html?mainstadium_id=" + $('#mainstadium_id').val()+"&merchantId="+$('#merchantId').val();
    });

});

//编辑更新数据  (根据不同的支付方式调用不同的接口更新数据)
function doUpdate() {
    //准备json数据
    var addJson = {};

    addJson.payWay = $('input[name="pay_way"]:checked').val();//支付方式

    /**
     * 支付宝更新数据
     */
    if(addJson.payWay=="1"){
        addJson.id = $('input[id="id"]').val();
        addJson.sellerId = $("#seller_id").val();
        addJson.payKey = $("#pay_key").val();
        addJson.partnerId = $("#partner_id").val();
        addJson.appId = $("#app_id").val();
        addJson.signType = $("#sign_type").val();
        addJson.mainstadium_id = $("#mainstadium_id").val();
        addJson.merchantId = $("#merchantId").val();

        $.ajax({
            url: '/MerchantPayController/updateByzhifubao.do',
            type: 'POST', //GET
            data: addJson,
            // contentType:'application/json',
            dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            success: function (result) {
                if ("000000" == result.code) {
                    layer.msg(result.result);
                    setTimeout(function () {
                        window.location.href = "/MerchantPayController/merchant_pay.html?mainstadium_id=" + $('#mainstadium_id').val()+"&merchantId="+$('#merchantId').val();
                    }, 3000);
                }else {
                    layer.alert(result.result);
                }
            },
            error: function (result) {
                layer.msg(result.result);
            }
        });

    }else if (addJson.payWay=="2"){//微信
        layer.alert("该功能正在开发中");
        setTimeout(function () {
            window.location.href = "/MerchantPayController/merchant_pay.html?mainstadium_id=" + $('#mainstadium_id').val()+"&merchantId="+$('#merchantId').val();
        }, 3000);
    }else if (addJson.payWay=="3"){//银联卡
        layer.alert("该功能正在开发中");
        setTimeout(function () {
            window.location.href = "/MerchantPayController/merchant_pay.html?mainstadium_id=" + $('#mainstadium_id').val()+"&merchantId="+$('#merchantId').val();
        }, 3000);
    }else {//银行卡
        layer.alert("该功能正在开发中");
        setTimeout(function () {
            window.location.href = "/MerchantPayController/merchant_pay.html?mainstadium_id=" + $('#mainstadium_id').val()+"&merchantId="+$('#merchantId').val();
        }, 3000);
    }
}
