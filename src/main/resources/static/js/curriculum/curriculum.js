/**
 * Created by wanggw on 2017/2/27.
 */
$(function () {

    /**
     * 进入合作商户新增页面
     */
    $("#addMerchant").bind('click', function () {
        window.location.href = "/curriculumController/addCurriculum.html";
    });
    $("#back").on('click', function () {
        window.history.back();
    })
});