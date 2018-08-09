package com.Decentralized.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Decentralized.bean.FileTo;
import com.Decentralized.dao.UserdaoImpl;
import com.Decentralized.db.AbstractDataAccessObject;


public class InsertIntoCLDAction extends HttpServlet {

	Connection  con=null;
	PreparedStatement ps=null;
	
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		long startmil = System.currentTimeMillis();
		   int i=0;
	       FileTo cb=new  FileTo();
			
	        String path="",token="";
	        boolean flag=false;
	        try{
	       int fileid=Integer.parseInt(request.getParameter("fileid"));
	      
	       cb.setFileid(fileid);
	        
	      
	         flag= new UserdaoImpl().uploadFileintocloud(cb);		
	         
	         
	         
	         
	         
	         
			  
			 
			  System.out.println("in Action class vcb..........."+flag);
			  if(flag){
				  request.setAttribute("status","Data Inserted to Cloud Successfully" );
				  path="viewAllDBFiles.jsp";
				 }
			  else {
				  request.setAttribute("status","Data Inserting Fail/Already file is inserted into Cloud/problem in encryption of file " );
				  path="viewAllDBFiles.jsp";
				}
			  
	   }
		  catch (Exception e) {
			  request.setAttribute("status","Some Technical Problems" );
			  path="AdminHome.jsp";
			e.printStackTrace();
			
		} catch (Throwable e) {
			
			e.printStackTrace();
		}
	        RequestDispatcher rd=request.getRequestDispatcher(path);
			rd.forward(request,response);
			
			long  endtime=System.currentTimeMillis();
			System.out.println("End  time"+endtime);
			long  finatime=endtime-startmil;
			System.out.println(finatime+"::::::::::Millis");
			con=new AbstractDataAccessObject().getConnection();
			
			try {
				ps=con.prepareStatement("insert  into starttime values((select nvl(max(tid),100)+1 from starttime),?,?,?)");
				ps.setLong(1,startmil);
				ps.setLong(2,endtime);
				ps.setLong(3, finatime);
				int p=ps.executeUpdate();
				System.out.println(p);
				System.out.println("Success");
			    
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			
			
			
		}

	}
