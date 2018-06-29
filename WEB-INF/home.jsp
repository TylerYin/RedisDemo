<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
	<title>消息管理</title>
	<meta name="decorator" content="default"/>
    <script src="${pageContext.request.contextPath}/resource/jquery-1.9.1.js"></script>
    
    <script type="text/javascript">
	    $(document).ready(function () {
		    $("#addMessage").click(function(){
		    	$.ajax({
                    url: '${pageContext.request.contextPath}/insert',
                    type: "get",
                    dataType: 'json',
                    success: function (data) {
                        $("#message").html("添加成功！");
                    }
                });
		    });
		    
			$("#removeMessage").click(function(){
				$.ajax({
                    url: '${pageContext.request.contextPath}/select',
                    type: "get",
                    dataType: 'json',
                    success: function (data) {
                    	$("#message").html("删除成功！");
                    }
                });
		    });
		});
    </script>
</head>
<body>
	<div style="text-align:center;margin-top:350px;">
		<input type="button" name="addMessage" id="addMessage" value="添加消息">
		<input type="button" name="removeMessage" id="removeMessage" value="删除消息">
		<div id="message" style="margin-top:30px;color:red"></div>
	</div>
</body>
</html>