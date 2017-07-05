/**
 * Created by wanggw on 2017/2/27.
 */

$(function () {
    var valConfig = {
        ignore: "",
        onsubmit:true,// 是否在提交是验证
        onfocusout:true,// 是否在获取焦点时验证
        rules: {
            name: {　　//要对应相对应的input中的name属性
                required: true
            },
            class_long: {
                required: true,
                number:true
            },
            class_times: {
                required: true,
                number:true
            },
            student_num: {
                required: true,
                number:true
            },
            leantime_type: {
                required: true
            },
            fee: {
                required: true,
                number:true
            }
            ,
            class_times_json : {
                required: true
            }
        },
        messages:{　　　　//验证错误信息
            name: {
                required: "请输入班名"
            },
            class_long: {
                required: "请输入课时",
                number:"请输入合法数字"
            },
            class_times: {
                required: "请输入上课次数",
                number:"请输入合法数字"
            },
            student_num: {
                required: "请输入每班人数",
                number:"请输入合法数字"
            },
            leantime_type: {
                required: "请选择上课日期"
            },
            fee: {
                required: "请输入费用",
                number:"请输入合法数字"
            }
            ,
            class_times_json: {
                required: "请添加上课时段"
            }
        },
        errorPlacement: function(error, element) {
            var error_container = element.nextAll(".error_container");
            if(error_container.length>0){
                error_container.append(error);
            }else{
                error.insertAfter(element);
            }

        },
        submitHandler: function(form){
            doAdd();
        }
    };
    $('#addForm').validate(valConfig);

    var classTime_valConfig = {
        ignore: "",
        onsubmit:true,// 是否在提交是验证
        onfocusout:true,// 是否在获取焦点时验证
        rules: {
            time : {
                required: true,
                maxlength: 100
            }
            ,
            max_people : {
                required: true,
                valid_number : true
            }
            ,
            reserve_people : {
                required: true,
                valid_number : true
            }
        },
        messages:{
            time : {
                required: "请输入时间段"
            }
            ,
            max_people : {
                required: "请输入限报人数",
                valid_number:"请输入有效的限报人数"
            }
            ,
            reserve_people : {
                required: "请输入预留名额",
                valid_number:"请输入有效的预留名额"
            }
        },
        errorPlacement: function(error, element) {
            var error_container = element.nextAll(".error_container");
            if(error_container.length>0){
                error_container.append(error);
            }else{
                error.insertAfter(element);
            }

        },
        submitHandler: function(form){
        }
    };
    $('#classTimeForm').validate(classTime_valConfig);

    $("input[name='leantime_type']").on("click", function () {
        var leantime_type = $(this).val()||"";
        if(leantime_type=="1"){
            $('#lean_time').rules('add',{
                required: true,
                messages:{
                    required: '请选择上课日期'
                }
            });
        }else{
            $('#lean_time').val("");
            $('#lean_time').rules('remove');
            $('#lean_time').valid();
        }
    });

    //新增界面保存按钮点击事件 ---  返回主界面
    $("#cancelBtn").on("click", function () {
        var curriculum_id = $("#curriculumId").val()||"";
        window.location.href = "/curriculumController/curriculumClass.html?curriculumId=" + curriculum_id;
    });

    $("#addtime").on("click", function () {
        layer.open({
            type: 1,
            skin: 'layui-layer-demo', //样式类名
            shift: 2,
            title: '上课时间段',
            shade: false,
            closeBtn: 0,
            area: ['560px', '360px'], //宽高
            content: $('#addTimeDialogs'),
            btn: ['确定', '取消'],
            btn1: function () {
                try {
                    if(!$("#classTimeForm").valid()) {
                        return false;
                    }

                    var class_time_json = $("#class_times_json").val()||"[]";
                    var class_time_array = JSON.parse(class_time_json);
                    var class_time_array_size = class_time_array.length;
                    var td_no = new Date().getTime() + "";

                    var time = $("#time").val()||"";
                    var max_people = $("#max_people").val()||"";
                    var reserve_people = $("#reserve_people").val()||"";
                    var classObj = {};
                    classObj.td_no = td_no;
                    classObj.time = time;
                    classObj.max_people = max_people;
                    classObj.reserve_people = reserve_people;

                    class_time_array.push(classObj);

                    var temp = "";
                    temp += '<tr>' +
                        '<td>' + time + '</td>' +
                        '<td>' + max_people + '</td>' +
                        '<td>' + reserve_people + '</td>' +
                        '<td width="80px"><a onclick="editTime(this, \''+td_no+'\')">编辑</a>&nbsp;<a onclick="delTime(this, \''+td_no+'\')">删除</a></td>' +
                        '</tr>'
                    $("#class_time_table_body").append(temp);

                    $("#class_times_json").val(JSON.stringify(class_time_array));
                    $("#class_times_json").valid();
                    layer.closeAll();
                }catch(err){

                }
            },
            btn2: function () {
                clearClassTimeForm();
                layer.closeAll();
            }
        });
    })
});

function clearClassTimeForm(){
    // $("#time").val("");
    // $("#max_people").val("");
    // $("#reserve_people").val("");
    $("#classTimeForm").data('validator').resetForm();
}

function editTime(a, td_no) {
    var tds = $(a).parent().prevAll();

    var class_time_json = $("#class_times_json").val()||"[]";
    var class_time_array = JSON.parse(class_time_json);

    var classTimeObj = {};
    for(var i=0; i<class_time_array.length; i++){
        classTimeObj = class_time_array[i];
        if(classTimeObj.td_no == td_no){
            break;
        }
    }

    $("#time").val(classTimeObj.time);
    $("#max_people").val(classTimeObj.max_people);
    $("#reserve_people").val(classTimeObj.reserve_people);

    layer.open({
        type: 1,
        skin: 'layui-layer-demo', //样式类名
        shift: 2,
        title: '上课时间段',
        shade: false,
        closeBtn: 0,
        area: ['560px', '360px'], //宽高
        content: $('#addTimeDialogs'),
        btn: ['确定', '取消'],
        btn1: function () {
            if(!$("#classTimeForm").valid()) {
                return false;
            }
            var time = $("#time").val()||"";
            var max_people = $("#max_people").val()||"";
            var reserve_people = $("#reserve_people").val()||"";

            var class_time_json = $("#class_times_json").val()||"[]";
            var class_time_array = JSON.parse(class_time_json);

            var classTimeObj = {};
            for(var i=0; i<class_time_array.length; i++){
                classTimeObj = class_time_array[i];
                if(classTimeObj.td_no == td_no){
                    break;
                }
            }
            classTimeObj.time = time;
            classTimeObj.max_people = max_people;
            classTimeObj.reserve_people = reserve_people;
            $("#class_times_json").val(JSON.stringify(class_time_array));

            $(tds[2]).text(time);
            $(tds[1]).text(max_people);
            $(tds[0]).text(reserve_people);

            layer.closeAll();
        },
        btn2: function () {
            $("#classTimeForm").data('validator').resetForm();
            layer.closeAll();
        }
    });

}
function delTime(a, td_no) {
    var class_time_json = $("#class_times_json").val()||"[]";
    var class_time_array = JSON.parse(class_time_json);

    var classTimeNo = 'N';
    var classTimeObj = {};
    for(var i=0; i<class_time_array.length; i++){
        classTimeObj = class_time_array[i];
        if(classTimeObj.td_no == td_no){
            classTimeNo = i;
            break;
        }
    }
    if(classTimeNo!='N'){
        class_time_array.splice(classTimeNo,1);

        var class_time_json_new = JSON.stringify(class_time_array);
        class_time_json_new = class_time_json_new == "[]" ? "" : class_time_json_new;
        $("#class_times_json").val(class_time_json_new);

        $(a).parent().parent("tr").remove();
    }
}
var oprFlag = 0;
//新增页面添加数据
function doAdd() {
    try{
        if(oprFlag==1){
            return false;
        }else{
            oprFlag = 1;
        }
        //准备json数据
        var addJson = {};
        addJson.name = $("#name").val();
        addJson.class_long = $("#class_long").val();
        addJson.class_times = $("#class_times").val();
        addJson.student_num = $("#student_num").val();
        addJson.max_num = $("#max_num").val();
        addJson.leantime_type = $('input[name="leantime_type"]:checked').val();
        if(addJson.leantime_type=="1"){
            addJson.lean_time = $("#lean_time").val();
        }
        addJson.bm_time = $("#bm_time").val();
        addJson.bm_end = $("#bm_end").val();
        addJson.target = $("#target").val();
        addJson.content = $("#m_content").val();
        addJson.fee_code = $("#fee_code").val();
        addJson.fee = $("#fee").val();
        addJson.fee_mark = $("#fee_mark").val();
        addJson.curriculum_id = $("#curriculumId").val();

        var class_time_json = $("#class_times_json").val()||"[]";
        addJson.timess = class_time_json;

        $.ajax({
            url: '/curriculumController/doAddCurriculumClass.do',
            type: 'POST', //GET
            data: addJson,
            dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            success: function (result) {
                try{
                    if ("000000" == result.code) {
                        layer.msg("添加成功！");
                        setTimeout(function () {
                            var curriculum_id = $("#curriculumId").val()||"";
                            window.location.href = "/curriculumController/curriculumClass.html?curriculumId=" + curriculum_id;
                        }, 500);
                    } else {
                        oprFlag = 0;
                        layer.msg(result.message);
                    }
                }catch(err){
                    oprFlag = 0;
                }
            },
            error: function (result) {
                oprFlag = 0;
                layer.msg("添加失败！");
            }
        });
    }catch(err){
        oprFlag = 0;
    }
}