var mainstadium;
$(function () {
    mainstadium = new MainstadiumObj();
    mainstadium.init();

});

//是否精选点击事件
function selectFist(strategyState, obj) {
    var id = $(obj).attr("data")
    var tip = "0" == strategyState ? "设为精选" : "取消精选";
    layer.confirm(tip, {
        btn: ['是', '否'] //按钮
    }, function () {
        $.ajax({
            url: '/mainStadiumController/updataSelectFirst.do',
            type: 'POST',
            data: {
                "mainStadiumid": id, "is_special": strategyState
            },
            dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            success: function (result) {
                if ("000000" == result.code) {
                    layer.msg("设为精选成功！");
                    window.location.reload(true);
                }
            },
            error: function (result) {
                layer.msg("设为精选失败！");
            }
        });
    });
}

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
    searchObj: {
        title: '',
        pageNum: 1,
        pageSize: 2
    },
    loadAreaSize: ['1000px', '600px'],//弹出框的宽高

    //带条件查找
    doSearch: function () {
        var sTitle = $.trim($('#stadiumnametext').val());//获取查询条件
        mainstadium.searchObj.title = sTitle || '';
        mainstadium.searchObj.pageNum = 1;  //reset
        mainstadium.renderPages(true);
    },

    //新增页面添加数据
    doAdd: function () {
        var data = $('#addForm').serializeArray();
        ajaxCommonFun({
            type: 'POST',
            url: '/mainStadiumController/addmainStadiumModel.do',
            data: data,
            success: function (result) {
                if ("000000" == result.code) {
                    layer.msg("添加成功！");
                    window.location.reload(true);
                }
                //console.log(result);
            },
            error: function (result) {
                layer.msg("添加失败！");
            }
        });
    },

    //编辑更新数据
    doUpdate: function () {
        var data = $("#updateForm").serializeArray();
        ajaxCommonFun({
            type: 'POST',
            url: '/mainStadiumController/updatemainstadium.do',
            data: data,
            success: function (result) {
                if ("000000" == result.code) {
                    layer.msg("编辑数据成功！");
                    window.location.reload(true);
                }
                //console.log(result);
            },
            error: function (result) {
                layer.msg("编辑数据失败！");
            }
        });
    },

    //删除数据
    doDelete: function () {
        var $this = $(this),
            $parent = $this.closest('tr'),
            sid = $parent.find('input[type=hidden]').val();
        layer.confirm('是否确认删除？', {
            btn: ['是', '否'] //按钮
        }, function () {
            $.ajax({
                url: '/mainStadiumController/delete.do',
                type: 'POST',
                data: {
                    "mainStadiumid": sid
                },
                dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
                success: function (result) {
                    if ("000000" == result.code) {
                        layer.msg("删除成功！");
                        window.location.reload(true);
                    }
                },
                error: function (result) {
                    layer.msg("删除失败！");
                }
            });
        });
    },

    /*end操作*/
    renderAddPage: function () {
        var that = this;
        getHtmlByUrl({
            url: '/mainStadiumController/add.html',
            success: function (res) {
                addLay = layer.open({
                    title: '新增场馆',
                    type: 1,
                    area: mainstadium.loadAreaSize, //宽高
                    content: res
                });
            }
        })
    },
    /*end弹出框*/

    //编辑页面弹窗
    updateMainStudium:function () {
        var $this = $(this),
            $parent = $this.closest('tr'),
            sid = $parent.find('input[type=hidden]').val();
        getHtmlByUrl({
            url: '/mainStadiumController/edit.html',
            type: 'POST',
            data: {
                "mainstadiumid": sid
            },
            dataType: 'json',
            success: function (res) {
                addLay = layer.open({
                    title: '编辑场馆',
                    type: 1,
                    area: mainstadium.loadAreaSize, //宽高
                    content: res
                });
            }
        })
    },

    selectFist: function (is_special,obj) {
        var id = $(obj).attr("data")
        var tip = "0" == is_special ? "确定取消精选吗?":"确定设为精选吗";
        if (confirm(tip)){
            $.ajax({
                url: '/mainStadiumController/updataSelectFirst.do',
                type: 'POST',
                data: {
                    "mainStadiumid": id,"is_special":is_special
                },
                dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
                success: function (result) {
                    if ("000000" == result.code) {
                        layer.msg("设为精选成功！");
                        window.location.reload(true);
                    }
                },
                error: function (result) {
                    layer.msg("设为精选失败！");
                }
            });
        }
    },


    /*页面初始化和事件绑定*/
    bindEvents: function () {
        /*update*/
        $(this.el).undelegate()
            //根据ID删除数据
            .delegate('#delete', 'click', mainstadium.doDelete)
            //带条件查询所有列表
            .delegate('#findStadiumname', 'click', mainstadium.doSearch)
            //新增页面点击保存，进入到doAdd
            .delegate('#savemainstaium', 'click', mainstadium.doAdd)
            //主页面点击新增按钮  进入到renderAddPage
            .delegate('#addNew', 'click', mainstadium.renderAddPage)
            //stadiumList页面点击编辑  进入编辑弹窗页面
            .delegate('.edit', 'click', mainstadium.updateMainStudium)
            .delegate('#editmainstaium', 'click', mainstadium.doUpdate);
    },

    //根据请求获取分页数据
    renderPages: function (isFirst) {
        var url = '/mainStadiumController/mainstadium.do', isAdd = false;
        var url2 = '/mainStadiumController/home.html';
        if (this.searchObj.title) {
            url = url + '?name=' + this.searchObj.title;
            isAdd = true;
        }
        if (this.searchObj.pageSize) {
            url = isAdd ? url + '&pageSize=' + this.searchObj.pageSize : url + '?pageSize=' + this.searchObj.pageSize;
            isAdd = true;
        }
        if (this.searchObj.pageSize) {
            url = isAdd ? url + '&page=' + this.searchObj.pageNum : url + '?page=' + this.searchObj.pageNum;
        }

        getHtmlByUrl({
            url: url2,
            success: function (res) {
                if (isFirst) {
                }
            }
        });

        getHtmlByUrl({
            url: url,
            dataType: 'html',
            success: function (res) {
                $("#div2").html(res);
                if (true) {
                    mainstadium.buildPageArea();
                }
            }
        });
    },
    /*end页面初始化和事件绑定*/

    /*分页*/
    doChangePage: function (num) {
        this.searchObj.pageNum = num;
        mainstadium.renderPages();
    },
    buildPageArea: function () {
        var pageAllCount = $('#pageAllCount').val(),
            totalPage = Math.ceil(pageAllCount / this.searchObj.pageSize);
        laypage({
            cont: 'page',
            pages: totalPage,
            curr: this.searchObj.pageNum,
            jump: function (obj, first) {
                if (!first) {
                    mainstadium.doChangePage(obj.curr);
                }
            }
        });
    },
    /*end分页*/

    /*页面初始化*/
    init: function () {
        mainstadium.renderPages(true);
        mainstadium.bindEvents();
    }
    /*end 页面初始化*/


};