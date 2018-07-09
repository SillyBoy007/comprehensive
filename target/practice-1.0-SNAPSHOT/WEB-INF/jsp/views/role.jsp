<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/7/5
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>角色管理</title>
</head>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/include.jsp"/>
<body>
    <div class="role-container">
        <div id="role_table"></div>
    </div>
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="add">增加</a>
        <a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>

    </script>
</body>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/include.jsp"/>
<script>
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
            ]]
            ,url:basePath+'/role/userrole'
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



    });


    //监听工具条
    table.on('tool(demo)', function(obj){
        var data = obj.data;
        userPage.data = obj.data;
        if(obj.event === 'detail'){
            layer.msg('ID：'+ data.id + ' 的查看操作');
        }
        else if(obj.event === 'del'){
            layer.confirm('真的要锁定该用户么？',{icon: 3, title:'确定操作'}, function(index){
                obj.del();
                $.post('/doLocked?id='+data.id,function (res) {
                    userPage.reload(table,res);
                });
                layer.close(index);
            });

        }
        else if(obj.event === 'add'){
            layer.open({
                title:'增加窗口',
                content:userPage.template,
                yes:function () {
                    var user = page.formToJson($('#layer_form').serialize());
                    var data = 'user='+user;
                    $.post('/doAdd.action',data,function (res) {
                        userPage.reload(table,res);
                    });
                    layer.closeAll();
                }
            })

        }
        else if(obj.event === 'edit'){
            layer.open({
                title:'编辑窗口',
                content:userPage.template,
                success:function () {
                    $('input[name="id"]').val(userPage.data.id);
                    $('input[name="username"]').val(userPage.data.username);
                    $('input[name="password"]').val(userPage.data.password);
                    $('input[name="gender"]').val(userPage.data.gender);
                    $('input[name="nichen"]').val(userPage.data.nichen);
                    $('input[name="birthday"]').val(userPage.data.birthday);
                },
                yes: function(){
                    var mgUser = page.formToJson($('#layer_form').serialize());
                    var data = 'user='+mgUser;
                    $.post('/doEdit.action',data,function (res) {
                        userPage.reload(table,res);
                    });
                    layer.closeAll();
                }
            })
        }
    });

</script>
</html>
