/**
 * Created by wangw on 2017/4/20.
 */
$(function () {
    //子场馆全选控制
    $("#subStadiumCheckAll").click(function(){
        $("input[name = subStadiumCheck]:checkbox").prop("checked", $("#subStadiumCheckAll").prop("checked"));
    })

    //表单配置
    var valConfig = {
        rules: {},
        messages: {},
        submitHandler: function () {
            var mainStadiumId = $.trim($("#mainStadiumId").val());//主场馆编号
            var merchantId = $.trim($("#merchantId").val());//合作商户编号

            var loginIdArray = [];//选择用户
            $('input[name="loginIdCheck"]:checkbox').each(function(){
                if ($(this).prop("checked")) {
                    loginIdArray.push($(this).prop('value'));
                }
            });
            var loginId = loginIdArray.toString();//选择用户

            var subStadiumArray = [];//选择子场馆
            $('input[name="subStadiumCheck"]:checkbox').each(function(){
                if ($(this).prop("checked")) {
                    subStadiumArray.push($(this).prop('value'));
                }
            });
            var subStadiumId = subStadiumArray.toString();//选择子场馆

            var reqParam = {
                mainStadiumId:mainStadiumId,
                merchantId:merchantId,
                loginId:loginId,
                subStadiumId:subStadiumId
            };
            if(loginId == ""){
                layer.msg("请选择用户");
            }else if(subStadiumId == ""){
                layer.msg("请选择子场馆")
            } else {
                $.ajax({
                    url:'/merchantAccount/addMerchantAccount.json',
                    type:'POST', //GET
                    data:reqParam,
                    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
                    success:function(result){
                        if("000000" == result.code) {
                            layer.msg(result.result);
                        }
                    },
                    error:function(result){
                        layer.msg("添加合作商户账户失败~");
                    }
                });
            }
        }
    };
    $("#addMerchantAccountForm").validate(valConfig);
});