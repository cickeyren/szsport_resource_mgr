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

    //时段全选控制
    $("#timeIntervalCheckAll").click(function(){
        $("input[name = timeIntervalCheck]:checkbox").prop("checked", $("#timeIntervalCheckAll").prop("checked"));
    })
});
