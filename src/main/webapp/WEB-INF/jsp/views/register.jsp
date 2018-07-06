<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/6/27
  Time: 19:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册页面</title>
</head>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/include.jsp"/>
<body class="layui-layout-body">
    <div class="layui-layout layui-layout-admin">
        <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/component/header.jsp"/>
    </div>

    <div class="form-wrapper login-form-wrapper">
        <div class="capital">用户注册</div>
        <form  id="register_form" action="" method="post"  class="layui-form layui-form-pane1 login-form" lay-filter="first">

            <div class="layui-form-item">
                <label class="layui-form-label">用&nbsp;&nbsp;户&nbsp;名</label>
                <div class="layui-input-block">
                    <input type="text" name="username" lay-verify="required|title" required placeholder="请输入用户名" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">密&emsp;&emsp;码</label>
                <div class="layui-input-block">
                    <input type="password" name="password" lay-verify="required|title" required placeholder="请输入密码" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">确认密码</label>
                <div class="layui-input-block">
                    <input type="password" name="notpassword" lay-verify="required|title" required placeholder="请输入密码" autocomplete="off" class="layui-input">
                </div>
            </div>
            ${message}
            <input type="button" id="register_btn" class="login_btn layui-btn layui-btn-normal" value="注册">
        </form>
    </div>
</body>

<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/include.jsp"/>
<script src="${pageContext.request.contextPath}/js/register.js"></script>
</html>
