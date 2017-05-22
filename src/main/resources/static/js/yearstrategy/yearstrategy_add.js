/**
 * Created by win7 on 2017/2/15.
 */
$(function () {
    /*********可用时间段效果功能开始*********/
    var checkUseableTimeArray = [];//验票可用时间段数组
    //点击可用时间段中的添加按钮
    $("#addLimitedStartAndEndBtn").click(function(){
        var checkLimitedStartTime = $.trim($("#checkLimitedStartTime").val());
        var checkLimitedEndTime = $.trim($("#checkLimitedEndTime").val());
        if(undefined == checkLimitedStartTime || "" == checkLimitedStartTime) {
            alert("请填写限制起始时间");
            return;
        }
        if(undefined == checkLimitedEndTime || "" == checkLimitedEndTime) {
            alert("请填写限制结束时间");
            return;
        }
        var startAndEndTimeKey = checkLimitedStartTime+"$"+checkLimitedEndTime;
        var startAndEndTimeValue = checkLimitedStartTime+"至"+checkLimitedEndTime;
        if(checkUseableTimeArray.contains(startAndEndTimeKey)) {
            alert("该时间的日期已经添加，请重新选择~");
            return;
        }
        checkUseableTimeArray.push(startAndEndTimeKey);
        $("#limitedStartAndEndTimeSelects").prepend('<option value="'+startAndEndTimeKey+'">'+startAndEndTimeValue+"</option>");

    });
    //可用段时间条目双击删除
    $("#limitedStartAndEndTimeSelects").dblclick(function(){
        var startAndEndTimeKey =  $("option:selected",this).attr("value");
        //alert(startAndEndTimeKey);
        $("option:selected",this).remove();
        checkUseableTimeArray.remove(startAndEndTimeKey);
        //console.log(checkUseableTimeArray);
    });
    /*********不可用日期功能结束*********/
    var shieldDateArray = [];//验票不可用日期数组
    //点击不可用日期中的添加按钮
    $("#addShieldStartAndEndBtn").click(function(){
        var shieldStartTime = $.trim($("#shieldStartTime").val());
        var shieldEndTime = $.trim($("#shieldEndTime").val());
        if(undefined == shieldStartTime || "" == shieldStartTime) {
            alert("请填写起始不可用日期");
            return;
        }
        if(undefined == shieldEndTime || "" == shieldEndTime) {
            alert("请填写结束不可用时间");
            return;
        }
        var shieldStartTimeKey = shieldStartTime+"$"+shieldEndTime;
        var shieldStartTimeValue = shieldStartTime+"至"+shieldEndTime;
        if(shieldDateArray.contains(shieldStartTimeKey)) {
            alert("该不可用日期已经添加，请重新选择~");
            return;
        }
        shieldDateArray.push(shieldStartTimeKey);
        $("#shieldStartTimeAndEndTimeSelects").prepend('<option value="'+shieldStartTimeKey+'">'+shieldStartTimeValue+"</option>");
    });
    //不可用日期段条目双击删除
    $("#shieldStartTimeAndEndTimeSelects").dblclick(function(){
        var shieldStartTimeKey =  $("option:selected",this).attr("value");
        $("option:selected",this).remove();
        shieldDateArray.remove(shieldStartTimeKey);
        //console.log(checkUseableTimeArray);
    });
    // 关闭过滤模式，保留所有标签
    KindEditor.options.filterMode = false;
    var orderDescriptionEditor;
    KindEditor.ready(function(K) {
        orderDescriptionEditor = K.create('textarea[name="orderDescription"]', {
            resizeType : 1,
            allowPreviewEmoticons : false,
            filterMode : false

        });
    });
    //初始化富文本
    var refundDescriptionEditor;
    KindEditor.ready(function(K) {
        refundDescriptionEditor = K.create('textarea[name="refundDescription"]', {
            resizeType : 1,
            allowPreviewEmoticons : false,
            filterMode : false
//                items : [
//                    'source','fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
//                    'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
//                    'insertunorderedlist', '|', 'emoticons', 'image', 'link']
        });
    });
    //表单配置
    var valConfig = {
        rules: {
            ticketName: {
                required: true,
                maxlength: 200
            },
            sellWay: {
                required: true
            },
            checkTicketType: {
                required: true
            },
            checkTicketWay: {
                required: true
            },
            fixAvailableTimes:{
                number:true
            },
            checkDailyLimitedTimes:{
                number:true
            },
            orderDescription: {
                required: true
            },
            refundDescription: {
                required: true
            },
            sellPrice:{
                required: true,
                myDouble:true
            },
            costPrice:{
                required: true,
                myDouble:true
            },
            storePrice:{
                required: true,
                myDouble:true
            }
        },
        messages: {
            ticketName: {
                required: "请填写门票名称"
            },
            fixAvailableTimes: {
                number: "请输入有效的数字"
            },
            checkDailyLimitedTimes: {
                number: "请输入有效的数字"
            },
            orderDescription: {
                required: "请填写预订说明"
            },
            refundDescription: {
                required: "请填写退票说明"
            },
            sellPrice:{
                required: "请填写销售价"//,myDouble: "请输入有效的数字"
            },
            costPrice:{
                required: "请填写成本价"//,
                //number: "请输入有效的数字",
                //min:"必须输入大于1的整数"
            },
            storePrice:{
                required:  "请填写门市价"//,
                //number: "请输入有效的数字",
                //min:"必须输入大于1的整数"
            }
        },
        submitHandler: function () {
            var ticketType = $.trim($("#ticketType").val());//套票类型
            var mainStadiumID = $("#mainStadiumID").val();//主场馆ID
            if(undefined == mainStadiumID || "" == mainStadiumID) {
                alert("请选择正确的主场馆内容~");
                return;
            }
            var subStadiumID = $("#subStadiumID").val();//子场馆ID
            if(undefined == subStadiumID || "" == subStadiumID) {
                alert("请选择正确的子场馆内容~");
                return;
            }
            var classify = "";
            $('#subStadiumID option').each(function(){
                if ($(this).prop("selected")) {
                    classify = $(this).attr('classify');
                    //console.log(classify);
                }
            });
            var ticketName = $.trim($("#ticketName").val());//门票名称
            var merchantId = $.trim($("#merchantId").val());//合作商
            var sellWayArray = [];//售卖渠道
            $('input[name="sellWay"]:checkbox').each(function(){
                if ($(this).prop("checked")) {
                    sellWayArray.push($(this).prop('value'));
                }
            });
            var sellWay = sellWayArray.toString();//售卖渠道
            var orderEffectiveType = $('input:radio[name="orderEffectiveType"]:checked').val();//使用有效期类型 0：预订后x天有效 1：固定有效期
            var orderFixDay = "";//多少天后有效
            var orderEffectiveStartTime = $.trim($("#orderEffectiveStartTime").val());//有效期起始时间(无论是有效天数还是固定时间都生成该时间)
            var orderEffectiveEndTime = $.trim($("#orderEffectiveEndTime").val());//售卖渠道
            if(orderEffectiveType == "0") {
                orderFixDay = $.trim($("#fixDay").val());
                if(!isDigital(orderFixDay)) {
                    $("#fixDay").focus();
                    alert("请填写正确有效天数~");
                    return;
                }
            } else if(orderEffectiveType == "1") {
                if("" == orderEffectiveStartTime || "" == orderEffectiveEndTime) {
                    alert("固定有效期填写不合法");
                    return;
                }
            }
            var orderRefundRule = $('input:radio[name="orderRefundRule"]:checked').val();//退款规则 0：不可退 1：随时退 2：条件退
            var checkTicketTypeArray = [];
            $('input[name="checkTicketType"]:checkbox').each(function(){
                if ($(this).prop("checked")) {
                    checkTicketTypeArray.push($(this).prop('value'));
                }
            });
            var checkTicketType = checkTicketTypeArray.toString();//验票凭证
            var checkTicketWayArray = [];
            $('input[name="checkTicketWay"]:checkbox').each(function(){
                if ($(this).prop("checked")) {
                    checkTicketWayArray.push($(this).prop('value'));
                }
            });
            var checkTicketWay = checkTicketWayArray.toString();//验票渠道
            var checkTicketAvailableTimes = $('input:radio[name="checkTicketAvailableTimes"]:checked').val();//限制次数类型选择
            var fixAvailableTimes = "";
            if(checkTicketAvailableTimes == "0"){
                fixAvailableTimes = "-1";
            } else if (checkTicketAvailableTimes == "1"){
                fixAvailableTimes = $.trim($("#fixAvailableTimes").val());
                if(!isDigital(fixAvailableTimes)) {
                    $("#fixAvailableTimes").focus();
                    alert("请填写正确限票次数~");
                    return;
                }
            }
            var checkDailyLimitedTimes = $.trim($("#checkDailyLimitedTimes").val());//每日限次
            var checkLimitedDateType = $('input:radio[name="checkLimitedDateType"]:checked').val();//时间限制 0：每日 1：每周
            var checkLimitedWeekDetailsArray = [];//
            if(checkLimitedDateType == "0"){
                checkLimitedDateType = "0";
            } else if (checkLimitedDateType == "1"){
                $('input[name="checkLimitedWeekDetails"]:checkbox').each(function(){
                    if ($(this).prop("checked")) {
                        checkLimitedWeekDetailsArray.push($(this).prop('value'));
                    }
                });
                if(checkLimitedWeekDetailsArray.length == 0) {
                    alert("请选择具体的星期几~");
                    return;
                }
            }
            var checkLimitedWeekDetails = checkLimitedWeekDetailsArray.toString();//如果时间限制为每周那么显示具体的限制星期几 用;隔开
            var checkUseableTime = checkUseableTimeArray.toString();//可用时段
            var checkLimitedHoursR = $('input:radio[name="checkLimitedHoursR"]:checked').val();
            var checkLimitedHours = "";//该票设置可用总次数 -1表示不限 其他：限次
            if(checkLimitedHoursR == "0"){
                checkLimitedHours = "-1";
            } else if (checkLimitedHoursR == "1"){
                checkLimitedHours = $.trim($("#checkLimitedHours").val());
            }

            var shieldDate = shieldDateArray.toString(); //不可用日期
            // 取得HTML内容html = editor.html();
            orderDescriptionEditor.sync();
            refundDescriptionEditor.sync();
            if($('#orderDescription').val() == "") {
                alert("请填写订票说明");
                return;
            }
            if($('#refundDescription').val() == "") {
                alert("请填写退票说明");
                return;
            }
            var orderDescription = orderDescriptionEditor.html();
            var refundDescription = refundDescriptionEditor.html();
            var sellPrice = $.trim($("#sellPrice").val());
            var costPrice = $.trim($("#costPrice").val());
            var storePrice = $.trim($("#storePrice").val());
            var discount = $('input:radio[name="discount"]:checked').val();//是否参与折扣 0：不参与 1：参与
            var reqParam = {
                "ticketType":ticketType,
                mainStadiumID:mainStadiumID,
                subStadiumID:subStadiumID,
                ticketName:ticketName,
                merchantId:merchantId,
                sellWay:sellWay,
                orderEffectiveType:orderEffectiveType,
                orderFixDay:orderFixDay,
                orderEffectiveStartTime:orderEffectiveStartTime,
                orderEffectiveEndTime:orderEffectiveEndTime,
                orderRefundRule:orderRefundRule,
                checkTicketType:checkTicketType,
                checkTicketWay:checkTicketWay,
                checkTicketAvailableTimes:fixAvailableTimes,
                checkDailyLimitedTimes:checkDailyLimitedTimes,
                checkLimitedDateType:checkLimitedDateType,
                checkLimitedWeekDetails:checkLimitedWeekDetails,
                checkUseableTime:checkUseableTime,
                checkLimitedHours:checkLimitedHours,
                shieldDate:shieldDate,
                orderDescription:orderDescription,
                refundDescription:refundDescription,
                sellPrice:sellPrice,
                costPrice:costPrice,
                storePrice:storePrice,
                discount:discount,
                classify:classify
            };
            $.ajax({
                url:'/yearstrategyticket/addYearStrategyTicket.json',
                type:'POST', //GET
                data:reqParam,
                dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
                success:function(result){
                    if("000000" == result.code) {
                        alert("添加成功~");
                       window.location.reload(true);
                    }
                    //console.log(result);
                },
                error:function(result){
                    alert("添加失败~");
                }
            });
        }
    }
    $('#addForm').validate(valConfig);
    $("#ticketName").focus(function(){
        $(this).val($("#subStadiumID").find("option:selected").text());
    });
    //给主场馆菜单绑定选择改变时同时改变子菜单的内容
    $("#mainStadiumID").change(function(){
        //更新子场馆
        updateSubStadiumList($("#mainStadiumID").val());
        //更新合作商户
        updateMerchantList($("#mainStadiumID").val());
    });
    $('#mainStadiumID').trigger('change');
});
/**
 * 更新子场馆列表
 */
function updateSubStadiumList(mainStadiumId) {
    $.ajax({
        url:'/yearstrategyticket/getSubStadiumListByMainId.json',
        type:'POST', //GET
        data:{"mainStadiumId":mainStadiumId},
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(result){
            if("000000" == result.code) {
                var items = result.result.subStadiumList;
                $("#subStadiumID").empty();
                $.each(items,function(i,n){
                    $("#subStadiumID").append("<option value=\"" + n.id + "\" classify=\""+ n.classify+"\">"+n.name+"</option>");
                });
                //alert("添加成功~");
            } else {
                alert("添加失败~");
            }
            //console.log(result);
        },
        error:function(result){
            alert("添加失败~");
        }
    })
}

/**
 * 更新合作商户列表
 */
function updateMerchantList(mainStadiumId) {
    $.ajax({
        url:'/MerchantController/getMerchantListByParam.json',
        type:'POST', //GET
        data:{"mainStadiumId":mainStadiumId},
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(result){
            if("000000" == result.code) {
                var items = result.result;
                $("#merchantId").empty();
                if(0 == items.length){
                    $("#merchantId").append("<option value=\"\"  value=\"\">无合作商户</option>");
                } else {
                    $.each(items,function(i,n){
                        $("#merchantId").append("<option value=\"" + n.id + "\">"+n.merchant_name+"</option>");
                    });
                }
            } else {
                layer.msg("更新合作商户列表失败~");
            }
        },
        error:function(result){
            layer.msg("更新合作商户列表失败~");
        }
    })
}