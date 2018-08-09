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
   <body background="./images/bg2.jpeg">
   <form name=f1 action="./SecretAction" >
 
  <c:choose>  
            <c:when test="${sessionScope.role=='user'}">  
              <jsp:include page="UserMenu.jsp"></jsp:include>
            </c:when>  
          
        </c:choose>   
    <center>
  <%
  if((String)request.getAttribute("status")!=null)
  {%>
  <FONT COLOR="Green"><%=(String)request.getAttribute("status")%></FONT>
  
  
  <%} %>
  <% 
   if((String)request.getAttribute("status1")!=null)
  {%>
  <FONT COLOR="red"><%=(String)request.getAttribute("status1")%></FONT>
  
  
  <%} %>
  </br>
  
  <font color="red"><h2>Enter Your Secret Key For Verification</h2></font> <br/>
  <input type="text" name="skey"/></center><br/><br/>
 <center> <input type=image alt=submit src="./images/varify.png"></center>
 </form></body>
</html>
