$(function () {
    layui.use(['layer'], function(){
        var layer = layui.layer;
        $('#register_btn').on('click',function () {
            var formData = $('#register_form').serialize();
            $.ajax({
                url:basePath+'/register',
                type:'post',
                data:formData,
                success:function (res) {
                    if (res.code === 0){
                        layer.msg("注册成功!");
                        setTimeout(function(){
                            window.location.href=basePath+"/login";
                        },1500);

                    }else{
                        layer.msg("注册失败"+res.code);
                    }

                }
            })
        });
    });

});