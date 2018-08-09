<%@ page language="java" import="java.util.*,com.Decentralized.bean.*,com.Decentralized.dao.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	
<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/highcharts.js" type="text/javascript"></script>


<script type="text/javascript">
		
			/**
			 * Visualize an HTML table using Highcharts. The top (horizontal) header 
			 * is used for series names, and the left (vertical) header is used 
			 * for category names. This function is based on jQuery.
			 * @param {Object} table The reference to the HTML table to visualize
			 * @param {Object} options Highcharts options
			 */
			Highcharts.visualize = function(table, options) {
				// the categories
				options.xAxis.categories = [];
				$('tbody th', table).each( function(i) {
					options.xAxis.categories.push(this.innerHTML);
				});
				
				// the data series
				options.series = [];
				$('tr', table).each( function(i) {
					var tr = this;
					$('th, td', tr).each( function(j) {
						if (j > 0) { // skip first column
							if (i == 0) { // get the name and init the series
								options.series[j - 1] = { 
									name: this.innerHTML,
									data: []
								};
							} else { // add values
								options.series[j - 1].data.push(parseFloat(this.innerHTML));
							}
						}
					});
				});
				
				var chart = new Highcharts.Chart(options);
			}
				
			// On document ready, call visualize on the datatable.
			$(document).ready(function() {			
				var table = document.getElementById('datatable'),
				options = {
					   chart: {
					      renderTo: 'container',
					      defaultSeriesType: 'column'
					   },
					   title: {
					      text: ' Create Key Distribution   Graph'
					   },
					   xAxis: {
					   },
					   yAxis: {
					      title: {
					         text: ''
					      }
					   },
					   tooltip: {
					      formatter: function() {
					         return '<b>'+ this.series.name +'</b><br/>'+
					            this.y +' '+ this.x.toLowerCase();
					      }
					   }
					};
				
			      					
				Highcharts.visualize(table, options);
			});
				
		</script>
		
</head>

  	<body>

<jsp:include page="Header.jsp"></jsp:include>
  
        
          <% 
    
    
    	ArrayList<RegisterBean> v=null;
    	UserDAO d=new UserDAO();
		v=d.viewtime();
      
    int sign=0;
    int isign=0;
	    if(v!=null)
					 {
						
					
						Iterator it = v.listIterator();

						while (it.hasNext()) {
						
						RegisterBean b3 = (RegisterBean) it.next();
				         isign=b3.getSign();
				         isign=isign*4;
						 sign=b3.getAge();
						 sign=sign*2;
						}
	   
	  }
  %>
  
        
 
    
  
  
  

  

<div id="container" style="width: 100%; height: 400px">

</div>
<table id="datatable" border="1" width="20%" cellspacing="0" cellpadding="0">
		
			<thead>
				<tr>
					<th></th>
					<th>Read Key Distribution</th>
					<th>Write Key Distribution</th>
					
					
					
					
				</tr>
			</thead>
			<center>
			
			<tbody>
			<%!String ser[];
			   int val[];
			 %>
			
		
			
				<tr>
				<th></th>
					<td><%=isign%></td>
					<td><%=sign%></td>
				
					
					
					
				</tr>
				<%
			
				 %>
			</tbody><center>
		</table>

  </body>
</html>
