var orderPage;
$(function () {
    orderPage = new orderPageObj();
    orderPage.init();

});
function orderPageObj() {
    /*基础配置*/
    this.el = 'body';
    this.addLay = null;
    this.updateLay = null;
    this.validateCong = {};
    /*end 基础配置*/
}
orderPageObj.prototype = {

    /*页面初始化*/
    init:function() {
        orderPage.renderPages(true);
        orderPage.bindEvents();
    }
    /*end 页面初始化*/
};