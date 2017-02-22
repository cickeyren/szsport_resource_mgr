var mainstadium;
/*$('#distpicker1').distpicker({
    province: '---- 所在省 ----',
    city: '---- 所在市 ----',
    district: '---- 所在区 ----'
});*/
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
    loadAreaSize:['800', '600'],//弹出框的宽高

    //带条件查找
    doSearch:function() {
        var sTitle = $.trim($('#stadiumnametext').val());//获取查询条件
        mainstadium.searchObj.title= sTitle || '';
        mainstadium.searchObj.pageNum = 1;  //reset
        mainstadium.renderPages(true);
    },
    //新增页面添加数据
    doAdd:function() {
        var data = $('#addForm').serializeArray();
        ajaxCommonFun({
            type: 'POST',
            url: '/mainStadiumController/addmainStadiumModel.do',
            data: data,
            success: function (res) {
                layer.open({
                    type: 1,
                    title:'新增',
                    area: ['600px', '400px'],
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
    //编辑更新数据
    doUpdate:function() {
        var data = $("#updateForm").serializeArray();
        ajaxCommonFun({
            type: 'POST',
            url: '/updatebook.do',
            data: data,
            success: function (t) {
                layer.close(updateLay);
                layer.msg(t.message);
                mainstadium.renderPages();
            }
        });
    },

    //删除数据
    doDelete:function () {
        var $this = $(this),
            $parent = $this.closest('tr'),
            sid = $parent.find('input[type=hidden]').val();
        var deleteLay = layer.confirm('是否确认删除？', {
            btn: ['是', '否'] //按钮
        }, function () {
            if($('#bookTemplate').find('tbody tr').length == 1 && this.searchObj.pageNum !== 1){ //当当前只有一页的时候
                this.searchObj.pageNum = this.searchObj.pageNum - 1;
            }

            ajaxCommonFun({
                type: 'POST',
                url: 'delete.do?bookid=' + sid,
                success: function (t) {
                    layer.close(deleteLay);
                    layer.msg(t.message);
                    mainstadium.renderPages(true);
                }
            });
        });
    },
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
    renderAddPage:function () {
        var that = this;
        getHtmlByUrl({
            url: '/mainStadiumController/add.html',
            success: function (res) {
                addLay = layer.open({
                    type: 1,
                    area: mainstadium.loadAreaSize, //宽高
                    content: res
                });
            }
        })
    },
    /*end弹出框*/

    //弹窗进行编辑
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

    // add:function () {
    //     var id =  $(this).attr("data");
    //     layer.open({
    //         type: 1,
    //         title:'编辑信息',
    //         area: ['760px', '450px'],
    //         content: $("#div3"),
    //         btn: ['确定','取消'],
    //         btn1: function(index){
    //             layer.msg("编辑场馆！");
    //
    //             layer.close(index);
    //         },
    //         btn2 : function(index){
    //             layer.msg("已经取消！");
    //
    //         }
    //
    //     });
    // },

    /*页面初始化和事件绑定*/
    bindEvents:function() {
        /*update*/
        $(this.el).undelegate()
            // .delegate('a[name=bookEdit]','click',mainstadium.renderUpdatePage)
            // .delegate('a[name=bookDelete]','click',mainstadium.doDelete)
            .delegate('#findStadiumname','click',mainstadium.doSearch)
            // .delegate('#addbook','click',mainstadium.renderAddPage)
            // .delegate('#showDiv','click',bookPage.sumit2)
            //新增页面点击保存，进入到doAdd
            .delegate('#savemainstaium','click',mainstadium.doAdd)
            // .delegate('#updateBtn','click',mainstadium.doUpdate)
            //主页面点击新增按钮  进入到renderAddPage
            .delegate('#addNew','click',mainstadium.renderAddPage)
            //stadiumList页面点击编辑  进入编辑弹窗页面editInfo
            .delegate('.edit','click',mainstadium.editInfo);
    },

    //根据请求获取分页数据
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