<%@ page language="java"
	import="java.util.*,com.Decentralized.bean.*,com.Decentralized.dao.*"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>


	<%
	String y="";
	String username="";
		String userrole = (String) session.getAttribute("hkey");
		String user = (String) session.getAttribute("UserName");
		Vector<RegisterBean> v = null;
		UserdaoImpl d = new UserdaoImpl();
	   // String u=d.getPrefer(user);
		
	    // String per=d.getPrefer();
	    // System.out.println("Permission"+per);
		v = d.viewMatrix(user);

		int skey = 0;
		int hkey;
		int hk = 0;
		int result = 0;

		if (v != null) {
			Iterator it = v.listIterator();

			while (it.hasNext()) {

				RegisterBean b = (RegisterBean) it.next();
				skey = b.getSkey();
				hkey = b.getHkey();
				result = skey * hkey;
			y=	b.getPreference();
			System.out.println("Permission"+y);
			}
		}
	%>
	<head>

	</head>
	<body background="./images/bg1.jpeg">
		<jsp:include page="Header.jsp"></jsp:include>

		<center>
			<div class="hr">
			</div>
			<p></p>

			<form action="./EnterKey.jsp">

				<center>
					<font color="red" size="3"><font  color="green"><b>User Verification</b></font> </font>
				</center>

				<br>
				<br>


				<%
					if (result == 1) {
				%>
				<center>
					<table border=1 width="600">

						<tr>

							<th>
								<font color="#003366">UserName</font>
							</th>
							<th>
								<font color="#003366">Verification Status</font>
							</th>
							<th>
								<font color="#003366">Result</font>
							</th>
						</tr>


						<tr>
							<td align="center">
								<font color="#147852F"><%=user%>
							</td>
							<td align="center">
								<font color="#147852F"><%=result%>
							</td>
							<td align="center">
								<font color="#147852F"><b></b>YOU HAVE PERMISSION TO
									DOWNLOAD THE FILE <b>
							</td>
						</tr>



					</table>
					<br>

					
				</center>


				<%
					}else if (result == 0){
				%>
				<center>
					<table border=1 width="600">

						<tr>

							<th>
								<font color="#003366">UserName</font>
							</th>
							<th>
								<font color="#003366">Verification Status</font>
							</th>
							<th>
								<font color="#003366">Result</font>
							</th>
						</tr>


						<tr>
							<td align="center">
								<font color=green><%=user%>
							</td>
							<td align="center">
								<font color=green><%=result%>
							</td>
							<td align="center">
								<font color=green>Not Possible to Access the File 
							</td>
						</tr>
					</table>


					<br>

				</center>
				<%
					}
				%>
<table>

						<tr>

							<td align="center">
								<input type="submit" name="submit" value="Get File">
							</td>

						</tr>
					</table>
				<jsp:include page="Footer.jsp"></jsp:include>
			</form>
	</body>
</html>

