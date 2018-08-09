package com.Decentralized.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.Decentralized.bean.FileTo;
import com.Decentralized.dao.UserdaoImpl;
import com.sun.org.apache.commons.beanutils.BeanUtils;

public class SecretAction extends HttpServlet {


		public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

	FileTo rto = new FileTo();
	Map map = request.getParameterMap();
	try {
		BeanUtils.populate(rto, map);
	} catch (IllegalAccessException e1) {

		e1.printStackTrace();
	} catch (InvocationTargetException e1) {

		e1.printStackTrace();

	}
	String path = "";
	String userrole = "";
	boolean flag = false;
	HttpSession s=request.getSession();
	String login=(String) s.getAttribute("UserName");
	String lid=request.getParameter("skey");
	System.out.println(" username from session is "+login);
	System.out.println(" lid from session is "+lid);
	rto.setUsername(login);


	String role=null;
	    try 
	    {
	  flag= new UserdaoImpl().deleteMatrix();
	  flag= new UserdaoImpl().insertMatrix(login);
	  userrole= new UserdaoImpl().viewRole(login);
	  s.setAttribute("userrole", userrole);
	  System.out.println(flag);
	  role= new UserdaoImpl().getLid(login,lid);
	  System.out.println(role);
		if (role!=null) {
			
			request.setAttribute("status","Your Secret Key is Correct");
					
			path = "./verifyhash.jsp";
		} else {
			request.setAttribute("status1","");
			path = "./verifyhash.jsp";
		}
	} catch (Exception e) {
		request.setAttribute("status"," Please Enter Valid File");
		path = "./verifyhash.jsp";
	}
	RequestDispatcher rd = request.getRequestDispatcher(path);
	rd.forward(request, response);

	}

	}
