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

	<body>
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
				Kdc One	Users
				</h3>
			</caption>

			<table  border="1" bgcolor="white">
				<tr bgcolor="white">
					<td style="size: 5; color: blue">
						<font size="4"><strong>Userid</strong>
						</font>
					</td>
					<td></td>
					<td style="size: 5; color: blue">
						<font size="4"><strong>FullName</strong>
						</font>
					</td>
					<td style="size: 5; color: blue">
						<font size="4"><strong>Usename</strong>
						</font>
					</td>
					
					<td style="size: 5; color: blue">
						<font size="4"><strong>Phone</strong>
						</font>
					</td>
					<td style="size: 5; color: blue">
						<font size="4"><strong>Mail</strong>
						</font>
					</td>
					<td style="size: 5; color: blue">
						<font size="4"><strong>SecretKey</strong>
						</font>
					</td>
					
					
					
					<td style="size: 5; color: blue">
						<font size="4"><strong>PrivateKey</strong>
						</font>
					</td>
					
					<td style="size: 5; color: blue">
						<font size="4"><strong>PublicKey</strong>
						</font>
					</td>
					
					
						
					
						<td style="size: 5; color: blue">
						<font size="4"><strong>HashKey</strong>
						</font>
					</td>
					
					
					
					
						
					
					
					
				</tr>
				
				<c:if test="${not empty servers}">
					<c:forEach var="ser" items="${servers}">


						<tr bgcolor="">
							<td align="center">
								${ser.userid}
							</td>
							<td></td>
							<td align="center">
								${ser.fullName}
							</td>
							<td align="center">
								${ser.userName}
							</td>
							<td align="center">
								${ser.phoneNo}
							</td>
							<td align="center">
								${ser.email}
							</td>
							<td align="center">
								${ser.scretkey}
							</td>
							
							<td align="center">
								${ser.prkey}
							</td>
							<td align="center">
								${ser.pubkey}
							</td>
							<td align="center">
								${ser.hashfunction}
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
