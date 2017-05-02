/**
 * Created by admin on 2017/4/24.
 */

var imguploadFile = function () {
    /*基础配置*/
    var el = 'body';
    var addLay = null, updateLay = null;
    var descEditor;
    /*页面初始化和事件绑定*/
    function bindEvents() {
        $(el).undelegate()
            .delegate('#uploadImg', 'change', uploadImg)

    }

    /*图片上传*/
    function uploadImg(field) {
        var $field = $(field.target);

        var $parent = $field.parent();
        var filePath = $field[0].value;
        $("#imgUrl").val(filePath);
        var fileExt = filePath.substring(filePath.lastIndexOf("."))
            .toLowerCase();

        if (!checkFileExt(fileExt)) {
            layer.msg("您上传的文件不是图片,请重新上传！");
            //img.value = "";
            return;
        }
        if ($field[0].files[0].size > 1048576) {
            layer.msg("最多上传1M图片,请重新上传！");
            return;
        }
        var $form = $('<form enctype="multipart/form-data" style="display:none"></form>').append($field);
        $('body').append($form);
        $form.ajaxSubmit({
            type: 'POST',
            url: '/upload/imageUpload',
            dataType: "json",
            success: function (t) {
                $form.remove();
                if ("000000" == result.code) {
                    layer.alert("上传成功");
                    $parent.find('img').attr('src', t.returnUrl);
                    $parent.find("img").before($field);
                } else {
                    layer.msg(result.result);
                    $parent.append($field);
                }
            },
            error: function () {
                $parent.prepend(field);
            }
        });

    }

    /*页面初始化*/
    function init() {
        bindEvents();
    }

    /*end 页面初始化*/
    init();

    //文件后缀检测
    function checkFileExt(ext) {
        if (!ext.match(/.jpg|.gif|.png|.bmp/i)) {
            return false;
        }
        return true;
    }
};


