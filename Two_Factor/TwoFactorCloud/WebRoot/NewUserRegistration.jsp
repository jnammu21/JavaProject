<%@ page language="java"
	import="java.util.*,com.Decentralized.bean.*,com.Decentralized.dao.*"
	pageEncoding="ISO-8859-1"%>
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
		<script language="JavaScript" type="text/javascript"
			src="<%=request.getContextPath() + "/scripts/ts_picker.js"%>">
</script>

		<script language="JavaScript"
			src="<%=request.getContextPath()
							+ "/scripts/gen_validatorv31.js"%>"
			type="text/javascript">
</script>
		<script type="text/javascript">
function disableBackButton() {
	window.history.forward();
}
setTimeout("disableBackButton()", 0);
</script>
		<script type="text/javascript">

function checkEmail() {
	var email = document.getElementById("email").value;
	var emailRegEx = /^([a-zA-Z0-9])(([a-zA-Z0-9])*([\._-])?([a-zA-Z0-9]))*@(([a-zA-Z0-9\-])+(\.))+([a-zA-Z]{2,4})+$/
	if (document.registerform.email.value.search(emailRegEx) == -1) //if match failed
	{
		alert("EmailID not Valid");
		return false;
	}
}
</script>

		<script type="text/javascript" src="js/gen_validatorv31.js">
</script>
		<script language="JavaScript" type="text/javascript"
			src="js/ts_picker.js">
</script>
		<script type="text/javascript" src="js/image.js">
</script>
		<script type="text/javascript" src="js/general.js">
</script>
		<script type="text/javascript" src="js/adi.js">
</script>
		<script type="text/javascript" src="js/form_validation.js">
</script>
		<script type="text/javascript" src="js/gen_validatorv31.js">
</script>
		<script language="JavaScript" src="js/javascripts.js">
</script>
		<script type="text/javascript">

function checkUserId() {
	var userid = document.getElementById("userid").value;
	var obj = null;
	try {
		if (window.XMLHttpRequest) {
			obj = new XMLHttpRequest();
		} else {
			obj = new ActiveXObject("Microsoft.XMLHTTP");
		}
	} catch (e) {
		alert("xmlHttp object creation failed");
	}
	obj.onreadystatechange = function() {
		if (obj.readyState == 4) {
			document.getElementById("avail").innerHTML = obj.responseText;
			obj.responseText = null;
			obj.abort();
		}
	}
	var url = "./CheckUserIDAvail?userid=" + userid;
	obj.open("GET", url, true);
	obj.send(null);

}
</script>
	</head>

	<body background="./images/bg2.jpeg">
		<jsp:include page="Header.jsp"></jsp:include>

		<center>
			<font color="red"><b> <c:if
						test="${requestScope.status!='null'}">

						<c:out value="${requestScope.status}"></c:out>
					</c:if> </b> </font>

		</center>
<center>
		<h3>
			<div align="center">
				<span class=subHead>Registration Form </span>
			</div>
		</h3>
		<form action="<%=request.getContextPath()%>/RegisteruserAction"
			method="post" name="register" onSubmit="return validate()">
<table>
				<th colspan="6" bgcolor="white">
					User Details
				</th>

				<tr>
					<td>
						<font color="white" size="4"><b>UserName</b> </font>
					</td>

					<td>
						<input type="text" name="userName" id="userid"
							value="<%if (request.getParameter("userName") != null)
				out.print(request.getParameter("userName"));%>"
							size="30" onblur="checkUserId();" />
						<div id="avail"></div>
						<font color="white"> ${requestScope.status } </font>
					</td>

				</tr>

				<tr>
					<td>
						<font color="white" size="4"><b>PassWord</b> </font>
					</td>
					<td>

						<input type="password" name="password" size="30"
							onkeyup="testPassword(document.forms.register.password.value);"
							onChange="Encrypt(document.forms.register.password.value);">
					</td>
				</tr>
				
				<tr>
					<td>
						<font color="white" size="4"><b>ConfirmPassword</b> </font>
					</td>
					<td>
						<input type="password" name="conformpassword" value="" size="30"
							onBlur="checkconformpassword();" />
						<br>
						
					</td>
				
					<td>
						<input type="hidden" name="role" value="user" readonly="readonly"
							size="30" />
					</td>
				</tr>

	<tr>
					<td>
						<font color="white" size="4"><b>Full Name</b> </font>
					</td>
					<td width="">
						<input type="text" name="fullName" value="" size="30">
					
					</td>
				</tr>
	<tr>
					<td>
						<font color="white" size="4"><b> Date of Birth</b> </font>
					</td>
					<td width="">
						<input type="text" name="dob" size="30" readonly="readonly">

						<a
							href="javascript:show_calendar('document.register.dob', document.register.dob.value);">
							<img src="<%=request.getContextPath() + "/images/cal.gif"%>"
								alt="a" width="18" height="18" border="0" /> </a>

					</td>
				</tr>
				<tr>
					<td>
						<font color="white" size="4"><b> Age</b> </font>
					</td>
					<td width="276">
						<input type="text" name="age" value="" size="30">
					
					</td>
				</tr>
				<tr>
					<td>
						<font color="white" size="4"><b> Email</b> </font>
					</td>
					<td>
						<input type="text" name="email" value="" size="30" />
						<br>
						
					</td>
				</tr>
                <tr>
					<td>
						<font color="white" size="4"><b> Phone No</b> </font>
					</td>
					<td>
						<input type="text" name="phoneNo" value="" size="30" />
						
					</td>
				</tr>
				
				 <tr>
					<td>
						<font color="white" size="4"><b>Select KDC</b> </font>
					</td>
					<td>
						<font size="3" face="Verdana"> <select name="kdc">
								<option value="">
									--Select One---
								</option>
								<option value="kdc1">
									kdc1
								</option>
								<option value="kdc2">
									kdc2
								</option>
								<option value="kdc3">
									kdc3
								</option>
                          	</select> </font>
                 </td>
				</tr>
 <tr>
					<td>
						<font color="white" size="4"><b> User Type</b> </font>
					</td>
					<td>
						<font size="3" face="Verdana"> <select name="role">
								<option value="">
									--Select One---
								</option>
								<option value="user">
									User
								</option>
								<option value="kdc1">
									kdc1
								</option>
								<option value="kdc2">
									kdc2
								</option>
								<option value="kdc3">
									kdc3
								</option>
                          	</select> </font>
</td>
				</tr>
<tr>
					<td>
						<font color="white" size="4"><b>UserPreference</b> </font>
					</td>
					<td>
						<input name="radio" type="radio" value="read"  checked="checked"/><font  color="white"> <b>Read</b></font>
						
						<input name="radio" type="radio" value="write" /><font  color="white"><b>Write</b></font>
					</td>
				</tr>
					<tr>
					<td align="center">
						<font size="3" face="Verdana"> <input type="submit"
								name="register" value="Register" />&nbsp; <input type="reset"
								name="cancel" value="Clear" /> </font>
					</td>
					<td align="center"></td>
				</tr>
			</table>
			<p>
				<br />
				
			</p>
			<p>
				&nbsp;
			</p>
			<p>
				&nbsp;
			</p>
			<p>
				&nbsp;
			</p>
			<p>
				<br />
			</p>
		</form>
		</center>
		<script language="JavaScript" type="text/javascript">
//You should create the validator only after the definition of the HTML form
var frmvalidator = new Validator("register");
frmvalidator.addValidation("userName", "req", "Please enter your Username");
frmvalidator.addValidation("password", "req", "Please enter your Password");
frmvalidator.addValidation("conformpassword", "req",
		"Please enter your Confirm Password");
frmvalidator.addValidation("fullName", "req", "Please enter your  Name");
frmvalidator.addValidation("email", "maxlen=50");
frmvalidator.addValidation("email", "req", "Please enter your  mail id");
frmvalidator.addValidation("email", "email");
frmvalidator.addValidation("phoneNo", "req");
frmvalidator.addValidation("phoneNo", "maxlen=10");
frmvalidator.addValidation("phoneNo", "numeric");
frmvalidator.addValidation("phoneNo", "Phone");
</script>
</body>
</html>
