/**
 * Created by wangw on 2017/4/5.
 */
$(function () {
    // 关闭过滤模式，保留所有标签
    KindEditor.options.filterMode = false;

    //初始化富文本
    var remarkEditor;
    KindEditor.ready(function(K) {
        remarkEditor = K.create('textarea[name="remark"]', {
            resizeType : 1,
            allowPreviewEmoticons : false,
            filterMode : false

        });
    });

    //表单配置
    var valConfig = {
        rules: {},
        messages: {},
        submitHandler: function () {
            var id = $("#id").val();//窗口支付ID
            var mainStadium = $("#mainStadium").val();//主场馆ID
            var merchant = $("#merchant").val();//合作商户
            if(undefined == merchant || "" == merchant) {
                alert("请选择合作商户~");
                return;
            }
            var payment = $("#payment").val();//支付方式
            // 取得HTML内容html = editor.html();
            remarkEditor.sync();
            /*if($('#remark').val() == "") {
                alert("请填写备注说明");
                return;
            }*/
            var remark = remarkEditor.html();

            var reqParam = {
                id:id,
                mainStadium:mainStadium,
                merchant:merchant,
                payment:payment,
                remark:remark
            };
            $.ajax({
                url:'/windowPayment/editWindowPayment.json',
                type:'POST', //GET
                data:reqParam,
                dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
                success:function(result){
                    if("000000" == result.code) {
                        layer.msg(result.result);
                    } else {
                        layer.msg(result.message);
                    }
                },
                error:function(result){
                    layer.msg("编辑窗口支付信息失败~");
                }
            });
        }
    }
    $('#editWindowPaymentForm').validate(valConfig);
});