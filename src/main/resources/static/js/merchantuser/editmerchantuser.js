/**
 * create by wanggw 2017/3/6
 */
$(function () {

    //编辑界面保存按钮点击事件 ---  执行保存
    $("#saveEdit").bind("click", function () {
        doUpdate();
    });

    //编辑界面保存按钮点击事件 ---  返回主界面
    $("#cancelEdit").bind("click", function () {
        //http://localhost:8080/MerchantUserController/merchantuser.html?mainstadium_id=1002&merchantId=00002
        window.location.href = "/MerchantUserController/merchantuser.html?mainstadium_id=" + $('#mainstadium_id').val()+"&merchantId="+$("#merchant_id").val();
    });

});


//编辑更新数据
function doUpdate() {
    //准备json数据
    var addJson = {};
    addJson.id = $('input[id="id"]').val();
    addJson.userName = $("#user_name").val();
    addJson.userTel = $("#user_tel").val();
    addJson.userPwd = $("#user_pwd").val();
    addJson.descride = $("#descride").val();
    addJson.adminRole =$("#admin_role").val();
    addJson.status = $('input[name="status"]:checked').val();
    addJson.merchantId = $("#merchant_id").val();
    getHtmlByUrl({
        type: 'POST',
        url: '/MerchantUserController/update.do',
        data: addJson,
        success: function (result) {
            if ("000000" == result.code) {
                layer.msg(result.result);
                window.location.href = "/MerchantUserController/merchantuser.html?mainstadium_id=" + $('#mainstadium_id').val()+"&merchantId="+$("#merchant_id").val();
            }else {
                layer.alert(result.result);
            }
        },
        error: function (result) {
            layer.msg(result.result);
        }
    });
}