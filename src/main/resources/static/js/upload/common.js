/**
 * Created by wanggw
 */
var layer;
var appId;
var hostUrl = '@app.url@';
var hostUrl = 'http://moms.eastdc.cn:82/';
//var hostUrl = '';
$(function(){
    window.localHost = '@app.url@';
    window.photoDownload = '@photo.download.path@';
    //widget.appListClick();
    window.localHost = 'http://moms.eastdc.cn:82/';
    window.photoDownload = 'http://files.eastdc.cn:82';
    appId = $('#appList1', parent.document).attr('appId');
});
function fnCallback(res,callback){
    if(res.code == '60000'){
        window.parent.open(res.result,'_self');
    }else {
        callback(res);
    }
}
function ajaxJsonCall(url, data, callback) {
    var app_path = hostUrl+'/';
    $.post(app_path + url, data, function(res){
        fnCallback(res,callback)
    }, 'json');
}
function ajaxEvent(url, data, callback) {
    var app_path = hostUrl+'/';
    $.post(app_path + url, data,  function(res){
        fnCallback(res,callback)
    }, 'json');
}
function setCookie(JSESSIONID, _uid, _route_event_) {
    document.cookie="JSESSIONID="+JSESSIONID+"; path=/;expires = -1",
    document.cookie="_uid="+_uid+"; path=/;expires = -1",
    document.cookie="_route_event_="+_route_event_+"; path=/;expires = -1";
}
$(document).ajaxStart(function(){
    layer.closeAll('loading');
    layer.load(2,{shade:0.2, area: ['32px', '32px'], time: 10*1000});
});
$(document).ajaxStop(function(){
    layer.closeAll('loading');
});

//添加弹出框
var fadeOut = null;
var widget={
    showAddWidgetAttr: function(title,obj,w,h,obj1){
        $(obj).window({
            width:w,
            height:h,
            modal:true,
            title: title,
            closed:false,
            collapsible:false,
            minimizable:false,
            maximizable:false,
            top:($(window).height()-h)/2,
            left:($(window).width()-w)/2,
            onBeforeClose: function(){
                $(obj1).unbind("click");
            }
        }).show();
    },
    popAny:function (objContent, style) {
        if(fadeOut != null){
            clearTimeout(fadeOut);
            $('.popAnyWhere').find('p').text(objContent);
        }else{
            $("body").append("<div class='popAnyWhere'></div>");
            var boxContent="<p>"+objContent+"</p>";
            $(".popAnyWhere").removeClass("smile").removeClass("cry").addClass(style);
            $(".popAnyWhere").append(boxContent);
            var boxWidth=$(".popAnyWhere").width();
            var leftx=(document.body.clientWidth-boxWidth)/2+100+"px";
            var bottomx="20px";
            $(".popAnyWhere").attr("style","display:block; left:"+leftx+";bottom:"+bottomx);
        }
        fadeOut = setTimeout(this.popFadeout,2000);
    },
    popFadeout:function (){
        $('.popAnyWhere').fadeOut();
        $('.popAnyWhere').remove();
        fadeOut = null;
    },
    // 上传图片
    uploadImg:function(curId,curImgId,curHideId){
        if(this.uploadImgValidate(curId)){
            $.ajaxFileUpload({
                url: window.localHost+'/upload/imageUpload.do', //用于文件上传的服务器端请求地址
                secureuri: false, //是否需要安全协议，一般设置为false
                fileElementId: curId, //文件上传域的ID
                dataType: 'json', //返回值类型 一般设置为json
                type:'post',
                success: function (data, status)  //服务器成功响应处理函数
                {
                    if('OK' == data.status){
                        $("#"+curImgId).attr('src', window.photoDownload+data.result);
                        $("#"+curHideId).val(data.result);
                    } else {
                        alert(data.message);
                    }
                },
                error: function (data, status, curId)//服务器响应失败处理函数
                {alert("error"+curId);}
            });
        }

    },
    // 验证类型和大小
    uploadImgValidate:function(curId){
        var flag = true;
        var filepath=$('#'+curId).val();
        var extStart=filepath.lastIndexOf('.');
        var ext=filepath.substring(extStart,filepath.length).toUpperCase();
        if(ext != '.BMP' && ext!='.PNG' && ext!='.JPG' &&ext!='.JPEG'){
            alert('图片限于bmp,png,gif,jpeg,jpg格式');
            flag = false;
            return false;
        }
        var fileSize = 0;
        var target = document.getElementById(curId);
        var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
        var filemaxsize = 1024;//1M
        if (isIE && !target.files) {
            var fileSystem = new ActiveXObject('Scripting.FileSystemObject');
            if(!fileSystem.FileExists(filepath)){
                alert('图片不存在，请重新输入！');
                return false;
            }
            var file = fileSystem.GetFile (filepath);
            fileSize = file.Size;
        } else {
            fileSize = target.files[0].size;
        }
        var size = fileSize / 1024;
        if(size>filemaxsize){
            alert("附件大小不能大于"+filemaxsize/1024+"M！");
            target.value ="";
            flag = false;
        }
        if(size<=0){
            alert("附件大小不能为0M！");
            target.value ="";
            flag = false;
        }
        return flag;
    },
    appListClick:function () {
        ajaxJsonCall('appInfo/getAppList.json',
            {},
            function(data){
                var trNode="";
                if(data.status=='OK'){
                    if(data.result.length>0){
                        // 查找cookie中是否存在选中的App模块
                        var appIdCookie = $.cookie('appId-cookie'),
                            appNameCookie = $.cookie('appName-cookie');
                        var flag = false;
                        $.each(data.result, function(key, val){
                            trNode += '<li value="'+val.app_id+'" appName="'+val.app_name+'">' +
                                '<a href="javascript:;" title="'+val.app_name+'">'+val.app_name+'</a>' +
                                '</li>';
                            if(val.app_id == appIdCookie){
                                flag = true;
                            }
                        });
                        var $span = $('.select-area').find('span');
                        if(flag && appIdCookie && appNameCookie) {
                            $span.attr('appId', appIdCookie).html(appNameCookie);
                        }else {
                            $span.attr('appId',data.result[0].app_id).html(data.result[0].app_name);
                        } 
                        
                        $span = null;
                        trNode += '<li class="appManagePage"><a href="javascript:;">管理App</a></li>';
                    }
                    else{
                        trNode = '';
                    }
                }else{
                    trNode="";
                }
                $('.select-menu-area ul').html(trNode).mCustomScrollbar();
                renderSelectMenu('.select-area');
            }
        );
        $('.app-list1').on('click','.selected',function () {
            $('.app-list1 ul').show()
        });
        $('.app-list1').on('click','ul li',function () {
            $(this).addClass('selectedMe').siblings().removeClass('selectedMe');
            var clickVal = $(this).attr('appName');
            if($(this).hasClass('appManagePage')){
                $('#sidebar').find('.lv1').addClass('open').next().stop().slideDown();
                createDelDivObj.createDiv('tab_0101','html/management/manage-index.html','App管理',1);
                $('.listSelected').attr('appId',$(this).attr('value'));
                $('.listSelected').html( '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+$(this).text());
            }else{
                if(confirm("即将切换到"+clickVal+"管理界面，是否立即切换")){
                    $('#div_tab').html('');
                    $('#div_pannel').html('');
                    $('.first-step').removeClass('open').next().stop().slideUp();
                    $('.listSelected').attr('appId',$(this).attr('value'));
                    $('.listSelected').html('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+ $(this).text());

                }else{
                }
            }
            $('.app-list1 ul').hide();

        });
        $('.app-list1 ul').mouseleave(function(){
            $('.app-list1 ul').hide();
        });
    },
    checkInputValidate: function (opts) {
        var $inputs = $(opts.el).find('input[type="text"]'),
            validateOpts = opts.validate,
            param = {},
            isPass = true;
        $inputs.each(function () {
            var $this = $(this),
                sName = $this.attr('name'),
                sVal = $this.val();
            if(validateOpts[sName]) {
                if(validateOpts[sName].require && !sVal) {
                    $this.closest('td').find('.'+sName+'-notice').html(validateOpts[sName].message).show();
                    isPass = false;
                }
                else {
                    param[sName] = sVal;
                    $this.closest('td').find('.'+sName+'-notice').hide();
                }
            }
            $this = null;
        });
        if(opts.isClose && isPass){
            $(opts.el).window('close');
        }
        $inputs = null;
        return param;
    },
    //校验手机号
    checkPhone:function(){
        if($('#phone').val() == null || $('#phone').val() == '') {
            $("input[tipMsg]").val('请输入您要搜索的手机号码或座机号');
            if($("input[tipMsg]").val() != ""){
                var oldVal=$("input[tipMsg]").attr("tipMsg");
                if($("input[tipMsg]").val()==""){$("input[tipMsg]").attr("value",oldVal).css({"color":"#888"});}
                $("input[tipMsg]").css({"color":"#888"})     //灰色
                    .focus(function(){
                        if($("input[tipMsg]").val()!=oldVal){$("input[tipMsg]").css({"color":"#000"})}else{$("input[tipMsg]").val("").css({"color":"#888"})}
                    })
                    .blur(function(){
                        var phoneNum = $("input[tipMsg]").val();
                        if(phoneNum==null || phoneNum.trim() == ""){$("input[tipMsg]").val(oldVal).css({"color":"#888"})}
                        if(phoneNum!=null && phoneNum.trim() != "" && phoneNum.trim() != '请输入您要搜索的手机号码或座机号')
                        {
                            var reg=/^[0-9]+$/;
                            if (!reg.test(phoneNum.trim()))
                            {
                                alert("请输入正确电话号!");
                                $("input[tipMsg]").val(oldVal).css({"color":"#888"});
                            }
                        }
                    })
                    .keydown(function(){$("input[tipMsg]").css({"color":"#000"})});
            }
        }
    },
    //校验起止时间
    checkDate:function(){
        var startDate = $('#t_starttime').val();
        var endDate = $('#t_endtime').val();
        if(startDate && endDate){
            if(startDate > endDate){
                alert('结束时间不能早于起始时间！');
                $('#t_starttime').val('');
                $('#t_endtime').val('');
            }
        }
    }
};
Date.prototype.format = function(format) {
    /*
     * eg:format="yyyy-MM-dd hh:mm:ss";
     */
    var o = {
        "M+" :this.getMonth() + 1, // month
        "d+" :this.getDate(), // day
        "h+" :this.getHours(), // hour
        "m+" :this.getMinutes(), // minute
        "s+" :this.getSeconds(), // second
        "q+" :Math.floor((this.getMonth() + 3) / 3), // quarter
        "S" :this.getMilliseconds()
        // millisecond
    }

    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "")
            .substr(4 - RegExp.$1.length));
    }

    for ( var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
                : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
};