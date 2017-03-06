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
        $("input[name = fieldCheck]:checkbox").attr("checked", $("#siteCheckAll").prop("checked"));
    })

    //日期每月全选控制
    $("#monthCheckAll").click(function(){
        $("input[name = monthCheck]:checkbox").attr("checked", $("#monthCheckAll").prop("checked"));
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

    //时段全选控制
    $("#timeIntervalCheckAll").click(function(){
        $("input[name = timeIntervalCheck]:checkbox").attr("checked", $("#timeIntervalCheckAll").prop("checked"));
    })

    //表单配置
    var valConfig = {
        rules: {
            ticketName: {
                required: true,
                maxlength: 100
            },
            site:{
                required: true
            },
            sellPrice: {
                required: true,
                digits: true
            },
            costPrice: {
                required: true,
                digits: true
            },
            storePrice: {
                required: true,
                digits: true
            }
        },
        messages: {
            strategyName: {
                required: "请填写策略名称"
            },
            site:{
                required: "请选择场地"
            },
            sellPrice: {
                digits: "请输入有效的数字"
            },
            costPrice: {
                digits: "请输入有效的数字"
            },
            storePrice: {
                digits: "请输入有效的数字"
            }
        },
        submitHandler: function () {
            var ticketType = $("#ticketType").val();//套票类型
            var mainStadium = $("#mainStadium").val();//主场馆ID
            if(undefined == mainStadium || "" == mainStadium) {
                alert("请选择正确的主场馆内容~");
                return;
            }
            var subStadium = $("#subStadium").val();//子场馆ID
            if(undefined == subStadium || "" == subStadium) {
                alert("请选择正确的子场馆内容~");
                return;
            }
            var classify = "";//子场馆分类
            $('#subStadium option').each(function(){
                if ($(this).prop("selected")) {
                    classify = $(this).attr('classify');
                }
            });
            var ticketName = $.trim($("#ticketName").val());//门票名称
            var merchant = $.trim($("#merchant").val());//合作商
            var stopOrderType = $('input:radio[name="stopOrderType"]:checked').val();//停止预订
            var beforeOpenTime = $.trim($("#beforeOpenTime").val());//开场前停止预订时间
            var afterOpenTime = $.trim($("#afterOpenTime").val());//开场后停止预订时间
            var beforeCloseTime = $.trim($("#beforeCloseTime").val());//闭场前停止预订时间
            var reserveTime = $.trim($("#reserveTime").val());//可预订时间
            var availableStartTime = $.trim($("#availableStartTime").val());//生效开始时间
            var availableEndTime = $.trim($("#availableEndTime").val());//生效结束时间
            var orderRefundRule = $('input:radio[name="orderRefundRule"]:checked').val();//退款规则 0：不可退 1：随时退 2：条件退
            var noRefundTime = $.trim($("#noRefundTime").val());//开场前多少小时不可退
            var sellWayArray = [];//售卖渠道
            $('input[name="sellWay"]:checkbox').each(function(){
                if ($(this).prop("checked")) {
                    sellWayArray.push($(this).prop('value'));
                }
            });
            var sellWay = sellWayArray.toString();//售卖渠道
            var minimumTimeLimit = $.trim($("#minimumTimeLimit").val());//起订时限
            var siteNumLimit = $.trim($("#siteNumLimit").val());//限订场次数
            var checkTicketTypeArray=[];
            $('input[name="checkTicketType"]:checkbox').each(function(){
                if ($(this).prop("checked")) {
                    checkTicketTypeArray.push($(this).prop('value'));
                }
            });
            var checkTicketType = checkTicketTypeArray.toString();

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
                ticketType:ticketType,
                mainStadium:mainStadium,
                subStadium:subStadium,
                classify:classify,
                ticketName:ticketName,
                merchant:merchant,
                stopOrderType:stopOrderType,
                beforeOpenTime:beforeOpenTime,
                afterOpenTime:afterOpenTime,
                beforeCloseTime:beforeCloseTime,
                reserveTime:reserveTime,
                availableStartTime:availableStartTime,
                availableEndTime:availableEndTime,
                orderRefundRule:orderRefundRule,
                noRefundTime:noRefundTime,
                timeCode:timeCode,
                timeInterval:timeInterval,
                sellPrice:sellPrice,
                costPrice:costPrice,
                storePrice:storePrice
            };
            $.ajax({
                url:'/siteTicket/addSiteTicketBasicInfo.json',
                type:'POST', //GET
                data:reqParam,
                dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
                success:function(result){
                    if("000000" == result.code) {
                        layer.msg("添加场地票策略成功~");
                        window.location.reload(true);
                    }
                },
                error:function(result){
                    layer.msg("添加场地票策略失败~");
                }
            });
        }
    }
    $('#addStragrey').validate(valConfig);
});
