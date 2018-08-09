package com.Decentralized.action;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Decentralized.bean.RegisterBean;
import com.Decentralized.dao.LoginDAO;
import com.Decentralized.util.UtilConstants;
import com.sun.org.apache.commons.beanutils.BeanUtils;

public class UpdateUserDataAction extends HttpServlet {


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
		boolean flag=false;
		String path="";
		
		
		
		
		
		RegisterBean rb=new RegisterBean();
		try{
			Map map = request.getParameterMap();
			BeanUtils.populate(rb, map);
			
			
			flag=new LoginDAO().updateUser(rb);
		if(flag)
		{
			path=UtilConstants._OWNER_HOME;
			request.setAttribute("status","Data Updated Sucessfully....");
			
		}
		else
		{
			path=UtilConstants._LOGIN_PAGE;
			request.setAttribute("status",UtilConstants._ADDED_FAILED);
		}
		}catch (Exception e) {
			path=UtilConstants._LOGIN_PAGE;
			request.setAttribute("status",UtilConstants._ADDED_FAILED);
			
		}
		finally{
			RequestDispatcher rd=request.getRequestDispatcher(path);
			rd.forward(request, response);
		}
	}

}
