package com.Decentralized.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Decentralized.bean.RegisterBean;
import com.Decentralized.dao.LoginDAO;
import com.Decentralized.dao.UserDAO;


public class CheckUserIDAvail extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userid=request.getParameter("userid");
		System.out.println("user mail id"+userid);
		boolean flag=false;
		ArrayList<RegisterBean> v=null;
		try{
			UserDAO d=new UserDAO();
			
			
			
				 v=d.retreveCategory();
				 
				 System.out.println("Vector"+v);
	
			flag=new LoginDAO().getUserId(userid);
			
			System.out.println("flag"+flag);
		}catch(Exception e){
			e.printStackTrace();
		}
		if(userid==null||userid==""){
			 request.setAttribute("v", v);
			out.println("<center><font color=red>Enter user id</font></center>");
		}
		else{
		if(flag){
			request.setAttribute("v", v);
			out.println("<center><font color=white>This username Already  available</font></center>");
		}else
		{
			  request.setAttribute("v", v);
			 out.println("<center><font color=white> This user Not available</font></center>");
		}
	}
		out.flush();
		out.close();
	}

}
