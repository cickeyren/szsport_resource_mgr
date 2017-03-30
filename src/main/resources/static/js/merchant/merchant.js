/**
 * Created by wanggw on 2017/2/27.
 */
$(function () {

    /**
     * 进入合作商户新增页面
     */
    $("#addMerchant").bind('click', function () {
        window.location.href = "/MerchantController/add.html?mainstadium_id=" + $('#mainstadium_id').val();
    });
    $("#back").on('click', function () {
        window.location.href = "/mainStadiumController/mainstadium.html";
    })
});