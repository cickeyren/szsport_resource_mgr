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

    /**
     * 新增设备
     */
    $("#saveEquipmentBtn").on('click', function () {
        $.ajax({
            url:'/equipment/addEquipment.json',
            type:'POST',
            data:{
                "equipmentId":$("#equipmentId").val(),
                "equipmentType":$("#equipmentType").val(),
                "mainStadium":$("#mainStadium").val(),
                "subStadium":$("#subStadium").val(),
                "status":$('input[name="status"]:checked').val()
            },
            dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            success:function(result){
                if("000000" == result.code) {
                    layer.msg("添加设备成功~");
                }
            },
            error:function(result){
                layer.msg("添加设备失败~");
            }
        })
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
        $.ajax({
            url:'/equipment/updateEquipment.json',
            type:'POST',
            data:{
                "id":$("#id").val(),
                "equipmentId":$("#equipmentId").val(),
                "equipmentType":$("#equipmentType").val(),
                "mainStadium":$("#mainStadium").val(),
                "subStadium":$("#subStadium").val(),
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
        })
    })

    /**
     * 取消编辑设备信息
     */
    $("#cancelEditBtn").on('click', function () {
        window.location.href = "equipmentList.html";
    })
});

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
