<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>首页</title>
</head>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/include.jsp"/>
<body class="layui-layout-body">

<div class="layui-layout layui-layout-admin">

    <jsp:include page="component/header.jsp"/>
    <jsp:include page="component/left.jsp"/>
    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div class="layui-tab" lay-filter="demo" lay-allowclose="true">
            <ul class="layui-tab-title">

            </ul>
            <div class="layui-tab-content">

            </div>
        </div>
    </div>
    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>
<script id="menu_scr" type="text/html">

    {{#    layui.each(d.result, function(index, item){
    if(item.parent != null && item.childs != null){
              if (index == 0){
                                                            }}
     <li class="layui-nav-item  layui-nav-itemed">
    {{#       }  else{                                      }}
     <li class="layui-nav-item">
    {{#                     }                               }}
         <a class="" href="javascript:;">{{item.parent.name}}</a>
         <dl class="layui-nav-child">
    {{#     layui.each(item.childs, function(index,child){  }}
             <dd><a href="javascript:;" class="a-tab-add" data-type="tabAdd" data-val="{{child.name}}" data-url="{{child.url}}" data-id="{{child.id}}">{{child.name}}</a></dd>
    {{#     });                                             }}
         </dl>
     </li>
    {{#   } });                                              }}

</script>
</body>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/include.jsp"/>

<script src="${pageContext.request.contextPath}/js/index.js"></script>
</html>
