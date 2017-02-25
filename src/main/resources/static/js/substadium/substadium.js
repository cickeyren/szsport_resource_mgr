$(function () {

    /**
     * 进入子场馆新增页面
     */
    $("#addSubstadium").on('click', function () {
        window.location.href = "/subStadiumController/add.html";
    })

    /**
     * 主场馆新增保存数据
     */
    $("#savemainstaium").on('click', function () {
        doAdd();
    })

    $("#editmainstaium").on("click", function () {
        doUpdate()
    })

    /**
     * 取消新增主场馆
     */
    $("#cancelsavemainstaium").on('click', function () {
        window.location.href = "substadium.html";
    })

    /**
     * 取消编辑主场馆
     */
    $("#canceleditmainstaium").on('click', function () {
        window.location.href = "substadium.html";
    })

    /**
     * 重置查詢條件
     */
    $("#resetBtn").on('click',function () {
        $("#name").val("")
        window.location.href = "substadium.html";
    })
});


//新增页面添加数据
function doAdd() {
    var data = $('#addForm').serializeArray();
    getHtmlByUrl({
        type: 'POST',
        url: '/mainStadiumController/addmainStadiumModel.do',
        data: data,
        success: function (result) {
            if ("000000" == result.code) {
                layer.msg("添加成功！");
                window.location.reload(true);
                window.location.href = "mainstadium.html";
            }
            //console.log(result);
        },
        error: function (result) {
            layer.msg("添加失败！");
        }
    });
}

//编辑更新数据
function doUpdate() {
    var data = $("#updateForm").serializeArray();
    getHtmlByUrl({
        type: 'POST',
        url: '/mainStadiumController/updatemainstadium.do',
        data: data,
        success: function (result) {
            if ("000000" == result.code) {
                layer.msg("编辑数据成功！");
                window.location.reload(true);
                window.location.href = "mainstadium.html";
            }
            //console.log(result);
        },
        error: function (result) {
            layer.msg("编辑数据失败！");
        }
    });
}

//删除数据
function doDelete() {
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
                    window.location.href = "mainstadium.html";
                }
            },
            error: function (result) {
                layer.msg("删除失败！");
            }
        });
    });
}

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


