package com.Decentralized.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Decentralized.dao.SecurityDaoImpl;
import com.Decentralized.exception.ConnectionException;
import com.Decentralized.util.UtilConstants;


public class KeyAvailabilityAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = "";
		boolean flag = false;

		String target = "";

		try {

			SecurityDaoImpl sdi = new SecurityDaoImpl();

			String filekey = request.getParameter("mkey");

			target = request.getParameter("path");

			flag = sdi.Availability(filekey);

			if (flag == false) {

				request.setAttribute("status", UtilConstants._USER_AVAILABLE);

				path = target;

			} else {

				request
						.setAttribute("status",
								UtilConstants._USER_NO_AVAILABLE);

				path = target;

			}
		} catch (ConnectionException e) {
			request.setAttribute("status1", e.getMessage());
			path = "./LoginPage.jsp";

		}
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);

	}

}

	
	
	
	
	
	
	
	
	
	
	
	

	

