/**
 * Created by wangw on 2017/6/19.
 */
$(function () {

    /**
     * 进入合作商户新增页面
     */
    $("#addMerchant").bind('click', function () {
        window.location.href = "/mainStadiumMerchant/addMerchant.html?mainStadiumId=" + $('#mainStadiumId').val();
    });
    $("#back").on('click', function () {
        window.location.href = "/mainStadiumController/mainstadium.html";
    })
});