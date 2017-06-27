/**
 * Created by wangw on 2017/6/19.
 */

$(function () {

    //新增界面保存按钮点击事件 ---  执行保存
    $("#addMerchantBtn").on("click", function () {
        doAdd();
    });

    $("#merchants").on("dblclick",function () {
        dbClickOper("merchants","Rmerchants");
    });
    $("#Rmerchants").on("dblclick",function () {
        dbClickOper("Rmerchants","merchants");
    });
    $("#addMerchant").on("click",function(){
        addOrRemoveOption("merchants","Rmerchants");
    });
    $("#delMerchant").on("click",function () {
        addOrRemoveOption("Rmerchants","merchants");
    });
})

//双击操作
function dbClickOper(sourceId,targetId){
    var $me = $("#" + sourceId);
    var value = $me.val(),
        text = $me.find("option:selected").text();
    $me.find("option:selected").remove();
    if(null != value && null != text){
        $("#" + targetId).append('<option value="'+value+'">'+text+'</option>');
    }
}

//多选操作
function addOrRemoveOption(sourceId,targetId){
    var $source = $("#" + sourceId).find("option:selected"),
        $target = $("#" + targetId).find("option:selected");
    if(null != $source && null != $target){
        var html = '';
        $source.each(function(){
            var me = this;
            var value = $(me).val(),
                text = $(me).text();
            if(null != value && null != text){
                html += '<option value="'+value+'">'+text+'</option>'
            }
        });
        $source.remove();
        if('' !== html){
            $("#" + targetId).append(html);
        }
    }
}


//新增页面添加数据
function doAdd() {
    var RmerchantsOption = $("#Rmerchants").find("option");
    var merchants=[];
    for ( var i=0;i<RmerchantsOption.length;i++){
        merchants.push($(RmerchantsOption[i]).val());
    }
    //准备json数据
    var addJson = {};
    addJson.mainStadiumId = $("#mainStadiumId").val();
    addJson.merchants = merchants.join(",");

    $.ajax({
        url: '/mainStadiumMerchant/addMerchant.json',
        type: 'POST', //GET
        data: addJson,
        // contentType:'application/json',
        dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success: function (result) {
            if ("000000" == result.code) {
                layer.msg("添加成功！");
                setTimeout(function () {
                    window.location.href = "/mainStadiumMerchant/merchantList.html?mainstadium_id=" + $('#mainStadiumId').val();
                }, 1000);
            }else {
                layer.msg(result.message);
            }
        },
        error: function (result) {
            layer.msg("添加失败！");
        }
    });
}