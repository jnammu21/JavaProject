<%@ page language="java"
	import="java.util.*,com.Decentralized.bean.*,com.Decentralized.dao.*"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.Decentralized.db.AbstractDataAccessObject"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>


	<%!Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;%>

	<%
		con = new AbstractDataAccessObject().getConnection();
		ps = con
				.prepareStatement("select  userid, username , preference  from  userdetails where preference='read'");
		rs = ps.executeQuery();
	%>
	<head>

	</head>
	<body background="./images/bg1.jpeg">
		<jsp:include page="Header.jsp"></jsp:include>

		<center>
			<div class="hr">
			</div>
			<p></p>

			<form>

				<center>
					<font color="red" size="3"> <b>Read Permission Users</b>
					</font>
				</center>

				<br>
				<br>

				<TABLE BORDER="1">
					<TR>
						<TH bgcolor="#147852F">
							UserId
						</TH>
						<TH bgcolor="#147852F">
							UserName
						</TH>

						<TH bgcolor="#147852F">
							PreferenceType
						</TH>

					</TR>
					<%
						while (rs.next()) {
					%>
					<TR>


						<TD bgcolor="#147852F">
							<b> <%=rs.getInt(1)%></b>
						</td>
						<TD bgcolor="#147852F">
							<b> <%=rs.getString(2)%></b>
						</td>

						<TD bgcolor="#147852F">
							<b><%=rs.getString(3)%></b>
						</TD>

					</TR>
					<%
						}
					%>


				</TABLE>


			</form>
		</center>
	</body>
	<br />
	<br />
	<br />




	<center>
		<a href="./WriteUsers.jsp"><b>IF U HAVE WANT TO SEE THE WRITE
				USERS</b>
		</a>
	</center>
	<br />
	<br />
	<br />

	<jsp:include page="Footer.jsp"></jsp:include>




</html>