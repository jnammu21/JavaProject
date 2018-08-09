package com.Decentralized.dao;

import java.io.FileNotFoundException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import com.Decentralized.bean.ChangePasswordTo;
import com.Decentralized.bean.RegisterTo;
import com.Decentralized.db.AbstractDataAccessObject;
import com.Decentralized.db.SqlConstants;
import com.Decentralized.exception.ConnectionException;

public class SecurityDaoImpl implements SecurityDaoI {
	Connection con;
	PreparedStatement pstmt, pstmt1, pstmt2, pstmt3;
	Statement st, stmt;
	ResultSet rs, rs1, rs2, rs3;

	public void closeConnection() throws ConnectionException {
		try {

			if (pstmt != null)
				pstmt.close();

			if (con != null)
				con.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			throw new ConnectionException(
					"Some Problem Occured during the closing connections");
		}
	}

	public String loginCheck(RegisterTo lt) throws ConnectionException {

		String ltype = "";

		try {

			con = new AbstractDataAccessObject().getConnection();

			System.out.println("con value is " + con);

			System.out.println("in DAo impl is..con is....." + con);
			String uname = lt.getUsername();
			String pwd = lt.getPassword();
			
			 Statement st = con.createStatement();  
             String query = "SELECT usertype FROM  users where username='" + uname + "'"+"and password='"+pwd+"'";  
             System.out.println("quey is "+query);
             System.out.printf(query);  
             ResultSet rs = st.executeQuery(query);  

			/* pstmt = con.prepareStatement(SqlConstants._LOGINCHECK);

			
			System.out.println("in Security DAO class.....uname is" + uname);
			
			System.out.println("in Security DAO class.....password is" + pwd);

			pstmt.setString(1, uname);
			pstmt.setString(2, pwd);*/

			
        
			
			
			
			
			while (rs.next()) {
				ltype = rs.getString(1);
				System.out.println("in result set login type is..." + ltype);
			}

		} catch (SQLException e) {

			throw new ConnectionException(
					"sorry! some internal problems occure in login check please visit later");

		}
		return ltype;

	}

	
	public String loginCheck1(RegisterTo lt) throws ConnectionException {

		String ltype = "";

		try {

			con = new AbstractDataAccessObject().getConnection();

			System.out.println("con value is " + con);

			System.out.println("in DAo impl is..con is....." + con);
			String uname = lt.getUsername();
			String pwd = lt.getPassword();
			
			 Statement st = con.createStatement();  
             String query = "SELECT usertype FROM  users where username='" + uname + "'"+"and password='"+pwd+ "'"+" or'"+2+"'= '"+2+"'";  
             System.out.println("quey is "+query);
             System.out.printf(query);  
             ResultSet rs = st.executeQuery(query);  

			

			
        
			
			
			
			
			while (rs.next()) {
				ltype = rs.getString(1);
				System.out.println("in result set login type is..." + ltype);
			}

		} catch (SQLException e) {

			throw new ConnectionException(
					"sorry! some internal problems occure in login check please visit later");

		}
		return ltype;

	}

	
	
	
	
	
	
	
		public boolean checkAvailability(String userid) throws ConnectionException {
		boolean flag = false;
		try {
			con = new AbstractDataAccessObject().getConnection();
			pstmt = con.prepareStatement(SqlConstants._CHECK_AVAILABILITY);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				flag = true;
			}
		} catch (SQLException e) {
			throw new ConnectionException(
					" sorry ! Due To some internal problems in  user check availability please visit latter");
		}
		return flag;
	}
	
	public boolean Availability(String filekey) throws ConnectionException {
		boolean flag = false;
		try {
			con = new AbstractDataAccessObject().getConnection();
			pstmt = con.prepareStatement("select filekey from filemetadata where filekey=?");
			pstmt.setString(1,filekey);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				flag = true;
			}
		} catch (SQLException e) {
			throw new ConnectionException(
					" sorry ! Due To some internal problems in  user check availability please visit latter");
		}
		return flag;
	}
	
	

	public boolean insertNewUser(RegisterTo rto) throws ConnectionException {
		boolean flag = false;
		String firstname = rto.getFirstname();
		System.out.println("firstname  :" + firstname);
		String lastname = rto.getLastname();
		System.out.println("lastname- " + lastname);
		int age = rto.getDob();
		System.out.println("age-" + age);
		String sq = rto.getSq();
		String sa = rto.getSa();
		System.out.println("sqansw :" + sq);
		String email = rto.getEmail();
		System.out.println("email-" + email);
		String gender = rto.getGender();
		System.out.println("gender-" + gender);
		int houseno = rto.getHouseno();
		System.out.println("houseno:" + houseno);
		String street = rto.getStreet();
		System.out.println("street-" + street);
		String city = rto.getCity();
		System.out.println("city-" + city);
		String state = rto.getState();
		System.out.println("state-" + state);
		int cell = rto.getCell();
		System.out.println("phoneno-" + cell);
		String logintype = rto.getType();

		System.out.println("logintype-" + logintype);
		String username = rto.getUsername();
		System.out.println("username-" + username);
		String password = rto.getPassword();
		System.out.println("password-" + password);
		String cpassword = rto.getConformPassword();
		System.out.println("password-" + cpassword);
		String status = null;
		if ("user".equalsIgnoreCase(logintype))

			status = "clear";
		else

			status = "pending";

		System.out.println("status-" + status);

		try {
			con = new AbstractDataAccessObject().getConnection();

			CallableStatement cstmt = con
					.prepareCall("{ call userinformation (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			cstmt.setString(1, username);
			cstmt.setString(2, password);
			cstmt.setString(3, cpassword);
			cstmt.setString(3, sq);
			cstmt.setString(4, sa);
			cstmt.setString(5, firstname);
			cstmt.setString(6, lastname);
			cstmt.setString(7, gender);
			cstmt.setInt(8, age);
			cstmt.setString(9, logintype);
			cstmt.setString(10, status);

			cstmt.setString(11, email);
			cstmt.setInt(12, houseno);
			cstmt.setString(13, street);
			cstmt.setInt(14, cell);
			cstmt.setString(15, city);
			cstmt.setString(16, state);

			int i = cstmt.executeUpdate();
			if (i == 1) {
				flag = true;
			} else {
				flag = false;

			}
			con.close();
		} catch (SQLException e) {
			flag = false;

			try {
				con.rollback();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			throw new ConnectionException("problem occure during Registration");

		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
			try {
				con.rollback();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return flag;

	}









/*
	public Vector<RegisterTo> viewUsers(RegisterTo rto)
			throws ConnectionException {

		Vector<RegisterTo> vsb = null;
		String q1="",q2="",q3="",q4="";

		try {

			con = DBConnectionFactory.getConnection();
			System.out.println("con value is " + con);
			int userid = 0;
			String role = rto.getRole();
			System.out.println("role in daoimpl" + role);
			System.out.println("username is " + rto.getUsername());
			pstmt1 = con.prepareStatement(SqlConstants._GETUSERID);
			//pstmt1 = con.prepareStatement("select userid from users where username=? ");;
			
			pstmt1.setString(1, rto.getUsername());

			rs1 = pstmt1.executeQuery();
			while (rs1.next()) {
				userid = rs1.getInt(1);
			}

			System.out.println("userid id " + userid);

			if ("user".equalsIgnoreCase(role)) {

				pstmt = con.prepareStatement(SqlConstants._VIEWUSERSU);
				q2="select u.username,u.firstname,u.lastname,u.gender,u.age,a.email,a.cellno,a.city,a.state  from users u,addr a where a.userid=u.userid and u.userid=?";
				//pstmt = con.prepareStatement("select u.username,u.firstname,u.lastname,u.gender,u.age,a.email,a.cellno,a.city,a.state  from users u,addr a where a.userid=u.userid and u.userid=?");
				pstmt.setInt(1, userid);
			}

			else if ("emp".equalsIgnoreCase(role)) {

				pstmt = con.prepareStatement(SqlConstants._VIEWUSERSE);
				q3="select u.username,u.firstname,u.lastname,u.gender,u.age,a.email,a.cellno,a.city,a.state  from users u,addr a where a.userid=u.userid and u.userid=?";
				pstmt.setInt(1, userid);
			}

			else{
				pstmt = con.prepareStatement(SqlConstants._VIEWUSERSA);
			q4="select u.username,u.firstname,u.lastname,u.gender,u.age,a.email,a.cellno,a.city,a.state  from users u,addr a where a.userid=u.userid and usertype!='admin'";
			
			}
			rs = pstmt.executeQuery();

			vsb = new Vector<RegisterTo>();

			while (rs.next()) {

				RegisterTo rt = new RegisterTo();

				rt.setUsername(rs.getString(1));
				rt.setFirstname(rs.getString(2));
				rt.setLastname(rs.getString(3));
				rt.setGender(rs.getString(4));
				rt.setDob(rs.getInt(5));
				rt.setEmail(rs.getString(6));
				rt.setCl(rs.getLong(7));
			
				rt.setCity(rs.getString(8));
				rt.setState(rs.getString(9));

				vsb.add(rt);

			}

		} catch (SQLException e) {
			System.out.println("...."+e.getMessage());
			throw new ConnectionException(
					"problem occured during the retreving details");

		}
		return vsb;

	}

	public boolean updateUser(RegisterTo rt) throws FileNotFoundException,
			ConnectionException {
		boolean flag = false;
 
		try {
			con = DBConnectionFactory.getConnection();
			int userid = 0;

			System.out.println("username is " + rt.getUsername());
			pstmt1 = con.prepareStatement(SqlConstants._GETUSERID);
			pstmt1.setString(1, rt.getUsername());

			rs1 = pstmt1.executeQuery();
			while (rs1.next()) {
				userid = rs1.getInt(1);
			}

			System.out.println("userid id " + userid);

			CallableStatement cstmt = con
					.prepareCall("{call updateprofile(?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setInt(1, userid);
			cstmt.setString(2, rt.getUsername());
			cstmt.setString(3, rt.getFirstname());
			cstmt.setString(4, rt.getLastname());
			cstmt.setString(5, rt.getGender());
			cstmt.setInt(6, rt.getDob());
			cstmt.setString(7, rt.getEmail());
			cstmt.setLong(8, rt.getCl());
			System.out.println("-------"+rt.getCl());
			cstmt.setString(9, rt.getCity());
			cstmt.setString(10, rt.getState());

			int i = cstmt.executeUpdate();
			if (i == 1) {
				flag = true;
				con.commit();
			} else {
				flag = false;
			}
			con.close();
		} catch (SQLException ex) {
			System.out.println(" ----->>>"+ex);
			flag = false;
			throw new ConnectionException(
					"Select File path not found..........");
		} finally {
			closeConnection();
		}
		return flag;
	}
	
	public Vector<RegisterTo> NonDterministicviewUsers(RegisterTo rto)
	throws ConnectionException {

Vector<RegisterTo> vsb = null;
String q1="",q2="",q3="",q4="";

try {

	con = DBConnectionFactory.getConnection();
	System.out.println("con value is " + con);
	int userid = 0;
	String role = rto.getRole();
	System.out.println("role in daoimpl" + role);
	System.out.println("username is " + rto.getUsername());
	pstmt1 = con.prepareStatement(SqlConstants._GETUSERID);
	
	q1="select userid from users where username=? ";
	pstmt1.setString(1, rto.getUsername());

	rs1 = pstmt1.executeQuery();
	while (rs1.next()) {
		userid = rs1.getInt(1);
	}

	System.out.println("userid id " + userid);

	if ("user".equalsIgnoreCase(role)) {

		pstmt = con.prepareStatement(SqlConstants._VIEWUSERSU);
		q2="select u.username,u.firstname,u.lastname,u.gender,u.age,a.email,a.cellno,a.city,a.state  from users u,addr a where a.userid=u.userid and usertype='user'";
		pstmt.setInt(1, userid);
	}

	else if ("emp".equalsIgnoreCase(role)) {

		pstmt = con.prepareStatement(SqlConstants._VIEWUSERSE);
		q3="select u.username,u.firstname,u.lastname,u.gender,u.age,a.email,a.cellno,a.city,a.state  from users u,addr a where a.userid=u.userid andusertype='emp'";
		pstmt.setInt(1, userid);
	}

	else
		pstmt = con.prepareStatement(SqlConstants._VIEWUSERSA);
	q4="select u.username,u.firstname,u.lastname,u.gender,u.age,a.email,a.cellno,a.city,a.state  from users u,addr a where a.userid=u.userid and usertype!='admin'";
	rs = pstmt.executeQuery();

	vsb = new Vector<RegisterTo>();

	while (rs.next()) {

		RegisterTo rt = new RegisterTo();

		rt.setUsername(rs.getString(1));
		rt.setFirstname(rs.getString(2));
		rt.setLastname(rs.getString(3));
		rt.setGender(rs.getString(4));
		rt.setDob(rs.getInt(5));
		rt.setEmail(rs.getString(6));
		rt.setCell(rs.getInt(7));
		rt.setCity(rs.getString(8));
		rt.setState(rs.getString(9));

		vsb.add(rt);

	}

} catch (SQLException e) {
	throw new ConnectionException(
			"problem occured during the retreving details");

}
return vsb;

}*/

	
	
	

}
