/**
 * Created by wangw on 2017/2/23.
 */
$(function () {
    //日期类型控制隐藏内容
    $('input:radio[name="dateType"]').change(function(){
        $("#weekDiv").hide();
        $("#monthDiv").hide();
        $("#specificDateDiv").hide();
        if (1 == $(this).val()) {
            $("#weekDiv").show();
        } else if(2 == $(this).val()){
            $("#monthDiv").show();
        } else if(3 == $(this).val()){
            $("#specificDateDiv").show();
        }
    });

    //场地全选控制
    $("#siteCheckAll").click(function(){
        $("input[name = fieldCheck]:checkbox").prop("checked", $("#siteCheckAll").prop("checked"));
    })

    //日期每月全选控制
    $("#monthCheckAll").click(function(){
        $("input[name = monthCheck]:checkbox").prop("checked", $("#monthCheckAll").prop("checked"));
    })

    var specificDateArray = [];//指定日数组
    //点击指定日中的添加按钮
    $("#addSpecificDateBtn").click(function(){
        var specificStartTime = $.trim($("#specificStartTime").val());
        var specificEndTime = $.trim($("#specificEndTime").val());
        if(undefined == specificStartTime || "" == specificStartTime) {
            alert("请填写起始不可用日期");
            return;
        }
        if(undefined == specificEndTime || "" == specificEndTime) {
            alert("请填写结束不可用时间");
            return;
        }
        var specificStartTimeKey = specificStartTime+"$"+specificEndTime;
        var specificStartTimeValue = specificStartTime+"至"+specificEndTime;
        if(specificDateArray.contains(specificStartTimeKey)) {
            alert("该指定日已经添加，请重新选择~");
            return;
        }
        specificDateArray.push(specificStartTimeKey);
        $("#specificDateSelects").prepend('<option value="'+specificStartTimeKey+'">'+specificStartTimeValue+"</option>");
    });
    //不可用日期段条目双击删除
    $("#specificDateSelects").dblclick(function(){
        var specificStartTimeKey =  $("option:selected",this).attr("value");
        $("option:selected",this).remove();
        specificDateArray.remove(specificStartTimeKey);
        //console.log(checkUseableTimeArray);
    });

    //时段策略改变时同时改变时段信息
    $("#timeCode").change(function(){
        updateTimeIntervalList($("#timeCode").val());
    });

    //时段全选控制
    $("#timeIntervalCheckAll").click(function(){
        $("input[name = timeIntervalCheck]:checkbox").prop("checked", $("#timeIntervalCheckAll").prop("checked"));
    })

    //表单配置
    var valConfig = {
        rules: {
            strategyName: {
                required: true,
                maxlength: 100
            },
            fieldCheck:{
                required: true
            },
            timeCode:{
                required: true
            },
            timeIntervalCheck:{
                required: true
            },
            sellPrice: {
                required: true,
                number: true
            },
            costPrice: {
                required: true,
                number: true
            },
            storePrice: {
                required: true,
                number: true
            }
        },
        messages: {
            strategyName: {
                required: "请填写策略名称"
            },
            fieldCheck:{
                required: "请选择场地"
            },
            timeCode:{
                required: "请创建时段策略"
            },
            timeIntervalCheck:{
                required: "请选择时段"
            },
            sellPrice: {
                required: "请填写销售价",
                number: "请输入有效的数字"
            },
            costPrice: {
                required: "请填写成本价",
                number: "请输入有效的数字"
            },
            storePrice: {
                required: "请填写门市价",
                number: "请输入有效的数字"
            }
        },
        submitHandler: function () {
            var ticketId = $.trim($("#ticketId").val());//场地票编号
            var subStadium = $.trim($("#subStadium").val());//子场馆编号
            var strategyName = $.trim($("#strategyName").val());//策略名称
            var siteArray = [];//选择场地
            $('input[name="fieldCheck"]:checkbox').each(function(){
                if ($(this).prop("checked")) {
                    siteArray.push($(this).prop('value'));
                }
            });
            var site = siteArray.toString();//选择场地

            var dateType = $('input:radio[name="dateType"]:checked').val();//日期类型
            var weekDetailsArray = [];//日期类型每周
            $('input[name="weekDetails"]:checkbox').each(function(){
                if ($(this).prop("checked")) {
                    weekDetailsArray.push($(this).prop('value'));
                }
            });
            var weekDetails = weekDetailsArray.toString();//日期类型每周

            var monthDetailsArray = [];//日期类型每月
            $('input[name="monthDetails"]:checkbox').each(function(){
                if ($(this).prop("checked")) {
                    monthDetailsArray.push($(this).prop('value'));
                }
            });
            var monthDetails = monthDetailsArray.toString();//日期类型每周

            var specificDate = specificDateArray.toString();//日期类型指定日

            var timeCode = $("#timeCode").val();//选择时段
            var timeIntervalArray = [];
            $('input[name="timeIntervalCheck"]:checkbox').each(function(){
                if ($(this).prop("checked")) {
                    timeIntervalArray.push($(this).prop('value'));
                }
            });
            var timeInterval = timeIntervalArray.toString();//时段详细
            var sellPrice = $.trim($("#sellPrice").val());//销售价
            var costPrice = $.trim($("#costPrice").val());//成本价
            var storePrice = $.trim($("#storePrice").val());//门市价

            var reqParam = {
                ticketId:ticketId,
                strategyName:strategyName,
                subStadium:subStadium,
                site:site,
                dateType:dateType,
                weekDetails:weekDetails,
                monthDetails:monthDetails,
                specificDate:specificDate,
                timeCode:timeCode,
                timeInterval:timeInterval,
                sellPrice:sellPrice,
                costPrice:costPrice,
                storePrice:storePrice
            };
            if(dateType == 1 && weekDetails == ""){
                layer.msg("请选择日期类型");
            } else if(dateType == 2 && monthDetails == ""){
                layer.msg("请选择日期类型")
            } else if(dateType == 3 && specificDate  == ""){
                layer.msg("请选择日期类型")
            } else {
                $.ajax({
                    url:'/siteTicket/addStrategyInfo.json',
                    type:'POST', //GET
                    data:reqParam,
                    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
                    success:function(result){
                        if("000000" == result.code) {
                            layer.msg(result.result);
                            window.location.reload(true);
                        }
                    },
                    error:function(result){
                        layer.msg("添加场地票策略失败~");
                    }
                });
            }
        }
    };
    $("#addSiteTicketStrategyForm").validate(valConfig);
});

/**
 * 更新时段列表信息
 */
function updateTimeIntervalList(timeCode) {
    $.ajax({
        url:'/siteTicket/getTimeIntervalByTimeCode.json',
        type:'POST', //GET
        data:{"timeCode":timeCode},
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(result){
            if("000000" == result.code) {
                var items = result.result;
                $("#timeIntervalList").empty();
                if(0 == items.length){
                    $("#timeIntervalList").append("");
                } else {
                    $.each(items,function(i,n){
                        $("#timeIntervalList").append("<input type='checkbox' id='timeIntervalCheck' name='timeIntervalCheck' value=\"" + n.id + "\">"+n.time_inter);
                    });
                }
            } else {
                layer.msg("更新时段信息失败~");
            }
        },
        error:function(result){
            layer.msg("更新时段信息失败~");
        }
    })
}