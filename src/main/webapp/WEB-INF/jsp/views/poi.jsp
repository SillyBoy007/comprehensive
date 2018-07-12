<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/7/10
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>POI操作练习</title>
    </head>
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/include.jsp"/>
    <body>

        <form class="layui-form" id="form1">
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <h1>导入运单信息</h1>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">活动名称  </label>
                <div class="layui-input-block">
                    <div id="activity" class="layui-form-mid layui-word-aux"></div>
                    <input type="hidden" id="liveid" name="liveid" value="moqianyi">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">运单信息  </label>
                <div class="layui-input-block">
                    <div class="layui-upload">
                        <button type="button" class="layui-btn layui-btn-normal"
                                id="selectExcel">
                            <i class="layui-icon">&#xe67c;</i>上传Excel
                        </button>
                        <a href="/akucunDeliverTrace/downloadExcel.do">点击下载Excel模板</a>
                    </div>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button type="button" class="layui-btn" id="uploadExcel">开始上传</button>
                </div>
            </div>
        </form>
    </body>
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/include.jsp"/>
    <script src="${pageContext.request.contextPath}/js/poi.js"></script>

</html>
