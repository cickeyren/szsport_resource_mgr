$(function () {
    /**
     * 新增页面
     */
    $("#add").on('click', function () {
        window.location.href = "/discount/add.html";
    });

    /**
     * 更新
     */
    $("#edit").on("click", function () {
        doUpdate();
    });

    /**
     * 删除(在循环中不能用id选择器)
     */
    $('a[name="delete"]').on("click", function () {
        doDelete(this);
    });

    /**
     * 取消
     */
    $("#cancel").on('click', function () {
        window.location.href = "/discount/discountList.html";
    });
    $("#cancelEdit").on('click', function () {
        window.location.href = "/discount/discountList.html";
    });
    /**
     * 重置查詢條件
     */
    $("#resetBtn").on('click', function () {
        window.location.href = "/discount/discountList.html";
    });

    /**
     * 主场馆发生变化
     */
    $("#mainStadiumId").change(function () {
        updateSubStadiumId($("#mainStadiumId").val());
    });

    $("#mainStadium_id").change(function () {
        updateSubStadium_Id($("#mainStadium_id").val());
    });
});

function updateSubStadiumId(mainStadiumId) {
    $.ajax({
        url:'/discount/getSubStadiumList.do',
        type:'POST', //GET
        data:{"mainStadiumId":mainStadiumId},
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(result){
            if("000000" == result.code) {
                var items = result.result;
                $("#subStadiumId").empty();
                $("#subStadiumId").append("<option value=''>全部</option>");
                $.each(items,function(i,n){
                    $("#subStadiumId").append("<option value=\"" + n.id + "\">"+n.name+"</option>");
                });
            }else {
                layer.alert(result.result);
            }
        },
    })
}

function updateSubStadium_Id(mainStadiumId) {
    $.ajax({
        url:'/discount/getSubStadiumList.do',
        type:'POST', //GET
        data:{"mainStadiumId":mainStadiumId},
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(result){
            if("000000" == result.code) {
                var items = result.result;
                $("#subStadium_id").empty();
                $("#subStadium_id").append("<option value=''>全部</option>");
                $.each(items,function(i,n){
                    $("#subStadium_id").append("<option value=\"" + n.id + "\">"+n.name+"</option>");
                });
            }else {
                layer.alert(result.result);
            }
        },
    })
}

//新增页面添加数据
function doAdd() {
    var addJson = {};
    addJson.discountType = $("#discountType").val();
    addJson.mainStadiumId = $("#mainStadium_id").val();
    addJson.subStadiumId = $("#subStadium_id").val();
    addJson.discountChannel = $("#discountChannel").val();
    addJson.payType = $("#payType").val();
    addJson.discountLimit = $("#discountLimit").val();
    addJson.startTime = $("#startTime").val();
    addJson.endTime = $("#endTime").val();
    getHtmlByUrl({
        type: 'POST',
        url: '/discount/add.do',
        data: addJson,
        success: function (result) {
            if ("000000" == result.code) {
                layer.msg("添加成功！");
                setTimeout(function () {
                    window.location.href = "/discount/discountList.html";
                },3000)
            }else {
                layer.alert(result.message);
            }
            console.log(result);
        },
        error: function (result) {
            layer.msg("添加失败！");
        }
    });
}

//编辑更新数据
function doUpdate() {
    var addJson = {};
    addJson.id = $('input[id="id"]').val();
    addJson.discountType = $("#discountType").val();
    addJson.mainStadiumId = $("#mainStadium_id").val();
    addJson.subStadiumId = $("#subStadium_id").val();
    addJson.discountChannel = $("#discountChannel").val();
    addJson.payType = $("#payType").val();
    addJson.discountLimit = $("#discountLimit").val();
    addJson.startTime = $("#startTime").val();
    addJson.endTime = $("#endTime").val();

    getHtmlByUrl({
        type: 'POST',
        url: '/discount/update.do',
        data: addJson,
        success: function (result) {
            if ("000000" == result.code) {
                layer.msg("编辑成功！");
                setTimeout(function () {
                    window.location.href = "/discount/discountList.html";
                },3000)
            }else {
                layer.alert(result.result);
            }
            console.log(result);
        },
        error: function (result) {
            layer.msg("编辑失败！");
        }
    });
}

//删除数据
function doDelete(filed) {
    var $this = $(filed),
        $parent = $this.closest('tr'),
        sid = $parent.find('input[type=hidden]').val();
    layer.confirm('是否确认作废？', {
        btn: ['是', '否'] //按钮
    }, function () {
        $.ajax({
            url: '/discount/delete.do',
            type: 'GET',
            data: {
                "id": sid
            },
            dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            success: function (result) {
                if ("000000" == result.code) {
                    layer.msg("作废成功！");
                    window.location.href = "/discount/discountList.html";
                }else {
                    layer.alert(result.result);
                }
            },
            error: function (result) {
                layer.msg("作废失败！");
            }
        });
    });
}



