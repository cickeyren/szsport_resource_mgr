/**
 * Created by admin on 2017/3/2.
 */
$(function () {

    /**
     * 进入合作商户新增页面
     */
    $("#addMerchant").bind('click', function () {
        window.location.href = "/MerchantUserController/add.html?mainstadium_id=" + $('#mainstadium_id').val()+"&merchant_id="+$('#merchant_id').val();
    });
});