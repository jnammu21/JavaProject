<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>Admin Menu</title>
		<meta name="Author" content="Stu Nicholls" />
         <link rel="stylesheet" type="text/css" href="./menu/pro_drop_1.css" />
        <script src="menu/stuHover.js" type="text/javascript">
</script>
     </head>
     <img src="./images/text10.gif" width="970" height="100" />
	<body>
		<ul id="nav">
			<li class="top">
				<a href="./MainHome.jsp" class="top_link"><span>HOME</span> </a>
			</li>
			<li class="top">
			
			
			<a href="./ViewProfileAction?userid=<%=session.getAttribute("UserName")%>" class="top_link">PROFILE</a>
			
			
			</li>

			
		
			
			<li class="top">
				<a href="./ViewCredentialAction?userid=<%=session.getAttribute("UserName")%>" class="top_link"><span>UserCredentials</span> </a>
			</li>
				<li class="top">
				<a href="./UploadFiles.jsp" class="top_link"><span>UPLOADFILE</span> </a>
			</li>
			<li class="top">
				<a href="./ViewAllFilesinDBAction" class="top_link"><span>VIEWFILESINDB</span> </a>
			</li>
		
			
			<!--<li class="top">
				<a href="./EnterKey.jsp" id="products" class="top_link"><span>AccessFileFromCloud</span>
				</a>
			</li>
			
			-->
			
			<li class="top">
				<a href="./VerifySecret.jsp" id="products" class="top_link"><span>AccessFileFromCloud</span>
				</a>
			</li>
				
			<li class="top">
				<a href="./ChangePassword.jsp" class="top_link"><span>ChangePassword</span></a>
			</li>
			
			<li class="top">
				<a href="./LogoutAction" id="products" class="top_link"><span>LOGOUT</span>
				</a>
			</li>
			
			
			
			

		</ul>
	</body>
</html>







