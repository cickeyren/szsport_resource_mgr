/**
 * Created by wangw on 2017/2/20.
 */
$(function () {
    /**
     * 新增设备页面
     */
    $("#addEquipmentBtn").on('click', function () {
        window.location.href = "/equipment/addEquipment.html";
    })

    //给主场馆菜单绑定选择改变时同时改变子菜单的内容
    $("#mainStadium").change(function(){
        updateSubStadiumList($("#mainStadium").val());
    });

    /**
     * 新增设备
     */
    $("#saveEquipmentBtn").on('click', function () {
        var equipmentId = $("#equipmentId").val();
        var reg  = /^[\d]{11}$/;
        if(reg.test(equipmentId)){
            //子场馆选择
            var subStadiumArray = [];//子场馆集合
            $('input[name="subStadium"]:checkbox').each(function(){
                if ($(this).prop("checked")) {
                    subStadiumArray.push($(this).prop('value'));
                }
            });
            var subStadium = subStadiumArray.toString();
            $.ajax({
                url:'/equipment/addEquipment.json',
                type:'POST',
                data:{
                    "equipmentId":equipmentId,
                    "equipmentType":$("#equipmentType").val(),
                    "mainStadium":$("#mainStadium").val(),
                    "subStadium":subStadium,
                    "status":$('input[name="status"]:checked').val()
                },
                dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
                success:function(result){
                    if("000000" == result.code) {
                        layer.msg("添加设备成功~");
                    } else {
                        layer.msg(result.result);
                    }
                },
                error:function(result){
                    layer.msg("添加设备失败~");
                }
            });
        } else {
            layer.msg("设备编号需为十一位数字");
        }
    })

    /**
     * 取消新增设备
     */
    $("#cancelAddBtn").on('click',function () {
        window.location.href = "equipmentList.html";
    })

    /**
     * 编辑设备信息
     */
    $("#editEquipmentBtn").on('click', function () {
        //设备编号必须是11位数字
        var equipmentId = $("#equipmentId").val();
        var reg  = /^[\d]{11}$/;
        if(reg.test(equipmentId)){
            //子场馆选择
            var subStadiumArray = [];//子场馆集合
            $('input[name="subStadium"]:checkbox').each(function(){
                if ($(this).prop("checked")) {
                    subStadiumArray.push($(this).prop('value'));
                }
            });
            var subStadium = subStadiumArray.toString();
            $.ajax({
                url:'/equipment/updateEquipment.json',
                type:'POST',
                data:{
                    "id":$("#id").val(),
                    "equipmentId":$("#equipmentId").val(),
                    "equipmentType":$("#equipmentType").val(),
                    "mainStadium":$("#mainStadium").val(),
                    "subStadium":subStadium,
                    "status":$('input[name="status"]:checked').val()
                },
                dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
                success:function(result){
                    if("000000" == result.code) {
                        layer.msg("编辑设备成功~");
                    }
                },
                error:function(result){
                    layer.msg("编辑设备失败~");
                }
            });
        } else {
            layer.msg("设备编号需为十一位数字");
        }
    })

    /**
     * 取消编辑设备信息
     */
    $("#cancelEditBtn").on('click', function () {
        window.location.href = "equipmentList.html";
    })
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
                $("#subStadiumDiv").empty();
                if(0 == items.length){
                    $("#subStadiumDiv").append("无子场馆");
                } else {
                    $.each(items,function(i,n){
                        $("#subStadiumDiv").append("<input type='checkbox' id='subStadium' name='subStadium' value=\"" + n.id + "\" classify=\""+ n.classify+"\">"+n.name);
                    });
                }
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
 * 删除设备
 * @param id
 */
function delEquipment(id) {
    /**
     * 提示是否删除设备信息
     */
    layer.confirm('您确认是否删除此设备？', {
        btn: ['删除','取消'], //按钮
        shade: false //不显示遮罩
    }, function(){
        $.ajax({
            url:'/equipment/delEquipment.json',
            type:'POST',
            data:{
                "id":id
            },
            dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            success:function(result){
                if("000000" == result.code) {
                    layer.msg("删除设备成功~");
                    //刷新当前页面
                    location.reload();
                } else {
                    layer.msg("删除设备失败~");
                }
            },
            error:function(result){
                layer.msg("删除设备失败~");
            }
        })
    }, function(){
        //layer.msg('取消成功', {shift: 6});
    });
}
