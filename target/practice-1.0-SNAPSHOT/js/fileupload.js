layui.use('upload', function() {
    var $ = layui.jquery
        , upload = layui.upload;

    //普通图片上传
    var uploadInst = upload.render({
        elem: '#upload_btn'
        , url: basePath+'/uploadImg'
        , before: function (obj) {
            //预读本地文件示例，不支持ie8
            obj.preview(function (index, file, result) {
                $('#demo1').attr('src', result); //图片链接（base64）
            });
        }
        , done: function (res) {
            //如果上传失败
            if (res.code === 0){
                return layer.msg('文件上传成功!');
            }
            if (res.code === 2) {
                return layer.msg('不支持该上传类型');
            }
            if (res.code === 3) {
                return layer.msg('文件为空');
            }else{
                return layer.msg('文件上传异常');

            }
            //上传成功
        }
        , error: function () {
            //演示失败状态，并实现重传
            var demoText = $('#demoText');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function () {
                uploadInst.upload();
            });
        }
    });
    //多图片上传
    upload.render({
        elem: '#test2'
        ,url: basePath+'/uploadImg'
        ,multiple: true
        ,before: function(obj){
            //预读本地文件示例，不支持ie8
            obj.preview(function(index, file, result){
                $('#demo2').append('<img width="150px" src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')
            });
        }
        ,done: function(res){
            //上传完毕
            //如果上传失败
            if (res.code === 0){
                return layer.msg('文件上传成功!');
            }
            if (res.code === 2) {
                return layer.msg('不支持该上传类型');
            }
            if (res.code === 3) {
                return layer.msg('文件为空');
            }else{
                return layer.msg('文件上传异常');

            }
        }
    });

    //选完文件后不自动上传
    upload.render({
        elem: '#test8'
        ,url: basePath+'/formupload'
        ,auto: false
        ,bindAction: '#test9'
        ,data:{
            username:function () {
                return $('#username').val()
            }
        }
        ,done: function(res){
            if (res.code === 0){
                return layer.msg('文件上传成功!');
            }
            if (res.code === 2) {
                return layer.msg('不支持该上传类型');
            }
            if (res.code === 3) {
                return layer.msg('文件为空');
            }else{
                return layer.msg('文件上传异常');

            }
        }
    });

//拖拽上传
    upload.render({
        elem: '#test10'
        ,url:  basePath+'/uploadImg'
        ,done: function(res){
            //如果上传失败
            if (res.code === 0){
                return layer.msg('文件上传成功!');
            }
            if (res.code === 2) {
                return layer.msg('不支持该上传类型');
            }
            if (res.code === 3) {
                return layer.msg('文件为空');
            }else{
                return layer.msg('文件上传异常');

            }
        }
    });



    //多文件列表示例
    var demoListView = $('#demoList')
        ,uploadListIns = upload.render({
        elem: '#testList'
        ,url: basePath+'/uploadImg'
        ,accept: 'file'
        ,multiple: true
        ,auto: false
        ,bindAction: '#testListAction'
        ,choose: function(obj){
            var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
            //读取本地文件
            obj.preview(function(index, file, result){
                var tr = $(['<tr id="upload-'+ index +'">'
                    ,'<td>'+ file.name +'</td>'
                    ,'<td>'+ (file.size/1014).toFixed(1) +'kb</td>'
                    ,'<td>等待上传</td>'
                    ,'<td>'
                    ,'<button class="layui-btn layui-btn-mini demo-reload layui-hide">重传</button>'
                    ,'<button class="layui-btn layui-btn-mini layui-btn-danger demo-delete">删除</button>'
                    ,'</td>'
                    ,'</tr>'].join(''));

                //单个重传
                tr.find('.demo-reload').on('click', function(){
                    obj.upload(index, file);
                });

                //删除
                tr.find('.demo-delete').on('click', function(){
                    delete files[index]; //删除对应的文件
                    tr.remove();
                    uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                });

                demoListView.append(tr);
            });
        }
        ,done: function(res, index, upload){
            if(res.code == 0){ //上传成功
                var tr = demoListView.find('tr#upload-'+ index)
                    ,tds = tr.children();
                tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
                tds.eq(3).html(''); //清空操作
                return delete this.files[index]; //删除文件队列已经上传成功的文件
            }
            this.error(index, upload);
        }
        ,error: function(index, upload){
            var tr = demoListView.find('tr#upload-'+ index)
                ,tds = tr.children();
            tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
            tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
        }
    });
});

