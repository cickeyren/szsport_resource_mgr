
$(function () {

    //新增界面保存按钮点击事件 ---  执行保存
    $("#save").on("click", function () {
        doAdd();
    });

    //新增界面保存按钮点击事件 ---  返回主界面
    $("#cancel").on("click", function () {
        window.location.href = "/MerchantUserController/merchantuser.html?mainstadium_id=" + $('#mainstadium_id').val()+"&merchantId="+$("#merchant_id").val();
    });

})

$("#merchant_id").val()
//新增页面添加数据
function doAdd() {
    //准备json数据
    var addJson = {};
    addJson.userName = $("#user_name").val();
    addJson.userTel = $("#user_tel").val();
    addJson.userPwd = $("#user_pwd").val();
    addJson.descride = $("#descride").val();
    addJson.adminRole =$("#admin_role").val();
    addJson.status = $('input[name="status"]:checked').val();
    addJson.merchantId = $("#merchant_id").val();

    $.ajax({
        url: '/MerchantUserController/add.do',
        type: 'POST', //GET
        data: addJson,
        // contentType:'application/json',
        dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success: function (result) {
            if ("000000" == result.code) {
                layer.msg(result.result);
                setTimeout(function () {
                    window.location.href = "/MerchantUserController/merchantuser.html?mainstadium_id=" + $('#mainstadium_id').val()+"&merchantId="+$("#merchant_id").val();
                }, 2000);
            }
        },
        error: function (result) {
            layer.msg(result.result);
        }
    });
}