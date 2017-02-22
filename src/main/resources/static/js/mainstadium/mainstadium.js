var mainstadium;
$(function () {
    mainstadium = new MainstadiumObj();
    mainstadium.init();

});
function MainstadiumObj() {
    /*基础配置*/
    this.el = 'body';
    this.addLay = null;
    this.updateLay = null;
    this.validateCong = {};
    /*end 基础配置*/
}
MainstadiumObj.prototype = {
    /*操作*/
    searchObj : {
        title: '',
        pageNum: 1,
        pageSize: 1
    },
    loadAreaSize:['600px', '400px'],//弹出框的宽高
    doSearch:function() {
        var sTitle = $.trim($('#stadiumnametext').val());
        mainstadium.searchObj.title= sTitle || '';
        mainstadium.searchObj.pageNum = 1;  //reset
        mainstadium.renderPages(true);
    },

   add:function() {
        var data = $('#addForm').serializeArray();
        ajaxCommonFun({
            type: 'GET',
            url: '/mainStadiumController/add.html',
            data: data,
            success: function (res) {
                layer.open({
                    type: 1,
                    title:'新增',
                    area: ['760px', '450px'],
                    content: res,
                    btn: ['确定','取消'],
                    btn1: function(index){
                        layer.msg("编辑场馆！");

                        layer.close(index);
                    },
                    btn2 : function(index){
                        layer.msg("已经取消！");

                    }

                });
        }});
    },
    // doUpdate:function() {
    //     var data = $("#updateForm").serializeArray();
    //     ajaxCommonFun({
    //         type: 'POST',
    //         url: '/updatebook.do',
    //         data: data,
    //         success: function (t) {
    //             layer.close(updateLay);
    //             layer.msg(t.message);
    //             mainstadium.renderPages();
    //         }
    //     });
    // },
    // doDelete:function () {
    //     var $this = $(this),
    //         $parent = $this.closest('tr'),
    //         sid = $parent.find('input[type=hidden]').val();
    //     var deleteLay = layer.confirm('是否确认删除？', {
    //         btn: ['是', '否'] //按钮
    //     }, function () {
    //         if($('#bookTemplate').find('tbody tr').length == 1 && this.searchObj.pageNum !== 1){ //当当前只有一页的时候
    //             this.searchObj.pageNum = this.searchObj.pageNum - 1;
    //         }
    //
    //         ajaxCommonFun({
    //             type: 'POST',
    //             url: 'delete.do?bookid=' + sid,
    //             success: function (t) {
    //                 layer.close(deleteLay);
    //                 layer.msg(t.message);
    //                 mainstadium.renderPages(true);
    //             }
    //         });
    //     });
    // },
    /*end操作*/

    // /*弹出框*/
    // renderUpdatePage:function () {
    //     var $this = $(this),
    //         $parent = $this.closest('tr'),
    //         sid = $parent.find('input[type=hidden]').val();
    //     getHtmlByUrl({
    //         url: "/edit.html?bookid=" + sid,
    //         success: function (res) {
    //             updateLay = layer.open({
    //                 type: 1,
    //                 area: mainstadium.loadAreaSize,
    //                 content: res
    //             });
    //         }
    //     })
    // },
    // renderAddPage:function () {
    //     var that = this;
    //     getHtmlByUrl({
    //         url: '/add.html',
    //         success: function (res) {
    //             addLay = layer.open({
    //                 type: 1,
    //                 area: mainstadium.loadAreaSize, //宽高
    //                 content: res
    //             });
    //         }
    //     })
    // },
    /*end弹出框*/
    editInfo:function () {
        var id =  $(this).attr("data");
         layer.open({
            type: 1,
            title:'编辑信息',
            area: ['760px', '450px'],
            content: $("#div3"),
            btn: ['确定','取消'],
            btn1: function(index){
                layer.msg("编辑场馆！");

                layer.close(index);
            },
            btn2 : function(index){
                layer.msg("已经取消！");

            }

        });

    },
    /*页面初始化和事件绑定*/
    bindEvents:function() {
        /*update*/
        $(this.el).undelegate()
            .delegate('a[name=bookEdit]','click',mainstadium.renderUpdatePage)
            .delegate('a[name=bookDelete]','click',mainstadium.doDelete)
            .delegate('#findStadiumname','click',mainstadium.doSearch)
            .delegate('#addbook','click',mainstadium.renderAddPage)
            // .delegate('#showDiv','click',bookPage.sumit2)
            // .delegate('#saveBook','click',doAdd)
            .delegate('#updateBtn','click',mainstadium.doUpdate)
            .delegate('#addNew','click',mainstadium.add)
        .delegate('.edit','click',mainstadium.editInfo);
    },
    renderPages:function(isFirst) {
        var url = '/mainStadiumController/mainstadium.do', isAdd = false;
        var url2 = '/mainStadiumController/home.html';
        if(this.searchObj.title){
            url = url+'?name='+this.searchObj.title;
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
                    mainstadium.buildPageArea();
                }
            }
        });
    },
    /*end页面初始化和事件绑定*/

    /*分页*/
    doChangePage:function(num) {
        this.searchObj.pageNum = num;
        mainstadium.renderPages();
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
                    mainstadium.doChangePage(obj.curr);
                }
            }
        });
    },
    /*end分页*/

    /*页面初始化*/
    init:function() {
        mainstadium.renderPages(true);
        mainstadium.bindEvents();
    }
    /*end 页面初始化*/
};