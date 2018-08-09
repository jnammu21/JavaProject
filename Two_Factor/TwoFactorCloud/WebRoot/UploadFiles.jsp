


<!DOCTYPE HTML PUBLIC "-//w3c//dtd html 4.0 transitional//en">
<html>
	<head>

<script language="JavaScript" src="scripts/gen_validatorv31.js"
			type="text/javascript"></script>
		<script language="JavaScript" type="text/javascript"
			src="scripts/ts_picker.js"></script>
		<script language="JavaScript1.1" src="scripts/pass.js">
</script>
		<script type="text/javascript" src="scripts/image.js"> </script>
		<script type="text/javascript" src="scripts/general.js"> </script>
		<script type="text/javascript" src="scripts/adi.js"> </script>
		<script type="text/javascript" src="scripts/form_validation.js"> </script>
        <script type="text/javascript" src="scripts/project.js"> </script>
		<script language="JavaScript" src="images/javascripts.js"></script>
		<script language="JavaScript" src="images/pop-closeup.js"></script>
		<script>

//var x_win = window.self; 

function goOn() {
var port=document.register.port.value;
var host=document.register.host.value;
var mkey=document.register.mkey.value;
window.location.href='http://'+host+':'+port+'/DecentralizedAccess/KeyAvailabilityAction?mkey='+mkey+"&path=./UploadFiles.jsp";

} 
</script>

		
		
		<style type="text/css">
.Title {
font-family:Verdana;
font-weight:bold;
font-size:8pt
}
.Title1 {font-family:Verdana;
font-weight:bold;
font-size:8pt
}
        </style>
        </head>
<body background="./images/vv3.jpg" >
<jsp:include page="Header.jsp"></jsp:include>
<div align="center" >
					
					
<%
		String s1 = (String) request.getAttribute("status123");if(s1!=null){%>
		<center>
		<td align=center><font color=ORANGE><b><%=s1 %> <%} %></b></font></td></center>
		<br/><br/>
<table background="./images/bg2.jpeg" align=center" WIDTH=700>
<form action="./UploadFileAction"  name="register" onSubmit="return validate()">
 <!--<table border="1"><tr><td></td></tr></table>-->
 <input type="hidden" name="port" value="<%=request.getLocalPort() %>">
 <input type="hidden" name="host" value="<%=request.getServerName() %>">
 <br>

 
<th colspan="6" bgcolor="#808"><span class=Title><font color="red" size="3"><b>File Upload Here</b></font></span></th>


 <tr>
					<td align='left'><b>
						<font color=GREE>ID FOR YOUR FILE </b>
					</td>
					<td width="303">
						<input type="text" name="mkey"
							value="<% if(request.getParameter("mkey")!=null)
    out.print(request.getParameter("mkey")); %>"
							 /><font color=yellow>YOUR ID MUST BE UNIQUE( must be 8 chars) </font>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;



					
					
						<font color="WHITE"><B> ${requestScope.status } </font>
				

					<td align='left'>

						<b><font color="gree"> <c:if
									test="${'requestScope.status1'!='null'}">

									<c:out value="${param.status1}"></c:out>
								</c:if> </font>
						</b>


					</td>
					
				</tr>
				  <tr>
 <td><pre><span class=Title>    <font color=GREE>TIME  STAMP </font></span></pre></td>
  <td>
  
        <input type="TEXT" name="tmpstamp" value="<%=new java.util.Date()%>"></td></tr>
        
				
			 <tr>
 <td><pre><span class=Title>    <font color=GREE>SELECT PRIVATE KEY </font></span></pre></td>
  <td>
  
        <input type="TEXT" name="prvkey"></td></tr>
        
        		 <tr>
 <td><pre><span class=Title>    <font color=GREE>SELECT PUBLIC KEY </font></span></pre></td>
  <td>
  
        <input type="TEXT" name="pubkey"></td></tr>
      
        
        
     	
				
<tr>
 <td><pre><span class=Title>    <font color=GREE>SELECT FILE </font></span></pre></td>
  <td>
  
        <input type="file" name="upfile"></td></tr>
        
     
     
    
     
     
     
     
     
     
     
     <tr>
     <td align="center">
       <ceneter><input type="submit" value="UPLOAD YOUR FILES TO CLOUD"/></ceneter>
</td></tr>

</tr>
</table>
</form>
</div>


<script language="JavaScript" type="text/javascript">
//You should create the validator only after the definition of the HTML form
  var frmvalidator  = new Validator("register");
    
  frmvalidator.addValidation("username","req","Please enter your user Name");
  frmvalidator.addValidation("username","maxlen=15",	"Max length for username is 15");
  frmvalidator.addValidation("username","alpha"," user Name Alphabetic chars only");
  
  frmvalidator.addValidation("password","req","Please enter your password");
  frmvalidator.addValidation("password","maxlen=15","Max length is 15");
  frmvalidator.addValidation("password","alpha"," password Alphabetic chars only");
  
  frmvalidator.addValidation("firstname","req","Please enter your First Name");
  frmvalidator.addValidation("firstname","maxlen=20",	"Max length for FirstName is 20");
  frmvalidator.addValidation("firstname","alpha"," First Name Alphabetic chars only");
  
  frmvalidator.addValidation("lastname","req","Please enter your Last Name");
  frmvalidator.addValidation("lastname","maxlen=20","Max length is 20");
  frmvalidator.addValidation("lastname","alpha"," Last Name Alphabetic chars only");
  
   frmvalidator.addValidation("gender","dontselect=0");
    frmvalidator.addValidation("dob","req","Please enter your Age");
  frmvalidator.addValidation("dob","maxlen=3",	"Max length for Age is 3");
  frmvalidator.addValidation("dob","numeric"," Age is Numaric only"); 
  
    frmvalidator.addValidation("type","dontselect=0");
  frmvalidator.addValidation("email","maxlen=50");
  frmvalidator.addValidation("email","req");
  frmvalidator.addValidation("email","email");
   frmvalidator.addValidation("cell","req");
  
  frmvalidator.addValidation("cell","maxlen=10");
  frmvalidator.addValidation("cell","numeric");
 frmvalidator.addValidation("cell","Cell");
      frmvalidator.addValidation("cell","req","Please enter your Phone Number");
   frmvalidator.addValidation("houseno","req","Please enter your House Number");
    frmvalidator.addValidation("houseno", "numeric", "Please enter Numeric only for House No ");
   frmvalidator.addValidation("street","req","Please enter your Street Number");
    frmvalidator.addValidation("street","alpha","Please enter char only for street");
      frmvalidator.addValidation("city","req","Please enter your city Name");
      frmvalidator.addValidation("city","alpha","Please enter char only");
     frmvalidator.addValidation("state","req","Please enter your State Name");
      frmvalidator.addValidation("state","alpha","Please enter char only");
  frmvalidator.addValidation("conformpassword","req","Please enter your Confirm Password");
   frmvalidator.addValidation("sa","req","Please enter your Answer");
    frmvalidator.addValidation("sq","dontselect=0");
  
 </script>

 										
 
	</body>
</html>



