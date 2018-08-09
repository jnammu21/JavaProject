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

public class RegisteruserAction extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean flag = false;
		String path = "";
        RegisterBean rb = new RegisterBean();
		try {
			Map map = request.getParameterMap();
			BeanUtils.populate(rb, map);

			String it = new LoginDAO().registerUser1(rb);
			if (it != null) {
				path = UtilConstants._SUCCESS_PAGE;
				request.setAttribute("status",
						" Registration Completed Sucessfully....");
				request.setAttribute("status1", " Your Token ID:  " + it);
			} else {
				path = UtilConstants._LOGIN_PAGE;
				request.setAttribute("status", UtilConstants._ADDED_FAILED);
			}
		} catch (Exception e) {
			path = UtilConstants._LOGIN_PAGE;
			request.setAttribute("status", UtilConstants._ADDED_FAILED);

		} finally {
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
		}
	}

}
