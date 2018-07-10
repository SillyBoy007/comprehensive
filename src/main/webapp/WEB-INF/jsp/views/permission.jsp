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
    <title>权限管理</title>
</head>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/include.jsp"/>
<body>
    <div class="permission-container">
        <div id="permission_table" lay-filter="demo"></div>
    </div>
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="add">增加</a>
        <a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>

        <!-- 这里同样支持 laytpl 语法，如： -->
        {{#  if(d.auth > 2){ }}
        <a class="layui-btn layui-btn-xs" lay-event="check">审核</a>
        {{#  } }}
    </script>
</body>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/include.jsp"/>
<script src="${pageContext.request.contextPath}/js/permission.js"></script>

</html>
