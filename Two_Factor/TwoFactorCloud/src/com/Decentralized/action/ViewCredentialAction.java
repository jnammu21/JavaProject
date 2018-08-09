package com.Decentralized.action;

import java.io.IOException;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.Decentralized.bean.RegisterBean;
import com.Decentralized.dao.UserDAO;
import com.Decentralized.util.UtilConstants;

public class ViewCredentialAction extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	 String path = "";
       HttpSession session = request.getSession();
		Vector<RegisterBean> opv = null;
		String user = request.getParameter("userid");
    	System.out.println("ViewUSERs :" + user);
		try {
            opv = new UserDAO().viewCredentials(user);
			System.out.println("This is the"+opv);
			if (!opv.isEmpty()) {
                // request.setAttribute("status", UtilConstants._USER_INFO);
				session.setAttribute("userinfo", opv);
	        	path = UtilConstants._VIEW_CRC;

			} else if (opv.isEmpty()) {
				request.setAttribute("status", UtilConstants._NO_USER);
				request.setAttribute("user", user);
				path = UtilConstants._VIEW_CRC;

			} else {
				request.setAttribute("status", UtilConstants._NO_USER);
				request.setAttribute("user", user);
				path = UtilConstants._VIEW_CRC;
			}

		} catch (Exception e) {
			request.setAttribute("status", UtilConstants._INVALIED_ENTRY);
			path = UtilConstants._VIEW_CRC;

		}
		RequestDispatcher rd = request.getRequestDispatcher(path);

		rd.forward(request, response);
	}
}
