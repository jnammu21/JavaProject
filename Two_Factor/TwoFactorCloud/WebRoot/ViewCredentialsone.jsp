<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

	<body  background="images/vv3.jpg">
		<jsp:include page="Header.jsp"></jsp:include>

		<center>
			<font color="red"><b> <c:if
						test="${requestScope.status!='null'}">

						<c:out value="${requestScope.status}"></c:out>
					</c:if> </b> </font>

		</center>

		<center>
			<caption>
				<h3>
					User 
				</h3>
			</caption>

			<table border="1" bgcolor="/images/bg2.jpeg">
				<tr bgcolor="">
<th style="size: 5; color: ">
						<font size="4"><strong>UserId</strong> </font>
					</th>
					<th style="size: 5; color: ">
						<font size="4"><strong>Usename</strong> </font>
					</th>


					<th style="size: 5; color: ">
						<font size="4"><strong>FullName</strong> </font>
					</th>



					<td style="size: 5; color: ">
						<font size="4"><strong>Dob</strong> </font>
					</td>

					<td style="size: 5; color: ">
						<font size="4"><strong>Age</strong> </font>
					</td>

					<td style="size: 5; color: ">
						<font size="4"><strong>Email</strong> </font>
					</td>


					<td style="size: 5; color: ">
						<font size="4"><strong>PhoneNumber</strong> </font>
					</td>


				</tr>
				<c:if test="${not empty userinfo}">
					<c:forEach var="userinfo" items="${userinfo}">


						<tr bgcolor="">
							<td align="center">
								${userinfo.userid}
							</td>
							
							<td align="center">
								${userinfo.userName}
							</td>
							
							
							<td align="center">
								${userinfo.fullName}
							</td>

							<td align="center">
								${userinfo.dob}
							</td>
							<td align="center">
								${userinfo.age}
							</td>
							<td align="center">
								${userinfo.email}
							</td>
							<td align="center">
								${userinfo.phoneNo}
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</center>

		<br />
		<br />

		<br />
		<br />
		<br />
		<br />
		<br />
		<br />

	</body>
</html>
