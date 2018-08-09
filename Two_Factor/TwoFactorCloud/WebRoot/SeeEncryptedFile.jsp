

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<script language="javascript">

</script>
		<script type="text/javascript" src="scripts/general.js">
</script>
		<script type="text/javascript" src="scripts/ts_picker.js">
</script>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />

		<meta http-equiv="Last-Modified"
			content="Thu, 02 Aug 2007 10:30:00 GMT" />


		<title>e-Banking</title>

		<link rel="icon" href="http://www.bis.org/favicon.ico"
			type="image/x-icon" />
		<link rel="shortcut icon" href="http://www.bis.org/favicon.ico"
			type="image/x-icon" />

		<link href="cbanks_files/standard.css" type="text/css"
			rel="stylesheet" />

		<!-- JavaScript variable to set the active Menu -->
		<script type="text/javascript" src="cbanks_files/standard.js">
</script>
		<script type="text/javascript">
<!--
		var primaryMenu = "Central bank hub";
		var secondaryMenu = "Central bank websites";
	// -->
	</script>
		<style type="text/css">
<!--
.style1 {
	font-size: 50px
}
-->
</style>
	</head>
	<body background="./images/gb2.jpg">


		<jsp:include page="Header.jsp"></jsp:include>

		<center>
			<h2>
				<font color=gree><% int i=0,fileid=0; String filename=null,path=null;
 	filename=request.getParameter("s2");
 	path=request.getParameter("s1");        
 	
 	
 	%>
 
  </b> </font><BR/><BR/>
			</h2>
		</center>
		<div class="hr">
		</div>
		<p></p>

		<form name="f" method="get" action="./">
<center><font color=#D588><B>ENCRYPTE DATA SHOW HERE</font><BR/><BR/>
			<table width="350" border="1" align="center" bordercolor="#8692E3">



				<tr bgcolor="#50cccc">
				
				
				
					<td width="233" height="16">
						<div align="center" class="style8">
						FILE NAME
						</div>
					</td>
					
					
					<td width="233" height="16">
						<div align="center" class="style8">
						DOWNLOAD FILE 
						</div>
					</td>
					
		            
                        
                        
						<tr bgcolor="#CcC9cA">
						<td>
							<b><font color=red><%=filename%>
						</td>
						<td>
							<a href="<%=path%>"><font color=green>SeeEnceryptedData
							</a>
						</td>
							
					

			</table>

<br/>
<CENTER>

</center>

			<br />
			<br />

			<jsp:include page="Footer.jsp"></jsp:include>
		</form>
	</body>
</html>

