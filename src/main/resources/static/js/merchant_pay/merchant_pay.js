/**
 * Created by wanggw on 2017/2/28.
 */
$(function () {

    /**
     * 进入合作商户新增页面
     */
    $("#addMerchant").bind('click', function () {
        window.location.href = "/MerchantPayController/add.html?mainstadium_id=" + $('#mainstadium_id').val()+"&merchantId="+$('#merchantId').val();
    });
    $("#back").bind('click', function () {
        window.location.href = "/MerchantController/merchant.html?mainstadium_id=" + $('#mainstadium_id').val()+"&merchantId="+$('#merchantId').val();
    });
});