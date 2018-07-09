var rolePage = {
    data: '',
    reload: function (table, res) {
        layer.msg(res.msg);
        if (res.code === 0) {

            table.reload('role_table', {
                url: basePath + '/role/userRole',
                page: {
                    curr: 1  //从第一页开始
                },
                done: function (res) {
                    rolePage.data = res.data;
                }
            });
        }
    }
};

layui.use(['table','laydate','form'], function(){
    var table = layui.table;
    table.render({
        elem: '#role_table'
        //,width: 900
        //,height: 274
        ,cols: [[ //标题栏
            {field: 'id', title: 'ID', width: 280, sort: true}
            ,{field: 'username', title: '用户名', width: 120}
            ,{field: 'role', title: '角色', width: 150}
            ,{fixed: 'right', title: '操作', width:210,align:'center', toolbar: '#barDemo'}
        ]]
        ,url:basePath+'/role/userRole'
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
        rolePage.data = obj.data;
        console.log(obj);
        if(obj.event === 'set'){
            layer.confirm('将该用户设置为管理？',{icon: 3, title:'确定操作'}, function(index){
                //obj.del();
                $.post('/role/setManage?id='+data.id,function (res) {
                    rolePage.reload(table,res);
                });
                layer.close(index);
            });

        }
        if(obj.event === 'unset'){
            layer.confirm('将该用户取消管理？',{icon: 3, title:'确定操作'}, function(index){
                //obj.del();
                $.post('/role/unSetManage?id='+data.id,function (res) {
                    rolePage.reload(table,res);
                });
                layer.close(index);
            });

        }
    });


});


