$(function () {
    /**
     * 进入主场馆新增页面
     */
    $("#addNewMainstadium").on('click', function () {
        window.location.href = "/mainStadiumController/add.html";
    })

    // /**
    //  * 主场馆新增保存数据
    //  */
    // $("#savemainstaium").on('click', function () {
    //     doAdd();
    // })

    /**
     * 主场馆数据更新
     */
    $("#editmainstaium").on("click", function () {
        doUpdate();
    })

    /**
     * 主场馆数据删除(在循环中不能用id选择器)
     */
    $('a[name="delete"]').on("click", function () {
        doDelete(this);
    })

    /**
     * 取消新增主场馆
     */
    $("#cancelsavemainstaium").on('click', function () {
        window.location.href = "/mainStadiumController/mainstadium.html";
    })

    /**
     * 取消编辑主场馆
     */
    $("#canceleditmainstaium").on('click', function () {
        window.location.href = "/mainStadiumController/mainstadium.html";
    })

    /**
     * 重置查詢條件
     */
    $("#resetBtn").on('click', function () {
        $("#name").val("")
        window.location.href = "/mainStadiumController/mainstadium.html";
    })

    /**
     * 省区发生改变之后
     */
    $("#provincial_level").change(function () {
        updateprovinceList($("#provincial_level").val());
    })

    /**
     * 市区发生改变之后
     */
    $("#city_level").change(function () {
        updatecityList($("#city_level").val());
    })


    //初始化富文本编辑器---请放在点击事件之后，预防在html页面渲染失败，导致html页面无法加载
    KindEditor.options.filterMode = false;
    var fieldDescriptionEditor;
    KindEditor.ready(function (K) {
        fieldDescriptionEditor = K.create('textarea[name="introduction"]', {
            resizeType: 1,
            allowPreviewEmoticons: false,
            filterMode: false
        });

        window.introductionEditor = fieldDescriptionEditor;
    });
});

function updateprovinceList(provinceID) {
    $.ajax({
        url:'/mainStadiumController/getCityByID.do',
        type:'POST', //GET
        data:{"provinceID":provinceID},
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(result){
            if("000000" == result.code) {
                var items = result.result;
                $("#city_level").empty();
                $.each(items,function(i,n){
                    $("#city_level").append("<option value=\"" + n.cityID + "\">"+n.city+"</option>");
                });
                var cityID = $("#city_level").val();
                updatecityList(cityID);
            }else {
                layer.alert(result.result);
            }
        },
    })
}

function updatecityList(cityID) {
    $.ajax({
        url:'/mainStadiumController/getAreaByID.do',
        type:'POST', //GET
        data:{"cityID":cityID},
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(result){
            if("000000" == result.code) {
                var items = result.result;
                $("#district_level").empty();
                $.each(items,function(i,n){
                    $("#district_level").append("<option value=\"" + n.areaID + "\">"+n.area+"</option>");
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
    addJson.mainStadiumid = $("#mainStadiumid").val();
    addJson.name = $("#name").val();
    addJson.provincial_level = $("#provincial_level").val();
    addJson.city_level = $("#city_level").val();
    addJson.district_level = $("#district_level").val();
    addJson.address = $("#address").val();
    addJson.telephone = $("#telephone").val();
    addJson.open_time = $("#open_time").val();
    addJson.lng = $("#lng").val();
    addJson.lat = $("#lat").val();

    window.introductionEditor.sync();
    addJson.introduction = window.introductionEditor.html();

    addJson.status = $('input[name="status"]:checked').val();

    getHtmlByUrl({
        type: 'POST',
        url: '/mainStadiumController/addmainStadiumModel.do',
        data: addJson,
        success: function (result) {
            if ("000000" == result.code) {
                layer.msg("添加成功！");
                setTimeout(function () {
                    window.location.href = "/mainStadiumController/mainstadium.html";
                },3000)

            }else {
                layer.alert(result.result);
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
    var addJson = {};
    addJson.id = $('input[id="mainStadiumid"]').val();
    addJson.name = $("#name").val();
    addJson.provincial_level = $("#provincial_level").val();
    addJson.city_level = $("#city_level").val();
    addJson.district_level = $("#district_level").val();
    addJson.address = $("#address").val();
    addJson.telephone = $("#telephone").val();
    addJson.open_time = $("#open_time").val();
    addJson.lng = $("#lng").val();
    addJson.lat = $("#lat").val();

    window.introductionEditor.sync();
    addJson.introduction = window.introductionEditor.html();

    addJson.status = $('input:radio[name="status"]:checked').val();
    getHtmlByUrl({
        type: 'POST',
        url: '/mainStadiumController/updatemainstadium.do',
        data: addJson,
        success: function (result) {
            if ("000000" == result.code) {
                layer.msg("编辑数据成功！");
                setTimeout(function () {
                    window.location.href = "/mainStadiumController/mainstadium.html";
                },3000)
            }else {
                layer.alert(result.result);
            }
            //console.log(result);
        },
        error: function (result) {
            layer.msg("编辑数据失败！");
        }
    });
}

//删除数据
function doDelete(filed) {
    var $this = $(filed),
        $parent = $this.closest('tr'),
        sid = $parent.find('input[type=hidden]').val();
    layer.confirm('是否确认删除？', {
        btn: ['是', '否'] //按钮
    }, function () {
        $.ajax({
            url: '/mainStadiumController/delete.do',
            type: 'GET',
            data: {
                "mainStadiumid": sid
            },
            dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            success: function (result) {
                if ("000000" == result.code) {
                    layer.msg("删除成功！");
                    window.location.href = "/mainStadiumController/mainstadium.html";
                }else {
                    layer.alert(result.result);
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
    var tip = "0" == strategyState ? "是否取消精选？" : "是否设为精选？";
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
                    window.location.href = "/mainStadiumController/mainstadium.html";
                }else {
                    layer.alert(result.result);
                }
            },
            error: function (result) {
                layer.msg("设为精选失败！");
            }
        });
    });
}




