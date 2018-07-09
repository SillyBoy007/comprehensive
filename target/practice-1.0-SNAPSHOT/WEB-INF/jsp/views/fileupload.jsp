<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/7/4
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单模块页面</title>
</head>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/include.jsp"/>
<body>
<div class="file-upload-container">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
        <legend>常规使用：普通图片上传</legend>
    </fieldset>

    <div class="layui-upload">
        <div class="layui-upload-list">
            <img class="layui-upload-img" id="demo1">
            <p id="demoText"></p>
        </div>
        <button type="button" class="layui-btn" id="upload_btn">上传图片</button>

    </div>

    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
        <legend>上传多张图片</legend>
    </fieldset>

    <div class="layui-upload">
        <button type="button" class="layui-btn" id="test2">多图片上传</button>
        <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
            预览图：
            <div class="layui-upload-list" id="demo2"></div>
        </blockquote>
    </div>


    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
        <legend>文件和表单域上传</legend>
    </fieldset>

    <div class="layui-upload">
        <form class="layui-form">
            <div class="layui-inline">
                <label for="username"></label>
                <input type="text" id="username" name="username" class="layui-input" placeholder="请输入用户名"/>
            </div>

            <button type="button" class="layui-btn layui-btn-normal" id="test8">选择文件</button>
            <button type="button" class="layui-btn" id="test9">开始上传</button>
        </form>

    </div>


    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
        <legend>拖拽上传</legend>
    </fieldset>

    <div class="layui-upload-drag" id="test10">
        <i class="layui-icon"></i>
        <p>点击上传，或将文件拖拽到此处</p>
    </div>


    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
        <legend>高级应用：制作一个多文件列表</legend>
    </fieldset>

    <div class="layui-upload">
        <button type="button" class="layui-btn layui-btn-normal" id="testList">选择多文件</button>
        <div class="layui-upload-list">
            <table class="layui-table">
                <thead>
                <tr><th>文件名</th>
                    <th>大小</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr></thead>
                <tbody id="demoList"></tbody>
            </table>
        </div>
        <button type="button" class="layui-btn" id="testListAction">开始上传</button>
    </div>
</div>
</body>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/include.jsp"/>
<script src="${pageContext.request.contextPath}/js/fileupload.js"></script>
</html>
