<%@ page language="java"
	import="java.util.*,com.Decentralized.bean.*,com.Decentralized.dao.*"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

	<%
String userrole=(String)session.getAttribute("hkey");
String user=(String)session.getAttribute("UserName");
Vector<RegisterBean> v=null;
    	UserdaoImpl d=new UserdaoImpl();
		v=d.viewMatrix(user);
  int skey=0;
  int hkey=0;
 %>
	<head>

	</head>
	<body background="./images/bg2.jpeg">
		<jsp:include page="Header.jsp"></jsp:include>

		<center>
			<div class="hr">
			</div>
			<p></p>

			<form action="./Conjunction.jsp">

				<center>
					<font color="red" size="3">Verification of 
						Secret Matrix</font>
				</center>

				<br>
				<br/>
				<center>
					<table border=1 width="600">

						<tr>

							<th>
								<font color="green"><b>SecretKey</b>
							</th>
							<th>
								<font color="green"><b>HashKey</b>
							</th>
							
						</tr>

						<%
                  
					 if(v!=null)
					 {
						
						Iterator it = v.listIterator();

						while (it.hasNext()) {

							RegisterBean b = (RegisterBean) it.next();
							 skey  =b.getSkey();
						     hkey  =b.getHkey();
							
					%>
						<tr>
							<td align="center">
								<font color="red"><%=b.getSkey() %>
							</td>
							<td align="center">
								<font color="red"><%=b.getHkey() %>
							</td>
						
						</tr>

						<%
						}
						
					%>
						<%}%>

					</table>


					<br>

					<table>

						<tr>

							<td align="center">
								<input type="submit" name="submit" value="Compilation">
							</td>

						</tr>
					</table>
					<jsp:include page="Footer.jsp"></jsp:include>
			</form>
	</body>
</html>

