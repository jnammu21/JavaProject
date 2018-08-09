package com.Decentralized.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.Decentralized.bean.FileTo;
import com.Decentralized.dao.UserdaoImpl;
import com.Decentralized.db.AbstractDataAccessObject;
import com.itextpdf.text.Document;

public class UploadFileAction extends HttpServlet {
	Connection con = null;
	PreparedStatement ps = null;
	long startmil = System.currentTimeMillis();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		int i = 0;
		FileTo cb = new FileTo();
		String path = "", token = "";
		boolean flag = false;

		String upfile = request.getParameter("upfile");

		cb.setUpfile(upfile);

		String tmpstamp = request.getParameter("tmpstamp");
		String prvkey = request.getParameter("prvkey");
		String pubkey = request.getParameter("pubkey");
		cb.setTmpstamp(tmpstamp);
		cb.setPrvkey(prvkey);
		cb.setPubkey(pubkey);

		System.out.println(" uploaded file is" + upfile);

		String rupfile = new StringBuffer(upfile).reverse().toString();
		System.out.println(" reverse path is " + rupfile);

		StringTokenizer str1 = new StringTokenizer(rupfile, "\\");
		String tk1 = "";
		System.out.println(" total tokens are " + str1.countTokens());

		while (str1.hasMoreElements()) {
			i++;

			tk1 = str1.nextToken();
			if (i == 1) {
				token = tk1;
				System.out.println(" retreved token " + token);
			}
		}
		String filename = new StringBuffer(token).reverse().toString();
		System.out.println(" file name in action class is" + filename);
		cb.setFilename(filename);
		String username = (String) request.getSession()
				.getAttribute("username");
		cb.setUsername(username);
		String mkey = request.getParameter("mkey");
		cb.setMkey(mkey);
		System.out.println(" key is " + mkey);
		try {
			flag = new UserdaoImpl().uploadFile(cb);

			System.out.println("in Action class vcb..........." + flag);
			if (flag) {
				request.setAttribute("status",
						"Data Inserted to Cloud Successfully");
				path = "./UploadFiles.jsp";
			} else {
				request
						.setAttribute("status",
								"Data Inserting Fail/May be you Entered  Wrong key size ");
				path = "./UploadFiles.jsp";
			}

		} catch (Exception e) {
			request.setAttribute("status", "Some Technical Problems");
			path = "./UploadFiles.jsp";
			e.printStackTrace();

		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		
		
		long endtime = System.currentTimeMillis();
		System.out.println("End  time" + endtime);
		long finatime = endtime - startmil;
		System.out.println(finatime + "::::::::::Millis");

		try {
			
			
			
		     con=new AbstractDataAccessObject().getConnection();
			ps = con
					.prepareStatement("insert  into starttime values((select nvl(max(tid),100)+1 from starttime),?,?,?)");
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
