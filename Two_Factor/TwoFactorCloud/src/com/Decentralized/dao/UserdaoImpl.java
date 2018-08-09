package com.Decentralized.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import com.Decentralized.bean.FileTo;
import com.Decentralized.bean.RegisterBean;
import com.Decentralized.db.AbstractDataAccessObject;
import com.Decentralized.exception.ConnectionException;

public class UserdaoImpl extends AbstractDataAccessObject {
	StringBuffer sb = new StringBuffer();
	Connection con;
	PreparedStatement pstmt, pstmt1, pstmt2, pstmt3, pstmt4, pstmt5, pstmt6,
			pstmt11, pstmt12, pstmt13, pstmt14, pstmt7, pstmt8, pstmt9,
			pstmt10, pstmt100;
	Statement st, stmt;
	ResultSet rs, rs1, rs2, rs3, rs6, rs7, rs8, rs9, rs10, rs11;
	String q1 = null, q2 = null, q3 = null, q4 = null, q5 = null;

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

	public Vector getFileFromCLD(FileTo b) throws ConnectionException,
			SQLException {

		boolean flag = true;
		con = new AbstractDataAccessObject().getConnection();
		Vector v = new Vector();

		String file = b.getFilename();
		String key = b.getMkey();
		String tk = null, tk1 = null, tk2 = null, real = null, efile = null, epath = null, ekey = null;
		String nfile = null, npath = null, nkey = null;

		/*
		 * StringTokenizer st1 = new StringTokenizer(file,"@"); while
		 * (st1.hasMoreElements()) {
		 * 
		 * tk = st1.nextToken(); real=tk; if()
		 * 
		 * System.out.println("token is ---->>>"+real); }
		 */

		efile = "*^#(543724^&*^*@" + file + "@$^^^$^^**%^%^^$%RT$!#";
		ekey = "*34$%^^&34355^@" + key + "@%&$$#34#%265%#%";
		pstmt3 = con
				.prepareStatement("select fileid,filename,filepath,masterkey  from cloudencrypteddata where filename=? and masterkey=? ");
		pstmt3.setString(1, efile);
		pstmt3.setString(2, ekey);
		rs3 = pstmt3.executeQuery();

		while (rs3.next()) {
			FileTo rto = new FileTo();
			rto.setFileid(rs3.getInt(1));
			rto.setFilename(rs3.getString(2));
			rto.setUpfile(rs3.getString(3));
			rto.setMkey(rs3.getString(4));
			rto.setEfile("E:\\Encrypted" + b.getFilename());

			v.add(rto);
		}

		return v;
	}

	public String getHash(String user, String hkey) throws ConnectionException,
			SQLException {
		con = new AbstractDataAccessObject().getConnection();

		String role = null;
		pstmt = con
				.prepareStatement("select role  from userdetails where username=? and hashfunction=?");
		pstmt.setString(1, user);
		pstmt.setString(2, hkey);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			role = rs.getString(1);
			pstmt7 = con
					.prepareStatement("update matrix set hashkey=? where username=? ");
			pstmt7.setInt(1, 1);
			pstmt7.setString(2, user);
			int k7 = pstmt7.executeUpdate();
			System.out.println("Updated" + k7);
		}
		return role;
	}

	public boolean uploadFileintocloud(FileTo b) throws Throwable {

		boolean flag = false;
		con = new AbstractDataAccessObject().getConnection();
		Vector v = null;
		int i = 0, j = 0;

		// String fpath="E:\\Encrypted"+b.getFilename();// encrypted file path
		// con.setAutoCommit(false);

		StringBuffer hexString = new StringBuffer();

		String password = "12";

		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");

			md.update(password.getBytes());

			byte byteData[] = md.digest();

			// convert the byte to hex format method 1
			sb = new StringBuffer();
			for (int g = 0; g < byteData.length; g++) {
				sb.append(Integer.toString((byteData[g] & 0xff) + 0x100, 16)
						.substring(1));
			}

			System.out.println("Hex format : " + sb.toString());

			// convert the byte to hex format method 2

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			pstmt3 = con
					.prepareStatement("select * from CLOUDENCRYPTEDDATA where dbfileid=?");
			pstmt3.setInt(1, b.getFileid());
			rs3 = pstmt3.executeQuery();
			if (rs3.next()) {
				System.out.println(" Already file is inserted into cloud");

			} else {

				String filename = null, efilename = null, filepath = null, efilepath = null, filekey = null, efilekey = null;
				Blob uploadedfile = null, euploadedfile = null;
				pstmt1 = con
						.prepareStatement("select filename,filepath,filekey from filemetadata where fileid=?");
				pstmt1.setInt(1, b.getFileid());
				rs1 = pstmt1.executeQuery();
				while (rs1.next()) {
					filename = rs1.getString(1);
					filepath = rs1.getString(2);
					filekey = rs1.getString(3);
					// uploadedfile=rs1.getBlob(4);

				}

				efilepath = "*^&^%&%$GFGVHG245#(@" + filepath
						+ "@&78979^&%%$%#$$!#";
				efilename = "*^#(543724^&*^*@" + filename
						+ "@$^^^$^^**%^%^^$%RT$!#";
				efilekey = "*34$%^^&34355^@" + filekey + "@%&$$#34#%265%#%";

				pstmt = con
						.prepareStatement("insert into CLOUDENCRYPTEDDATA values((select nvl(max(fileid),8516)+1 from CLOUDENCRYPTEDDATA),?,?,?,?,?,?)");
				pstmt.setString(1, efilename);
				pstmt.setString(2, efilepath);
				pstmt.setString(3, efilekey);
				File file4 = new File(filepath);
				FileInputStream fis4 = new FileInputStream(file4);
				pstmt.setBinaryStream(4, fis4, (int) file4.length());
				pstmt.setInt(5, b.getFileid());
				pstmt.setString(6, sb.toString());

				String key = filekey; // needs to be at least 8 characters for
				// DES
				try {
					FileInputStream fis = new FileInputStream(filepath);

					System.out.println(" ----------->>>" + "G:\\Encrypted"
							+ filename);
					FileOutputStream fos = new FileOutputStream("G:\\Encrypted"
							+ filename);
					encrypt(key, fis, fos);
					i = pstmt.executeUpdate();
				} catch (Exception e) {
					System.out.println("exception in encryption is" + e);
				}

				if (i > 0) {
					flag = true;

				} else
					flag = false;

			}

		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		return flag;

	}

	public Vector UsergetFile(FileTo b) throws ConnectionException,
			SQLException {
		long startTime = System.currentTimeMillis();
		String type = null;
		long totalTime = 0;
		boolean flag = true;
		double kb = 0;
		con = new AbstractDataAccessObject().getConnection();
		Vector v = new Vector();
		try {
			System.out.println("------>" + b.getMkey());
			int i = 0, j = 0, k = 0;
			String real1 = null, tk1 = null, tk2 = null, tk3 = null, real2 = null, real3 = null;
			String chkey = b.getMkey().trim();
			StringTokenizer st1 = new StringTokenizer(chkey, "@");
			while (st1.hasMoreElements()) {
				i++;
				tk1 = st1.nextToken();
				if (i == 2) {
					real1 = tk1;
					System.out.println("key value is---> " + real1);
				}

			}

			pstmt3 = con
					.prepareStatement("select filename,filepath,uploadedfile from CLOUDENCRYPTEDDATA where masterkey=? ");
			pstmt3.setString(1, chkey);
			rs3 = pstmt3.executeQuery();
			String efile = null, efilepath = null;
			while (rs3.next()) {
				FileTo rto = new FileTo();
				System.out.println("falnnnnnnn" + rs3.getString(1));
				efile = rs3.getString(1).trim();

				StringTokenizer st2 = new StringTokenizer(efile, "@");
				while (st2.hasMoreElements()) {
					j++;
					tk2 = st2.nextToken();
					if (j == 2) {
						real2 = tk2;
						rto.setFilename(real2);
						System.out.println("file  is ---->" + real2);
					}

				}
				efilepath = rs3.getString(2).trim();

				StringTokenizer st3 = new StringTokenizer(efilepath, "@");
				while (st3.hasMoreElements()) {
					k++;
					tk3 = st3.nextToken();
					if (k == 2) {
						real3 = tk3;
						rto.setUpfile(real3);

						System.out.println("file path is----> " + real3);
					}

				}

				String mkey = real1;

				rto.setFile(rs3.getBlob(3));

				try {
					String key = mkey; // needs to be at least 8 characters for
					// DES
					String spath = "G:\\Encrypted";
					String dpath = "G:\\Decrypted";
					System.out.println(" encrypted file path" + spath
							+ b.getFilename());
					System.out.println(" decrypted key" + mkey);
					FileInputStream fis2 = new FileInputStream(spath
							+ b.getFilename());
					FileOutputStream fos2 = new FileOutputStream(dpath
							+ b.getFilename());
					decrypt(key, fis2, fos2);

					long endTime = System.currentTimeMillis();
					totalTime = endTime - startTime;
					System.out.println(totalTime);
					rto.setTime(totalTime);
				} catch (Throwable e) {
					e.printStackTrace();
				}

				v.add(rto);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return v;
	}

	public static void encrypt(String key, InputStream is, OutputStream os)
			throws Throwable {
		encryptOrDecrypt(key, Cipher.ENCRYPT_MODE, is, os);
	}

	public static void decrypt(String key, InputStream is, OutputStream os)
			throws Throwable {
		encryptOrDecrypt(key, Cipher.DECRYPT_MODE, is, os);
	}

	public static void encryptOrDecrypt(String key, int mode, InputStream is,
			OutputStream os) throws Throwable {

		DESKeySpec dks = new DESKeySpec(key.getBytes());
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey desKey = skf.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES"); // DES/ECB/PKCS5Padding for
		// SunJCE

		if (mode == Cipher.ENCRYPT_MODE) {
			cipher.init(Cipher.ENCRYPT_MODE, desKey);
			CipherInputStream cis = new CipherInputStream(is, cipher);
			doCopy(cis, os);
		} else if (mode == Cipher.DECRYPT_MODE) {
			cipher.init(Cipher.DECRYPT_MODE, desKey);
			CipherOutputStream cos = new CipherOutputStream(os, cipher);
			doCopy(is, cos);
		}
	}

	public static void doCopy(InputStream is, OutputStream os)
			throws IOException {
		byte[] bytes = new byte[64];
		int numBytes;
		while ((numBytes = is.read(bytes)) != -1) {
			os.write(bytes, 0, numBytes);
		}
		os.flush();
		os.close();
		is.close();
	}

	public boolean upload(FileTo cb) throws FileNotFoundException,
			ConnectionException {

		boolean flag = true;
		int uid = 0;

		String upfile = cb.getUpfile();

		try {

			con = new AbstractDataAccessObject().getConnection();

			System.out.println(" file is -----------" + cb.getFilename());

			pstmt = con
					.prepareStatement("insert into cloudserver1 values(?,(select nvl(max(fileid),9695)+7 from cloudserver1),?,?,?,?,?,?)");

			pstmt.setInt(1, cb.getBlock());

			pstmt.setInt(2, cb.getPort());
			pstmt.setString(3, cb.getIp());
			pstmt.setString(4, cb.getFilename());
			pstmt.setString(5, cb.getFilepath());
			File file5 = new File(upfile);
			FileInputStream fis5 = new FileInputStream(file5);
			pstmt.setBinaryStream(6, fis5, (int) file5.length());

			String data = cb.getData();
			/*
			 * //DesEncrypter encrypter=new DesEncrypter("passPhrase"); String
			 * encriptpwd=encrypter.encrypt(data);
			 * System.out.println("Encripted data=========>>>>>"+encriptpwd);
			 * String decriptpwd=encrypter.decrypt(encriptpwd);
			 * System.out.println("decripted- Data------------->"+decriptpwd);
			 * 
			 * pstmt.setString(7, encriptpwd);
			 */

			int owner = pstmt.executeUpdate();

			if (owner > 0) {
				flag = true;
			} else {
				flag = false;

			}

		}

		catch (SQLException e) {
			e.printStackTrace();
			flag = false;
			try {
				con.rollback();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return flag;
	}

	public Vector viewAllFilesFromDB(FileTo b) throws ConnectionException,
			SQLException {

		boolean flag = true;
		con = new AbstractDataAccessObject().getConnection();
		Vector v = new Vector();
		pstmt3 = con
				.prepareStatement("select fileid,filename,filepath,filekey from filemetadata");

		rs3 = pstmt3.executeQuery();

		while (rs3.next()) {
			FileTo rto = new FileTo();

			rto.setFileid(rs3.getInt(1));
			rto.setFilename(rs3.getString(2));
			rto.setUpfile(rs3.getString(3));
			rto.setMkey(rs3.getString(4));
			v.add(rto);
		}

		return v;
	}

	public boolean uploadFile(FileTo b) throws Throwable {

		boolean flag = true;
		con = new AbstractDataAccessObject().getConnection();
		Vector v = null;
		int i = 0, j = 0;
		// String fpath="E:\\Encrypted"+b.getFilename();// encrypted file path
		// con.setAutoCommit(false);

		try {
			pstmt = con
					.prepareStatement("insert into FILEMETADATA values((select nvl(max(fileid),7361)+1 from FILEMETADATA),?,?,?,?,?,?,?)");

			pstmt.setString(1, b.getFilename());
			pstmt.setString(2, b.getUpfile());
			pstmt.setString(3, b.getMkey());
			pstmt.setString(4, b.getTmpstamp());
			pstmt.setString(5, b.getPrvkey());
			pstmt.setString(6, b.getPubkey());
			File file4 = new File(b.getUpfile());
			FileInputStream fis4 = new FileInputStream(file4);
			pstmt.setBinaryStream(7, fis4, (int) file4.length());
			i = pstmt.executeUpdate();

			if (i > 0) {
				flag = true;
			} else
				flag = false;

		} catch (Exception e) {
			System.out.println(e);
		}
		return flag;

	}

	public boolean uploadGroup(String gname) throws FileNotFoundException,
			ConnectionException {

		boolean flag = true;
		int uid = 0;
		long signature = 0;

		try {
			Random random = new Random();

			signature = (long) (Math.random() * 100000 + 3333300000L);
			con = new AbstractDataAccessObject().getConnection();

			pstmt = con
					.prepareStatement("insert into usergroup values((select nvl(max(gid),2001)+1 from usergroup),?,?)");
			pstmt.setString(1, gname);
			pstmt.setLong(2, signature);

			int owner = pstmt.executeUpdate();

			if (owner > 0) {
				flag = true;
			} else {
				flag = false;

			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
			try {
				con.rollback();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return flag;
	}

	public boolean deleteMatrix() throws FileNotFoundException,
			ConnectionException {

		boolean flag = true;
		int uid = 0;
		long signature = 0;

		try {

			con = new AbstractDataAccessObject().getConnection();

			pstmt = con.prepareStatement("truncate table matrix");

			int m = pstmt.executeUpdate();
			if (m > 0) {
				flag = true;
			} else {
				flag = false;

			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
			try {
				con.rollback();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return flag;
	}

	public boolean insertMatrix(String user) throws FileNotFoundException,
			ConnectionException {

		boolean flag = true;
		int uid = 0;

		try {

			con = new AbstractDataAccessObject().getConnection();
			pstmt = con.prepareStatement("insert into matrix values(?,?,?)");
			pstmt.setInt(1, 0);
			pstmt.setInt(2, 0);
			pstmt.setString(3, user);
			int m = pstmt.executeUpdate();
			if (m > 0) {
				flag = true;
			} else {
				flag = false;
			}

		}

		catch (SQLException e) {
			e.printStackTrace();
			flag = false;
			try {
				con.rollback();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return flag;
	}

	public Vector<RegisterBean> viewMatrix(String user)
			throws FileNotFoundException, ConnectionException {

		Vector<RegisterBean> v = new Vector<RegisterBean>();
		try {
			con = new AbstractDataAccessObject().getConnection();
             pstmt = con
					.prepareStatement("select scretkey,hashkey from matrix where username=?");

			pstmt.setString(1, user);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				RegisterBean rto = new RegisterBean();

				rto.setSkey(rs.getInt(1));
				rto.setHkey(rs.getInt(2));
				v.add(rto);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return v;
	}

	public String viewRole(String user) throws FileNotFoundException,
			ConnectionException {

		String role = null;

		try {

			con = new AbstractDataAccessObject().getConnection();

			pstmt = con
					.prepareStatement("select role from userdetails where username=?");

			pstmt.setString(1, user);

			rs = pstmt.executeQuery();
			while (rs.next()) {

				role = rs.getString(1);

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return role;
	}

	public FileTo getKey(FileTo b) throws ConnectionException, SQLException {
		con = new AbstractDataAccessObject().getConnection();

		pstmt = con
				.prepareStatement("select fileid  from cloudserver1 where filename=?");
		pstmt.setString(1, b.getFilename());
		rs = pstmt.executeQuery();

		String filename = b.getFilename();
		int key = 0;
		while (rs.next()) {
			key = rs.getInt(1);
		}
		b.setKey(key);
		b.setFilename(filename);

		return b;
	}

	public String getLid(String user, String lid) throws ConnectionException,
			SQLException {
		con = new AbstractDataAccessObject().getConnection();

		String role = null;
		pstmt = con
				.prepareStatement("select  role  from userdetails where username=? and scretkey=?");
		pstmt.setString(1, user);
		pstmt.setString(2, lid);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			role = rs.getString(1);

			pstmt7 = con
					.prepareStatement("update  matrix set scretkey=? where username=? ");
			pstmt7.setInt(1, 1);
			pstmt7.setString(2, user);
			int k7 = pstmt7.executeUpdate();
			System.out.println(k7);
		}
		return role;
	}

	public String getAge(String user, int age) throws ConnectionException,
			SQLException {
		con = new AbstractDataAccessObject().getConnection();

		String role = null;
		pstmt = con
				.prepareStatement("select logintype  from userdata where username=? and age=?");
		pstmt.setString(1, user);
		pstmt.setInt(2, age);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			role = rs.getString(1);
			pstmt7 = con
					.prepareStatement("update  matrix set age=? where username=? ");
			pstmt7.setInt(1, 1);
			pstmt7.setString(2, user);
			int k7 = pstmt7.executeUpdate();
			System.out.println(k7);
		}
		return role;
	}

	public String getSignature(String user, String signature)
			throws ConnectionException, SQLException {
		con = new AbstractDataAccessObject().getConnection();

		String role = null;
		pstmt = con
				.prepareStatement("select logintype  from userdata where username=? and signature=?");
		pstmt.setString(1, user);
		pstmt.setString(2, signature);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			role = rs.getString(1);
			pstmt7 = con
					.prepareStatement("update  matrix set sign=? where username=? ");
			pstmt7.setInt(1, 1);
			pstmt7.setString(2, user);
			int k7 = pstmt7.executeUpdate();
			System.out.println(k7);
		}
		return role;
	}

	public String getRole(String user, String userrole)
			throws ConnectionException, SQLException {
		con = new AbstractDataAccessObject().getConnection();

		String role = null;
		pstmt = con
				.prepareStatement("select logintype  from userdata where username=? and role=?");
		pstmt.setString(1, user);
		pstmt.setString(2, userrole);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			role = rs.getString(1);
			pstmt7 = con
					.prepareStatement("update  matrix set role=? where username=? ");
			pstmt7.setInt(1, 1);
			pstmt7.setString(2, user);
			int k7 = pstmt7.executeUpdate();
			System.out.println(k7);
		}
		return role;
	}

	public FileTo getFile(FileTo b) throws ConnectionException, SQLException {
		con = new AbstractDataAccessObject().getConnection();
		Vector v = null;
		String filename = "";
		int key = b.getKey();
		String data1 = "", data2 = "", data3 = "", tpadata = "", filepath = "";
		pstmt1 = con
				.prepareStatement("select filename from cloudserver1 where fileid=? ");
		System.out.println(" key value in daoimpl is " + b.getKey());
		pstmt1.setInt(1, b.getKey());
		rs1 = pstmt1.executeQuery();
		if (rs1.next()) {
			filename = rs1.getString(1);
			System.out.println(" file name in the dao impl class is "
					+ filename);

			pstmt = con
					.prepareStatement("select s1.data1,s1.filepath from cloudserver1 s1 where s1.filename=? ");
			pstmt.setString(1, filename);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				data1 = rs.getString(1);
				filepath = rs.getString(2);

			}
			b.setData1(data1);
			b.setFilepath(filepath);

			return b;

		} else {
			b = null;
		}
		return b;

	}

	
	
	
	
	
	
	
	
	public String getPrefer(String username)
	throws ConnectionException, SQLException {
con = new AbstractDataAccessObject().getConnection();

String preference = null;
pstmt = con
		.prepareStatement("select preference  from userdetails where username=?");
pstmt.setString(1,username);
rs = pstmt.executeQuery();
if (rs.next()) {
	preference = rs.getString(1);
	System.out.println("PREFERENCE VALUE::::::::::"+preference);
}
return username;
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public boolean insertRetreveDetails(FileTo b) throws ConnectionException,
			SQLException {

		boolean flag = true;
		con = new AbstractDataAccessObject().getConnection();
		Vector v = null;
		int key = b.getKey();
		String data1 = "", data2 = "", data3 = "", tpadata = "";

		pstmt = con
				.prepareStatement("insert into FileStatus values(?,?,?,?,?,?,?,?)");
		pstmt.setString(1, b.getFilename());
		pstmt.setInt(2, b.getKey());
		pstmt.setInt(3, b.getBlock());
		pstmt.setInt(4, b.getTransferblock());
		pstmt.setInt(5, b.getPendingblock());

		pstmt.setString(6, b.getRequestedserver());
		pstmt.setString(7, b.getHelpingserver());
		pstmt.setString(8, b.getBaddata());

		int i = pstmt.executeUpdate();

		if (i > 0) {
			flag = true;
		} else
			flag = false;

		return flag;
	}

	public boolean updateFile(FileTo b) throws ConnectionException,
			SQLException {

		boolean flag = true;
		con = new AbstractDataAccessObject().getConnection();
		Vector v = null;

		String data1 = "", data2 = "", data3 = "", tpadata = "";

		pstmt = con.prepareStatement("delete * from ");
		pstmt.setString(1, b.getFilename());
		pstmt.setInt(2, b.getKey());
		pstmt.setInt(3, b.getBlock());
		pstmt.setInt(4, b.getTransferblock());
		pstmt.setInt(5, b.getPendingblock());

		pstmt.setString(6, b.getRequestedserver());
		pstmt.setString(7, b.getHelpingserver());
		pstmt.setString(8, b.getBaddata());

		int i = pstmt.executeUpdate();

		if (i > 0) {
			flag = true;
		} else
			flag = false;

		return flag;
	}

	public Vector fileStatus(FileTo b) throws ConnectionException, SQLException {

		boolean flag = true;
		con = new AbstractDataAccessObject().getConnection();
		Vector v = new Vector();

		pstmt = con
				.prepareStatement("select unique filename,key,blocks,blockstransfered,pendingblocks,requestedserver,helpingserver,baddata from filestatus ");
		rs = pstmt.executeQuery();
		System.out.println(" hi sanjay");
		while (rs.next()) {
			FileTo rto = new FileTo();

			rto.setFilename(rs.getString(1));
			rto.setKey(rs.getInt(2));
			rto.setBlock(rs.getInt(3));
			rto.setTransferblock(rs.getInt(4));
			rto.setPendingblock(rs.getInt(5));
			rto.setRequestedserver(rs.getString(6));
			System.out.println(" next server value is in daoimpl is "
					+ rs.getString(7));
			rto.setHelpingserver(rs.getString(7));
			rto.setBaddata(rs.getString(8));

			v.add(rto);
		}

		return v;
	}

	public Vector serverDetails(FileTo b) throws ConnectionException,
			SQLException {

		boolean flag = true;
		con = new AbstractDataAccessObject().getConnection();
		Vector v = new Vector();

		pstmt = con.prepareStatement("select * from serverdetails ");
		rs = pstmt.executeQuery();
		System.out.println(" hi sanjay");
		while (rs.next()) {
			FileTo rto = new FileTo();

			rto.setServername(rs.getString(1));
			rto.setPort(rs.getInt(2));
			rto.setIp(rs.getString(3));
			rto.setRole(rs.getString(4));

			v.add(rto);
		}

		return v;
	}

	public String getWordData(FileTo b) throws ZipException, IOException,
			SAXException, ParserConfigurationException {

		System.out.println(" I AM IN DAOIMPL");
		String data = "";
		try {
			File myFile = new File(b.getUpfile());
			List<String> lines = new ArrayList<String>();

			ZipFile docxFile = new ZipFile(myFile);
			ZipEntry documentXML = docxFile.getEntry("word/document.xml");
			InputStream documentXMLIS = docxFile.getInputStream(documentXML);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			Document doc = dbf.newDocumentBuilder().parse(documentXMLIS);

			Element tElement = doc.getDocumentElement();
			NodeList n = (NodeList) tElement.getElementsByTagName("w:p");
			String s = "";
			for (int j = 0; j < n.getLength(); j++) {

				Node child = n.item(j);
				String line = child.getTextContent();
				data = data + line;

				if (line != null && !line.trim().isEmpty()) {

					lines.add(line.trim());

				}

			}
			System.out.println(" ---------->>> Woord document  in daoimpl"
					+ data);

		} catch (Exception e) {
			e.getStackTrace();
		}

		return data;
	}

	public Vector retreveFilesFromServer(FileTo b) throws ConnectionException,
			SQLException {
		con = new AbstractDataAccessObject().getConnection();
		boolean flag = true;

		Vector v = new Vector();

		pstmt = con
				.prepareStatement("select unique filename from cloudserver1 ");
		rs = pstmt.executeQuery();
		System.out.println(" hi sanjay");
		while (rs.next()) {
			FileTo rto = new FileTo();

			rto.setFilename(rs.getString(1));

			v.add(rto);
		}

		return v;
	}

}