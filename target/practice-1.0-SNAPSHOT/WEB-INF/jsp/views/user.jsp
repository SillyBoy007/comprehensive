<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户</title>
</head>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/include.jsp"/>

<body>
<div class="sort-wrapper">
    <form  class="layui-form" id="query_form">
       <%-- <div class="layui-inline">
            <input type="text" id="username" name="username" class="layui-input" placeholder="请输入关键字"/>
        </div>--%>
        <div class="layui-inline">
            <label class="layui-form-label">搜索选择框</label>
            <div class="layui-input-inline my-select">
                <select name="username"   lay-filter="selectdemo" id="myselect" lay-search="" >

                </select>
            </div>
        </div>
        <input type="button" class="layui-btn " id="sub_query_form" value="搜索"/>
    </form>



    <div id="data_grid" lay-filter="demo" ></div>
    <form action="${pageContext.request.contextPath}/doExport" id="download_form" method="post" class="layui-inline" style="display: inline;">
        <input type="hidden" id="page_data" class="layui-inline" name="userList">
        <input type="button" class="layui-btn layui-btn-normal layui-inline" id="export_excel" value="导出数据"/>
    </form>
</div>


<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">锁定</a>

</script>
</body>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/include.jsp"/>
<script src="${pageContext.request.contextPath}/js/user.js"></script>
</html>
