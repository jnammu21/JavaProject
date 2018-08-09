<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'upload.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
   <body background="./images/bbgg2.jpg">
  <jsp:include page="Header.jsp"></jsp:include>
  <form name=f1 action="./GetFileFromCLDAction" >
  <center>
  <%
  if((String)request.getAttribute("status")!=null)
  {%>
  <FONT COLOR=teal><%=(String)request.getAttribute("status")%>
  
  
  <%} %>
  
 <CENTER><FONT COLOR=teal ><B> <FONT COLOR=teal>ENTER FILE DETAILS <FONT COLOR=teal ></CENTER>
  
  
  <font color=teal><H4> File Name <input type="text" name="filename"><br><br><br>
    <font color=teal><H4>MASTER KEY <input type="text" name="mkey"><br><BR/><br><BR/>
 <center> <center> <input type=submit value="GET KEY"></center></center>
  </form></body>
</html>
