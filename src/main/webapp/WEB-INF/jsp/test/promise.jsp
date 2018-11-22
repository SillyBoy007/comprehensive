<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/11/22
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/include.jsp"/>
<body>
    <button id="btn">按钮</button>
</body>
<script>

    var getJSON = function(url) {
        var promise = new Promise(function(resolve, reject){
           $.get(url,function (result) {
               resolve(result);
           })
        });
        return promise;
    };

    getJSON("/test/getData").then(function(json) {
        console.log('Contents: ' + JSON.stringify(json));
    }, function(error) {
        console.error('出错了', error);
    });
</script>
</html>
