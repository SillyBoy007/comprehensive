layui.use([ 'element', 'layer', 'form', 'upload' ], function() {
    var layer = layui.layer;
    var upload = layui.upload;

        upload.render({
            elem : '#selectExcel',
            url : '/importExcel',
            auto : false,
            accept : 'file',
            bindAction : '#uploadExcel',
            done : function(res) {
                layer.msg(res.msg);
                location.reload();
            },
            before : function(obj) {
                //layer.load(); //上传loading
                this.data = {
                    'liveid' : $("#liveid").val()
                };
            }
        });
});