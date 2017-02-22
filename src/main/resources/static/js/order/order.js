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
    /*操作*/
    searchObj : {
        title: '',
        pageNum: 1,
        pageSize: 10
    },
    loadAreaSize:['600px', '400px'],//弹出框的宽高

    //带条件查找
    doSearch:function() {
        var sTitle = $.trim($('#ticketName').val());//获取查询条件
        orderPage.searchObj.title= sTitle || '';
        orderPage.searchObj.pageNum = 1;  //reset
        orderPage.renderPages(true);
    },

    /*页面初始化和事件绑定*/
    bindEvents:function() {
        /*update*/
        $(this.el).undelegate()
            .delegate('#search','click',orderPage.doSearch);
    },

    //根据请求获取分页数据
    renderPages:function(isFirst) {
        var url = '/order/orderList.do', isAdd = false;
        var url2 = '/order/myOrder.html';
        if(this.searchObj.title){
            url = url+'?ticketName='+this.searchObj.title;
            isAdd = true;
        }
        if(this.searchObj.pageSize){
            url = isAdd ? url+'&pageSize='+this.searchObj.pageSize : url+'?pageSize='+this.searchObj.pageSize;
            isAdd = true;
        }
        if(this.searchObj.pageSize){
            url = isAdd ? url+'&page='+this.searchObj.pageNum :  url+'?page='+this.searchObj.pageNum;
        }

        getHtmlByUrl({
            url: url2,
            success: function (res) {
                if(isFirst){
                }
            }
        });

        getHtmlByUrl({
            url: url,
            dataType:'html',
            success: function (res) {
                $("#div2").html(res);
                if(true){
                    orderPage.buildPageArea();
                }
            }
        });
    },
    /*end页面初始化和事件绑定*/

    /*分页*/
    doChangePage:function(num) {
        this.searchObj.pageNum = num;
        orderPage.renderPages();
    },
    buildPageArea:function() {
        var pageAllCount = $('#pageAllCount').val(),
            totalPage = Math.ceil(pageAllCount/this.searchObj.pageSize);
        laypage({
            cont: 'page',
            pages: totalPage,
            curr: this.searchObj.pageNum,
            jump: function(obj, first){
                if(!first){
                    orderPage.doChangePage(obj.curr);
                }
            }
        });
    },
    /*end分页*/

    /*页面初始化*/
    init:function() {
        orderPage.renderPages(true);
        orderPage.bindEvents();
    }
    /*end 页面初始化*/
};