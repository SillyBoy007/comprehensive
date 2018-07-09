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
        <div id="role_table" lay-filter="demo"></div>
    </div>
    <script type="text/html" id="barDemo"  >
            <a class="layui-btn layui-btn-xs" lay-event="set">设置管理</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="unset">取消管理</a>
    </script>
</body>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/include.jsp"/>
<script src="${pageContext.request.contextPath}/js/role.js"></script>

</html>
