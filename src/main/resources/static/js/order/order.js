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
        //获取查询条件
        var orderStartDate = $.trim($('#orderStartDate').val());
        var orderEndDate = $.trim($('#orderEndDate').val());
        var userTel = $.trim($('#userTel').val());
        var ticketName = $.trim($('#ticketName').val());
        var ticketType = "";
        $('#ticketType option').each(function(){
            if ($(this).prop("selected")) {
                ticketType = $(this).val();
            }
        });
        var orderChannel = "";
        $('#orderChannel option').each(function(){
            if ($(this).prop("selected")) {
                orderChannel = $(this).val();
            }
        });
        var orderStatus = [];
        $('input[name="status"]:checkbox').each(function(){
            if ($(this).prop("checked")) {
                orderStatus.push($(this).prop('value'));
            }
        });
        //alert(orderStatus);
        orderPage.searchObj.pageNum = 1;  //reset
        orderPage.searchObj.params = "orderStartDate="+orderStartDate+"&orderEndDate="+orderEndDate
            +"&userTel="+userTel +"&ticketName="+ticketName +"&ticketType="+ticketType
            +"&orderChannel="+orderChannel +"&status="+orderStatus;
/*        alert("data:orderStartDate="+orderStartDate+";orderEndDate="+orderEndDate
            +";userTel="+userTel
            +";ticketName="+ticketName
            +";ticketType="+ticketType
            +";orderChannel="+orderChannel
            +";status="+orderStatus);*/
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
        if(this.searchObj.params){
            url = url+'?'+this.searchObj.params;
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