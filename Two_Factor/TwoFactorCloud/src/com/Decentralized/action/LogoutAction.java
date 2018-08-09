package com.Decentralized.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction extends HttpServlet {

	private static final long serialVersionUID = 1L;


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


        HttpSession session=request.getSession();
		
		System.out.println("logout");
		session.invalidate();
		request.setAttribute("status", "Logout successfully");
		RequestDispatcher rd = request.getRequestDispatcher("./LoginForm.jsp");
		rd.forward(request, response);	
	}

}
