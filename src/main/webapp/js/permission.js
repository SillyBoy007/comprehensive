var permissionPage = {
    data: '',
    reload: function (table, res) {
        layer.msg(res.msg);
        if (res.code === 0) {

            table.reload('permission_table', {
                url: basePath + '/permission/getRolePermission',
                page: {
                    curr: 1  //从第一页开始
                },
                done: function (res) {
                    permissionPage.data = res.data;
                }
            });
        }
    },
    template:'  <form id="layer_form">\n' +
    '\n' +
    '            <div class="layui-input-block">\n' +
    '                    <input type="hidden" name="id"  autocomplete="off" class="layui-input">\n' +
    '            </div>\n' +

    '            <div class="layui-form-item">\n' +
    '                <label class="layui-form-label">角色</label>\n' +
    '                <div class="layui-input-block">\n' +
    '                    <input type="text" name="role"  lay-verify="required|title" required placeholder="请输入角色" autocomplete="off" class="layui-input">\n' +
    '                </div>\n' +
    '            </div>\n' +
    '            <div class="layui-form-item">\n' +
    '                <label class="layui-form-label">权限</label>\n' +
    '                <div class="layui-input-block">\n' +
    '                    <input type="text" name="permission" lay-verify="required|title" required placeholder="请输入权限" autocomplete="off" class="layui-input">\n' +
    '                </div>\n' +
    '            </div>\n' +

    '        </form>'
};


layui.use(['table','laydate','form'], function(){
    var table = layui.table;
    table.render({
        elem: '#permission_table'
        //,width: 900
        //,height: 274
        ,cols: [[ //标题栏
            {field: 'id', title: 'ID', width: 50, sort: true}
            ,{field: 'role', title: '角色', width: 120}
            ,{field: 'permission', title: '权限', width: 150}
            ,{fixed: 'right', title: '操作', width:210,align:'center', toolbar: '#barDemo'}
        ]]
        ,url:basePath+'/permission/getRolePermission'
        ,skin: 'row' //表格风格
        ,even: true
        ,page: true //是否显示分页
        ,limits: [3,5,10]
        ,limit: 5 //每页默认显示的数量
        ,done:function(res){
            //userPage.data = res.data;
        }
        //,loading: false //请求数据时，是否显示loading
    });

//监听工具条
    table.on('tool(demo)', function(obj){
        var data = obj.data;
        permissionPage.data = obj.data;
        if(obj.event === 'detail'){
            layer.msg('ID：'+ data.id + ' 的查看操作');
        }
        else if(obj.event === 'del'){
            layer.confirm('真的要删除这条记录么',{icon: 3, title:'确定删除'}, function(index){
                obj.del();
                $.post('/doDelete.action?id='+data.id,function (res) {
                    permissionPage.reload(res);
                });
                layer.close(index);
            });

        }
        else if(obj.event === 'add'){
            layer.open({
                title:'权限增加',
                content:permissionPage.template,
                yes:function () {
                  //  var user = page.formToJson($('#layer_form').serialize());
                    var data = $('#layer_form').serialize();
                    $.post('/permission/addRolePermission',data,function (res) {
                        userPage.reload(res);
                    });
                    layer.closeAll();
                }
            })

        }
        else if(obj.event === 'edit'){
            layer.open({
                title:'编辑窗口',
                content:permissionPage.template,
                success:function () {
                    $('input[name="id"]').val(data.id);
                    $('input[name="role"]').val(data.role);
                    $('input[name="permission"]').val(data.permission);

                },
                yes: function(){
                    var mgUser = page.formToJson($('#layer_form').serialize());
                    var data = 'user='+mgUser;
                    $.post('/doEdit.action',data,function (res) {
                        permissionPage.reload(res);
                    });
                    layer.closeAll();
                }
            })
        }
    });
});