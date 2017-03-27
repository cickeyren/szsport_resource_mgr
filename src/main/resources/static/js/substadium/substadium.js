$(function () {
    /**
     * 进入子场馆新增页面
     */
    $("#addSubstadium").on('click', function () {
        window.location.href = "/subStadiumController/add.html?substadiumID="+$("#substadiumID").val();
    })

    /**
     * 子场馆新增保存数据
     */
    $("#saveSubstadium").on('click', function () {
        var mainStadiumId = $.trim($("#substadiumAdd").val());
        doAdd(mainStadiumId);
    })

    /**
     * 子场馆数据更新
     */
    $("#editSubstadium").on("click", function () {
        doUpdate()
    })

    /**
     * 子场馆数据删除
     */
    $('a[name="delete"]').on('click',function () {
        doDelete(this);
    })

    /**
     * 取消新增子场馆
     */
    $("#cancelSubstadium").on('click', function () {
        window.location.href = "/subStadiumController/substadium.html";
    })

    /**
     * 取消编辑主场馆
     */
    $("#cancelSubstadium").on('click', function () {
        window.location.href = "/subStadiumController/substadium.html";
    })

    //给体育项目增加change事件
    $("#classflyOne").change(function(){
        updateSubStadiumList($("#classflyOne").val());
    });

    //初始化富文本编辑器---请放在点击事件之后，预防在html页面渲染失败，导致html页面无法加载
    KindEditor.options.filterMode = false;
    var orderDescriptionEditor;
    KindEditor.ready(function (K) {
        orderDescriptionEditor = K.create('textarea[id="introduction"]', {
            resizeType: 1,
            allowPreviewEmoticons: false,
            filterMode: false
        });
        window.introductionEditor = orderDescriptionEditor;
    });

});

/**
 * 根据体育馆的主向项目获取子项目列表(该功能无测试数据)
 * @param
 */
function updateSubStadiumList(cid) {
    $.ajax({
        url:'/subStadiumController/getclassflyByMainId.json',
        type:'POST', //GET
        data:{"cid":cid},
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(result){
            if("000000" == result.code) {
                var items = result.result.classflyList;
                $("#classify").empty();
                $.each(items,function(i,n){
                    $("#classify").append("<option value=\"" + n.cid + "\">"+n.categoryName+"</option>");
                });
            }else {
                layer.alert(result.result);
            }
        },
    })
}

//新增页面添加数据
function doAdd(mainStadiumId) {
    window.introductionEditor.sync();
    var data = $('#addForm').serializeArray();
    getHtmlByUrl({
        type: 'POST',
        url: '/subStadiumController/addsubStadium.do?mainStadiumId='+mainStadiumId,
        data: data,
        success: function (result) {
            if ("000000" == result.code) {
                layer.msg("添加成功！");
                setTimeout(function () {
                    window.location.href = "/subStadiumController/substadium.html?mainStadiumId="+mainStadiumId;
                },6000)
            }else {
                layer.alert(result.result)
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
//准备json数据
    var addJson = {};
    addJson.id = $("#id").val();
    addJson.parentId = $("#parent_id").val();
    addJson.name = $("#name").val();
    addJson.name = $("#name").val();
    addJson.classify = $("#classify").val();
    addJson.status = $('input[name="status"]:checked').val();
    window.introductionEditor.sync();
    addJson.introduction = window.introductionEditor.html();
    var mainStadiumIdedit = $("#mainStadiumIdedit").val();
    getHtmlByUrl({
        type: 'POST',
        url: '/subStadiumController/updatesubStadium.do',
        data: addJson,
        success: function (result) {
            if ("000000" == result.code) {
                layer.msg("添加成功！");
                setTimeout(function () {
                    //返回到子场馆列表
                    window.location.href = "/subStadiumController/substadium.html?mainStadiumId=" + mainStadiumIdedit;
                }, 1000);
            }else {
                layer.alert(result.result);
            }
        },
        error: function (result) {
            layer.msg("添加失败！");
        }
    });
}

//删除数据
function doDelete(field) {
    var $this = $(field),
        $parent = $this.parent().parent(),
        id = $parent.find('input[type="hidden"]').val();
    layer.confirm('是否确认删除？', {
        btn: ['是', '否'] //按钮
    }, function () {
        $.ajax({
            url: '/subStadiumController/delete.do',
            type: 'GET',
            data: {
                "subStadiumid": id
            },
            dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            success: function (result) {
                if ("000000" == result.code) {
                    layer.msg("删除成功！");
                    window.location.href = "/subStadiumController/substadium.html?mainStadiumId="+$("#substadiumID").val();
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


