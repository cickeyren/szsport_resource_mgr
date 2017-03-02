/**
 * Created by wanggw on 2017/3/1
 */

$(function () {

    //新增界面保存按钮点击事件 ---  执行保存
    $("#save").on("click", function () {
        doAdd();
    });

    //新增界面保存按钮点击事件 ---  返回主界面
    $("#cancel").on("click", function () {
        window.location.href = "/MerchantPayController/merchant_pay.html?mainstadium_id=" + $('#mainstadium_id').val()+"&merchantId="+$('#merchantId').val();
    });

})


//新增页面添加数据
function doAdd() {
    //准备json数据
    var addJson = {};

    addJson.payWay = $('input[name="pay_way"]:checked').val();//支付方式

    /**
     * 支付宝添加数据
     */
    if(addJson.payWay=="1"){

        addJson.sellerId = $("#seller_id").val();
        addJson.payKey = $("#pay_key").val();
        addJson.partnerId = $("#partner_id").val();
        addJson.appId = $("#app_id").val();
        addJson.signType = $("#sign_type").val();
        addJson.mainstadium_id = $("#mainstadium_id").val();
        addJson.merchantId = $("#merchantId").val();

        $.ajax({
            url: '/MerchantPayController/add.do?mainstadiumId='+$("#mainstadium_id").val(),
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