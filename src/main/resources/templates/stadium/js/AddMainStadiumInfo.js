/**
 * Created by xuxueyuan on 2017/2/15.
 */
$(function(){
    //获取省-市-区三级级联下拉列表
    getThreeList();
});

function getProvienceList(){
    //获取省级列表
    $.ajax({
        type: "get",
        url: "",
        dataType: "json",
        success: function (data) {
            alert('请求成功');
        },
        error: function (msg) {
        }
    });
}

function submitTheForm() {
    var paramMap = {
        //场馆名称
        mainStadiumName: $("#mainStadiumName").val(),
        //省份
        provienceSelected: "",
        //市
        citySelected: "",
        //区域
        zoneSelected: "",
        //商圈
        tradeArea: "",
        //地址
        address: "",
        //电话
        telephone: "",
        //开放时间
        openTime: "",
        //简介
        introduction: "",
        //经度
        lng: "",
        //纬度
        lat: "",
        //状态
        stadiumStatus: ""
    }


    //对必填项进行非空验证
    if ("" == mainStadiumName || null == mainStadiumName || "" == address || null == address || "" == openTime || null == openTime) {
        alert();
        return false;
    }

    $.ajax({
        type: "get",
        url:"",
        data:paramMap,
        dataType:'json',
        success:function(msg){
            if(msg=='1'){

            }
        }
    })
}