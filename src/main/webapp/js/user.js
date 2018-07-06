var userPage ={
    data : '',
    reload:function(table,res){
        if (res.code === 0 ){
            layer.msg(res.msg);
            table.reload('data_grid', {
                url: basePath+'/user/query',
                page:{
                    curr:1  //从第一页开始
                },
                done:function (res) {
                    userPage.data = res.data;
                }
            });
        } else {
            layer.msg(res.msg);
        }
    },
    template:'  <form id="layer_form">\n' +
    '\n' +
    '            <div class="layui-input-block">\n' +
    '                    <input type="hidden" name="id"  autocomplete="off" class="layui-input">\n' +
    '            </div>\n' +

    '            <div class="layui-form-item">\n' +
    '                <label class="layui-form-label">用户名</label>\n' +
    '                <div class="layui-input-block">\n' +
    '                    <input type="text" name="username"  lay-verify="required|title" required placeholder="请输入用户名" autocomplete="off" class="layui-input">\n' +
    '                </div>\n' +
    '            </div>\n' +
    '            <div class="layui-form-item">\n' +
    '                <label class="layui-form-label">密&emsp;码</label>\n' +
    '                <div class="layui-input-block">\n' +
    '                    <input type="password" name="password" lay-verify="required|title" required placeholder="请输入密码" autocomplete="off" class="layui-input">\n' +
    '                </div>\n' +
    '            </div>\n' +
    '            <div class="layui-form-item">\n' +
    '                <label class="layui-form-label">性&emsp;别</label>\n' +
    '                <div class="layui-input-block">\n' +
    '                    <input type="text" name="gender" lay-verify="required|title" required placeholder="请输入性别" autocomplete="off" class="layui-input">\n' +
    '                </div>\n' +
    '            </div>\n' +
    '            <div class="layui-form-item">\n' +
    '                <label class="layui-form-label">昵&emsp;称</label>\n' +
    '                <div class="layui-input-block">\n' +
    '                    <input type="text" name="nichen" lay-verify="required|title" required placeholder="请输入昵称" autocomplete="off" class="layui-input">\n' +
    '                </div>\n' +
    '            </div>\n' +
    '            <div class="layui-form-item">\n' +
    '                <label class="layui-form-label">生&emsp;日</label>\n' +
    '                <div class="layui-input-block">\n' +
    '                    <input type="date" name="birthday" lay-verify="required|title" required placeholder="请输入出生年月" autocomplete="off" class="layui-input">\n' +
    '                </div>\n' +
    '            </div>\n' +
    '        </form>'
};
layui.use(['table','laydate','form'], function(){
    var table = layui.table,laydate = layui.laydate,form = layui.form;

    table.render({
        elem: '#data_grid'
        //,width: 900
        //,height: 274
        ,cols: [[ //标题栏
            {field: 'id', title: 'ID', width: 280, sort: true}
            ,{field: 'username', title:'用户名',width: 100} //空列
            ,{field: 'password', title: '密码', width: 120}
            ,{field: 'salt', title: '盐值', width: 150}
            ,{field: 'createtime', title: '创建时间', width: 150}
            ,{field: 'locked', title: '是否锁定', width: 120}
            ,{fixed: 'right', title: '操作', width:100,align:'center', toolbar: '#barDemo'}
        ]]
        ,url:basePath+'/user/query'
        ,skin: 'row' //表格风格
        ,even: true
        ,page: true //是否显示分页
        ,limits: [3,5,10]
        ,limit: 5 //每页默认显示的数量
        ,done:function(res){
            userPage.data = res.data;
        }
        //,loading: false //请求数据时，是否显示loading
    });

    laydate.render({
        elem: '#birthday'
    });
    laydate.render({
        elem: '#birthday2'
    });



    $.ajax({  //搜索框查询用户信息
        type: 'POST',
        url: basePath+'/user/query',
        //data: {keywords:areaId},
        dataType:  'json',
        success: function(data){
            $("#myselect").html("<option value=\"\">直接选择或搜索选择</option>");
            $.each(data.data, function(key, val) {
                var option1 = $("<option>").val(val.username).text(val.username);
                $("#myselect").append(option1);
                form.render('select');
            });
        }
    });

//select下拉选择框
    form.on('select(selectdemo)',function (data) {
        console.log(data.value);
        console.log(data.id)
    });

    $('#sub_query_form').on('click',function () {

        table.reload('data_grid', {
            url: basePath+'/user/query',
            page:{
                curr:1  //从第一页开始
            },
            method:'post',
            where:{
                username:$('#myselect').val()
            },
            done:function (res) {
                userPage.data = res.data;
            }
        });
    });


    $('#export_excel').on('click',function () {
        $('#page_data').val(JSON.stringify(dataTable.data));
        $("#download_form").submit();
    });



//监听工具条
    table.on('tool(demo)', function(obj){
        var data = obj.data;
        userPage.data = obj.data;


        if(obj.event === 'del'){
            layer.confirm('真的要锁定该用户么？',{icon: 3, title:'确定操作'}, function(index){
                obj.del();
                $.post('/doLocked?id='+data.id,function (res) {
                    userPage.reload(table,res);
                });
                layer.close(index);
            });

        }
    });

});


