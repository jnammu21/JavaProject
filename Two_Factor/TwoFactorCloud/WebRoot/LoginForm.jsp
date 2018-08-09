
<!DOCTYPE HTML PUBLIC "-//w3c//dtd html 4.0 transitional//en">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>

		<script language="JavaScript"
			src="<%=request.getContextPath()
							+ "/scripts/gen_validatorv31.js"%>"
			type="text/javascript">
</script>
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
          <script language="JavaScript" type="text/javascript">
//--------------- LOCALIZEABLE GLOBALS ---------------
var d=new Date();
var monthname=new Array("January","February","March","April","May","June","July","August","September","October","November","December");
//Ensure correct for language. English is "January 1, 2004"
var TODAY = monthname[d.getMonth()] + " " + d.getDate() + ", " + d.getFullYear();
//---------------   END LOCALIZEABLE   ---------------
</script>
		<script type="text/javascript">
function disableBackButton() {
	window.history.forward();
}
setTimeout("disableBackButton()", 0);
</script>

<script type="text/javascript"> 
function display_c(){
var refresh=1000; // Refresh rate in milli seconds
mytime=setTimeout('display_ct()',refresh)
}

function display_ct() {
var strcount

var x = new Date()
var x1=x.getDate() + "/" + x.getMonth() + "/" + x.getYear(); 
x1 = x1 + " - " +  x.getHours( )+ ":" +  x.getMinutes() + ":" +  x.getSeconds();
document.getElementById('ct').innerHTML = x1;

tt=display_c();


 }
</script>


	</head>
 <body  background="images/bg2.jpeg" onload="javascript:disableBackButton()">

		<jsp:include page="Header.jsp"></jsp:include>
<br/><br/><br/>
		<form action="./LoginAction" method=post name="login">
			<table  border="0" align="center" bgcolor="white">
				<tr>
					<td height="120" align="center"></td>
					<td>
						<center>
							<table border="0" align="center">
								<tr>
									<td align='center' colspan='2'>
										<h3>
											Login here
										</h3>
									</td>
								</tr>

								<tr>
									<td align='right'>
										<span class="Title">UserID :</span>
									</td>
									<td>
										<font face="Baskerville Old Face"><input type="text"
												name="loginid"> </font>
									</td>
								</tr>
								<tr>
									<td align='right'>
										<span class="Title">Password :</span>
									</td>
									<td>
										<input type="password" name="password">
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<div align="center" class="style11">
											<input type="submit" name="Submit" value="Sign In">
											&nbsp;
											<input name="Input2" type="reset" value="Clear">
										</div>
									</td>
								</tr>
								<tr>
									<td align='center' colspan='2'>
										<b><a href="./NewUserRegistration.jsp">New User...?</a> </b>
									</td>
								</tr>
								
							</table>
						</center>
			</table>
		</form>
		<script language="JavaScript" type="text/javascript">
//You should create the validator only after the definition of the HTML form
var frmvalidator = new Validator("login");

frmvalidator.addValidation("loginid", "req", "Login Name is required");
frmvalidator.addValidation("password", "req", "Password is required");
</script>
		<jsp:include page="Footer.jsp"></jsp:include>
	</body>
</html>