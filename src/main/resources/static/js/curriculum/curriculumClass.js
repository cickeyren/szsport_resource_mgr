/**
 * Created by wanggw on 2017/2/27.
 */
$(function () {

    /**
     * 进入合作商户新增页面
     */
    $("#addClass").on('click', function () {
        window.location.href = "/curriculumController/addCurriculumClass.html?curriculumId=" + $('#curriculumId').val();
    });
    $("#back").on('click', function () {
        window.location.href = "/curriculumController/curriculum.html";
    })
});