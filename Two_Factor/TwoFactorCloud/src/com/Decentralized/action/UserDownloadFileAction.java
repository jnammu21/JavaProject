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
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import com.Decentralized.db.AbstractDataAccessObject;
import com.Decentralized.exception.ConnectionException;
import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class UserDownloadFileAction extends HttpServlet {
	Connection con = null;
	PreparedStatement ps = null;
	long startmil = System.currentTimeMillis();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int i = 0;
		long totalTime = 0;
		String d = "";
		FileTo cb = new FileTo();
		RequestDispatcher rd = null;
		String path = "", token = "";
		boolean flag = false;

		String filename = request.getParameter("filename");
		String key = request.getParameter("mkey");
		cb.setFilename(filename);
		cb.setMkey(key);

		/*
		 * File file =new File(filename);
		 * 
		 * if(file.exists()){
		 * 
		 * double bytes = file.length(); double kilobytes = (bytes / 1024);
		 */

		String username = (String) request.getSession()
				.getAttribute("username");

		System.out.println("in action Class login is........." + username);

		Vector vcb = null;
		try {

			vcb = new UserdaoImpl().UsergetFile(cb);

		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (!vcb.isEmpty()) {
			request.setAttribute("vcb", vcb);
			request.setAttribute("etime", totalTime);
			request.setAttribute("status",
					"YOUR CRDENTIALS ARE CORRECT PLS DOWNLOAD FILE");
			System.out.println("--------->yes");
			path = "UserDownloadFile.jsp";
		} else {
			request
					.setAttribute("status",
							"ENTER CORRECT KEY TO DOWNLOAD FILE");
			path = "UserDownloadFile.jsp";
		}

		rd = request.getRequestDispatcher(path);
		rd.forward(request, response);

		long endtime = System.currentTimeMillis();
		System.out.println("End  time" + endtime);
		long finatime = endtime - startmil;
		System.out.println(finatime + "::::::::::Millis");

		try {
			
		     con=new AbstractDataAccessObject().getConnection();
			ps = con
					.prepareStatement("insert  into decrptedtimecal values((select nvl(max(tid),100)+1 from decrptedtimecal),?,?,?)");
			ps.setLong(1, startmil);
			ps.setLong(2, endtime);
			ps.setLong(3, finatime);
			int p = ps.executeUpdate();
			System.out.println(p);
			System.out.println("Success");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
