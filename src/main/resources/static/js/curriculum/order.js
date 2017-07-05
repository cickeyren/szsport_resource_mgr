$(function () {
    /**
     * 取消
     */
    $("#back").on('click', function () {
        window.location.href = "/curriculumController/orderList.html";
    });
    /**
     * 重置查詢條件
     */
    $("#resetBtn").on('click', function () {
        window.location.href = "/curriculumController/orderList.html";
    });
});
