<%
	if (session.getAttribute("UserName") == null) {

		RequestDispatcher rd = request
				.getRequestDispatcher("./LoginForm.jsp");
		rd.forward(request, response);
%>
<%
	}
%>

<html>
	<head>
		<style type="text/css">
.Title {
	font-family: Verdana;
	font-weight: bold;
	font-size: 8pt
}

.Title1 {
	font-family: Verdana;
	font-weight: bold;
	font-size: 8pt
}
</style>


		<script type="text/javascript">
function validator() {

	document.getElementById("pass").innerHTML = "";
	document.getElementById("change").innerHTML = "";

	if (document.getElementById("newpass").value == "") {

		document.getElementById("pass").innerHTML = "Plz Enter Password";
		return false;
	} else if (document.getElementById("new").value == "") {

		document.getElementById("pass").innerHTML = "Plz Enter New Password";
		return false;
	}

}
</script>

		<script type="text/javascript">
function disableBackButton() {
	window.history.forward();
}
setTimeout("disableBackButton()", 0);
</script>


	</head>

	<body background="./images/bg2.jpeg">

		<jsp:include page="Header.jsp"></jsp:include>

		<br />
		<br />
		<br />
		<br />
		<center>
			<td align=right>
				<font color=blue><b> <%
 	if ((String) session.getAttribute("status1") != null) {
 %> <%=session.getAttribute("status1")%> <%
 	}
 %> </b> </font>
			</td>
		</center>
		<br />
		<br />

		<center>
			<span class=Title><h3>
					<font color="blue">Change Password Form</font>
				</h3> </span>
		</center>



		<form id="form3" name="newedesignation" method="post"
			action="<%=request.getContextPath() + "/ChangePasswordAction"%>"
			onsubmit="return validator();">

			<table border="0" align="center" background="./images/g1.jpeg">
				<tr>
					<td>
						<span class=Title> Login Name </span>
					</td>
					<td>
						<input type="text" name="username"
							value="<%=session.getAttribute("UserName")%>" readonly />
					</td>
				</tr>
				<tr>
					<td>
						<span class=Title> Old Password </span>
					</td>
					<td>
						<input type="password" name="oldpassword" id="newpass" />
						<div id="pass"></div>
					</td>
				</tr>
				<tr>
					<td>
						<span class=Title> New Password </span>
					</td>
					<td>
						<input type="password" name="newpassword" id="new" />
						<div id="change"></div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<strong> <input type="submit" name="Submit"
									value="Change" /> </strong>
						</div>
					</td>
				</tr>
			</table>
		</form>

		<br />
		<jsp:include page="Footer.jsp"></jsp:include>
	</body>
</html>
