<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'homebody.jsp' starting page</title>
    
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
  
   <center>
   <font color="red"><b>
   
		 <c:if test="${requestScope.status!='null'}">
					 
                          <c:out value="${requestScope.status}"></c:out> 
                          </c:if>
                          </b>
                          </font>
                         
                           </center>
                            <center>
   <font color="#8000ff"><b> 
                       KDC
		 
                          </b></font>
                         
                         
         <center>
   <font color="blue" size="5"><b>
   <%
   if(request.getAttribute("blocks")!=null){
   String block=(String)request.getAttribute("blocks");
   int blocks=Integer.parseInt(block);
   if(blocks>0)
   {
    %>
    <font > Data Divided into </font> 
    <%} %>
		 <c:if test="${requestScope.blocks!=0}">
					  
                          <c:out value="${requestScope.blocks}"></c:out> 
                          </c:if>
                           <%
   if(blocks>0)
   {
    %>
    <font> Blocks </font> 
    <%} }%>
                          </b>
                          </font>
                         
                           </center>
        
                          <br/>                   </center>
                          
  <center>
  
    <font size="6" color="purple"></font>
    </center>
     </b> </b>
    <table align="center">
   <tr>
   <td>
   <img src="image/c6.jpeg" height="150" width="391" alt="Image"/>
   </td>
   </tr>
   </table>
    <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
  </body>
</html>
