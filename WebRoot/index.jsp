<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	<%
		List<String> text = (List<String>) request.getAttribute("text");
		String sStr = text.get(0);
		String tStr = text.get(1);
		String[] sSplit = sStr.split("[。？！.?!]");
		String[] tSplit = tStr.split("[。？！.?!]");
		List<String> simliarityArray = (List<String>) request
				.getAttribute("simliarityArray");
	%>
	<%
		String a = "";
		boolean isSima = false;
		for (int i = 0; i < sSplit.length; i++) {
			for (int j = 0; j < simliarityArray.size(); j++) {
				//System.out.println("sSplit[i]" + sSplit[i]);
				if (sSplit[i].equals(simliarityArray.get(j))) {
					a += "<font style='color:red'>" + sSplit[i] + "<font>";
					isSima = true;
				}
			}
			if (isSima == false) {
				a += sSplit[i];
			}
			isSima = false;
		}
	%>
	<%
		String b = "";
		boolean isSimb = false;
		for (int i = 0; i < tSplit.length; i++) {
			for (int j = 0; j < simliarityArray.size(); j++) {
				//	System.out.println("sSplit[i]" + sSplit[i]);
				if (tSplit[i].equals(simliarityArray.get(j))) {
					b += "<font style='color:red'>" + tSplit[i] + "<font>";
					isSimb = true;
				}
			}
			if (isSimb == false) {
				b += tSplit[i];
			}
			isSimb = false;
		}
	%>


	文本1
	<br />

	<div>
		<%=a%>
	</div>
	<br />
	<font style="color:black">文本2</font>
	<br />

	<div style="color:black">
		<%=b%>
	</div>
	<br />
	<font style="color:black">相似度:</font>
	<%
		String num = String.valueOf(request.getAttribute("num"));
	%>

	<input type="text" value="<%=num + "%"%>">

</body>
</html>
