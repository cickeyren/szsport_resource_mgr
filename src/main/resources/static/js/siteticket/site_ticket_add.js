/**
 * Created by wangw on 2017/2/23.
 */
$(function () {
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
        });
    });

    //给主场馆菜单绑定选择改变时同时改变子菜单的内容
    $("#mainStadium").change(function(){
        updateSubStadiumList($("#mainStadium").val());
    });
    $('#mainStadium').trigger('change');

    //表单配置
    var valConfig = {
        rules: {
            ticketName: {
                required: true,
                maxlength: 100
            },
            beforeOpenTime: {
                digits: true
            },
            afterOpenTime: {
                digits: true
            },
            beforeCloseTime: {
                digits: true
            },
            reserveTime: {
                required: true,
                digits:true
            },
            availableStartTime: {
                required: true
            },
            availableEndTime: {
                required: true
            },
            noRefundTime: {
                digits:true
            },
            sellWay: {
                required: true
            },
            minimumTimeLimit: {
                digits:true
            },
            siteNumLimit: {
                digits:true
            },
            checkTicketType: {
                required: true
            },
            checkTicketWay: {
                required: true
            },
            approachTime: {
                digits:true
            },
            departureTime: {
                digits:true
            },
            peopleNumLimit: {
                digits:true
            },
            orderDescription: {
                required: true
            },
            refundDescription: {
                required: true
            }
        },
        messages: {
            ticketName: {
                required: "请填写门票名称"
            },
            beforeOpenTime: {
                digits: "请输入有效的数字"
            },
            afterOpenTime: {
                digits: "请输入有效的数字"
            },
            beforeCloseTime: {
                digits: "请输入有效的数字"
            },
            reserveTime: {
                required: "请填写可预约多少日内门票",
                digits: "请输入有效的数字"
            },
            availableStartTime: {
                required: "请输入生效起始时间"
            },
            availableEndTime: {
                required: "请输入生效结束时间"
            },
            noRefundTime: {
                digits: "请输入有效的数字"
            },
            minimumTimeLimit: {
                digits: "请输入有效的数字"
            },
            siteNumLimit: {
                digits: "请输入有效的数字"
            },
            approachTime: {
                digits: "请输入有效的数字"
            },
            departureTime: {
                digits: "请输入有效的数字"
            },
            peopleNumLimit: {
                digits: "请输入有效的数字"
            },
            orderDescription: {
                required: "请填写预订说明"
            },
            refundDescription: {
                required: "请填写退票说明"
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
            var checkTicketType = checkTicketTypeArray.toString();//验票凭证
            var checkTicketWayArray = [];
            $('input[name="checkTicketWay"]:checkbox').each(function(){
                if ($(this).prop("checked")) {
                    checkTicketWayArray.push($(this).prop('value'));
                }
            });
            var checkTicketWay = checkTicketWayArray.toString();//验票渠道
            var approachTime = $.trim($("#approachTime").val());//可提前多少分钟进场
            var departureTime = $.trim($("#departureTime").val());//可退后多少分钟离场
            var peopleNumLimit = $.trim($("#peopleNumLimit").val());//限用人数
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
                sellWay:sellWay,
                minimumTimeLimit:minimumTimeLimit,
                siteNumLimit:siteNumLimit,
                checkTicketType:checkTicketType,
                checkTicketWay:checkTicketWay,
                approachTime:approachTime,
                departureTime:departureTime,
                peopleNumLimit:peopleNumLimit,
                orderDescription:orderDescription,
                refundDescription:refundDescription
            };
            $.ajax({
                url:'/siteTicket/addSiteTicketBasicInfo.json',
                type:'POST', //GET
                data:reqParam,
                dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
                success:function(result){
                    if("000000" == result.code) {
                        layer.msg("添加场地票基本信息成功~");
                        window.location.reload(true);
                    }
                },
                error:function(result){
                    layer.msg("添加场地票基本信息失败~");
                }
            });
        }
    }
    $('#addSiteTicketBasicInfoForm').validate(valConfig);
});

/**
 * 更新子场馆列表
 */
function updateSubStadiumList(mainStadiumId) {
    $.ajax({
        url:'/equipment/getSubStadiumByParentId.json',
        type:'POST', //GET
        data:{"mainStadiumId":mainStadiumId},
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(result){
            if("000000" == result.code) {
                var items = result.result;
                $("#subStadium").empty();
                if(0 == items.length){
                    $("#subStadium").append("<option value=\"\"  value=\"\">无子场馆</option>");
                } else {
                    $.each(items,function(i,n){
                        $("#subStadium").append("<option value=\"" + n.id + "\" classify=\""+ n.classify+"\">"+n.name+"</option>");
                    });
                }
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