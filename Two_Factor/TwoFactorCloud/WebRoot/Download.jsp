<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
   <body background="./image/bg1.jpg">
   <form name=f1 action="./GettingKeyAction" >
  <jsp:include page="Header.jsp"></jsp:include>
  
   
  <%
  if((String)request.getAttribute("status")!=null)
  {%>
  <FONT COLOR=YELLOW><%=(String)request.getAttribute("status")%>
  
  
  <%} %>
  
 
  
  
  <font color="#003366"><h2>Enter File Name</h2></font> <input type="text" name="filename"/></center><br/>
 <center> <input type=image alt=submit src="./images/getfile.png"></center>
 </form></body>
</html>
