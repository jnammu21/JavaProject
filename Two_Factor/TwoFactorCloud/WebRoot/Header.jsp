<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<body background="images/bg2.jpeg">
		<table>
			<tr>
				<td><jsp:include page="SlideImages.jsp"></jsp:include>
				</td>
			</tr>
		</table>
		<c:choose>
			<c:when test="${sessionScope.role eq 'kdc'}">
				<jsp:include page="KDCMenu.jsp" />
			</c:when>
			<c:when test="${sessionScope.role eq 'kdc1'}">
				<jsp:include page="KdcMenu1.jsp" />
			</c:when>
			
			<c:when test="${sessionScope.role eq 'kdc2'}">
				<jsp:include page="KdcMenu2.jsp" />
			</c:when>
			<c:when test="${sessionScope.role eq 'kdc3'}">
				<jsp:include page="KdcMenu3.jsp" />
			</c:when>
			
			<c:when test="${sessionScope.role eq 'cloud'}">
				<jsp:include page="CloudMenu.jsp" />
			</c:when>

			<c:when test="${sessionScope.role eq 'user'}">
				<jsp:include page="UserMenu.jsp" />
			</c:when>
			<c:otherwise>
				<jsp:include page="HomeMenu.jsp"></jsp:include>
			</c:otherwise>
		</c:choose>
		<center>
			<font color="red"><b> <c:if
						test="${requestScope.status!='null'}">
						<c:out value="${requestScope.status}"></c:out>
					</c:if> </b> </font>
		</center>
	</body>
</html>