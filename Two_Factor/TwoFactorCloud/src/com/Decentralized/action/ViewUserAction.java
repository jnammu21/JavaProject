package com.Decentralized.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Decentralized.bean.RegisterBean;
import com.Decentralized.dao.AdminDAO;
import com.Decentralized.exception.DataNotFoundException;
import com.Decentralized.util.UtilConstants;



public class ViewUserAction extends HttpServlet {

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

		 String path="";
			
			ArrayList<RegisterBean> al;
			try
			{
				al=new AdminDAO().viewUsers();
				if(!al.isEmpty())
				{
					path=UtilConstants._VIEW_USERS;
					request.setAttribute("servers",al);
					
				}
				else
				{
					path=UtilConstants._KDC_HOME1;
					request.setAttribute("status",UtilConstants._NO_DATA);
					
				}
			}
			catch(DataNotFoundException e)
			{
				path=UtilConstants._KDC_HOME1;
				request.setAttribute("status",e.getMessage());
				e.printStackTrace();
			}
			
			catch (Exception e) {
				path=UtilConstants._KDC_HOME1;
				request.setAttribute("status",UtilConstants._NO_DATA);
				e.printStackTrace();
			}
			finally
			{
				RequestDispatcher rd=request.getRequestDispatcher(path);
				rd.forward(request,response);
			
			}
		}

	}

