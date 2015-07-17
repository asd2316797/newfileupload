<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>File upload</title>
</head>
<body>
	<!-- action="fileupload"对应web.xml中
	<servlet-mapping>中<url-pattern>的设置.
	 -->
	<form name="myform" action="fileupload" method="post"
		enctype="multipart/form-data">
		目标文件:<br> <input type="file" name="myfile"><br> <br>
		比对文件:<br /> <input type="file" name="myfile"> <br /> <input
			type="submit" name="submit" value="开始相似度比对">
	</form>

</body>
</html>