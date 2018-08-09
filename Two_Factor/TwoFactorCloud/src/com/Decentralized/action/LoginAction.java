package com.Decentralized.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Decentralized.dao.LoginDAO;
import com.Decentralized.util.UtilConstants;

public class LoginAction extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userName = request.getParameter("loginid");
		String password = request.getParameter("password");
		String target = "";
		String role = "";

		try {
			role = (String) new LoginDAO().loginCheck(userName, password);
			System.out.println("in LoginAction Role is.........." + role);

			if (role.equalsIgnoreCase(UtilConstants._OWNER)) {

				request.setAttribute("status", "Welcome   to" + userName);
				target = UtilConstants._ADMIN_HOME;
				session.setAttribute(UtilConstants._LOGINUSER, userName);
				session.setAttribute(UtilConstants._ROLE, role);
				session.setAttribute(UtilConstants._PASSWORD, password);

			} else if (role.equalsIgnoreCase(UtilConstants._USER)) {
				request.setAttribute("status", "Welcome   to" + userName);
				target = UtilConstants._USER_HOME;
				session.setAttribute(UtilConstants._LOGINUSER, userName);
				session.setAttribute(UtilConstants._ROLE, role);
				session.setAttribute(UtilConstants._PASSWORD, password);
			}

			else if (role.equalsIgnoreCase(UtilConstants._CLOUD)) {
				request.setAttribute("status", "Welcome to  " + userName);
				target = UtilConstants._CLOUD_HOME;
				session.setAttribute(UtilConstants._LOGINUSER, userName);
				session.setAttribute(UtilConstants._ROLE, role);
				session.setAttribute(UtilConstants._PASSWORD, password);

			}

			else if (role.equalsIgnoreCase(UtilConstants._KDC1)) {
				request.setAttribute("status", "Welcome  to  " + userName);
				target = UtilConstants._KDC_HOME1;
				session.setAttribute(UtilConstants._LOGINUSER, userName);
				session.setAttribute(UtilConstants._ROLE, role);
				session.setAttribute(UtilConstants._PASSWORD, password);
			}

			else if (role.equalsIgnoreCase(UtilConstants._KDC2)) {
				request.setAttribute("status", "Welcome  to  " + userName);
				target = UtilConstants._KDC_HOME2;
				session.setAttribute(UtilConstants._LOGINUSER, userName);
				session.setAttribute(UtilConstants._ROLE, role);
				session.setAttribute(UtilConstants._PASSWORD, password);

			}

			else if (role.equalsIgnoreCase(UtilConstants._KDC3)) {
				request.setAttribute("status", "Welcome  to  " + userName);
				target = UtilConstants._KDC_HOME3;
				session.setAttribute(UtilConstants._LOGINUSER, userName);
				session.setAttribute(UtilConstants._ROLE, role);
				session.setAttribute(UtilConstants._PASSWORD, password);

			}

			else if (role == "" || role == null) {
				System.out.println("Role" + role);

				request.setAttribute("status", UtilConstants._INVALID_USER);

				target = UtilConstants._LOGIN_PAGE;
			}

		} catch (NullPointerException e) {

			request.setAttribute("status", UtilConstants._INVALID_USER);
			target = UtilConstants._LOGIN_PAGE;

		} catch (Exception e) {

			request.setAttribute("status", UtilConstants._SERVER_BUSY);
			target = UtilConstants._LOGIN_PAGE;

		} finally {

			RequestDispatcher rd = request.getRequestDispatcher(target);
			rd.forward(request, response);
		}

	}
}
