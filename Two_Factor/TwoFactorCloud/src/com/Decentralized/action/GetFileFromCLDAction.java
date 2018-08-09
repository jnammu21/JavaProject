package com.Decentralized.action;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.sql.SQLException;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Decentralized.bean.FileTo;
import com.Decentralized.dao.UserdaoImpl;
import com.Decentralized.exception.ConnectionException;
import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;


public class GetFileFromCLDAction extends HttpServlet {

	
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
				 vcb = new UserdaoImpl().getFileFromCLD(cb);
			} catch (ConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (!vcb.isEmpty()) {
				request.setAttribute("vcb", vcb);
				request.setAttribute("status", "USERS INFORMATION");
				path ="GetFileCLD.jsp";
			} else {
				request.setAttribute("status", "NO DATA");
				path ="GetFileCLD.jsp";
			}
			rd = request.getRequestDispatcher(path);
			rd.forward(request, response);

			}

			}
