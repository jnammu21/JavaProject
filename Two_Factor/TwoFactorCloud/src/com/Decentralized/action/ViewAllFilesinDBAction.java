package com.Decentralized.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.Decentralized.bean.FileTo;
import com.Decentralized.dao.UserdaoImpl;
import com.Decentralized.exception.ConnectionException;
public class ViewAllFilesinDBAction extends HttpServlet {
public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	
	
	int i = 0;
	String d = "";
	FileTo cb = new FileTo();
	RequestDispatcher rd = null;
	String path = "", token = "";
	boolean flag = false;
   String filename = request.getParameter("filename");
	String key=request.getParameter("mkey");
	cb.setFilename(filename);
	cb.setMkey(key);

	

		String username = (String) request.getSession().getAttribute(
				"username");

		System.out.println("in action Class login is........." + username);

		Vector vcb=null;
		try {
			 vcb = new UserdaoImpl().viewAllFilesFromDB(cb);
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (!vcb.isEmpty()) {
			request.setAttribute("vcb", vcb);
			request.setAttribute("status", "FILES WHICH ARE IN DB ENGINE");
			path ="viewAllDBFiles.jsp";
		} else {
			request.setAttribute("status", "NO DATA");
			path ="viewAllDBFiles.jsp";
		}
		rd = request.getRequestDispatcher(path);
		rd.forward(request, response);

		}

		}
