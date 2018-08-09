/**
 * 
 */
package com.Decentralized.dao;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import com.Decentralized.Rsa.RSA;
import com.Decentralized.bean.DataBean;
import com.Decentralized.bean.KeyServerBean;
import com.Decentralized.bean.RegisterBean;
import com.Decentralized.db.AbstractDataAccessObject;
import com.Decentralized.db.SqlConstants;
import com.Decentralized.exception.ConnectionException;
import com.Decentralized.exception.DataNotFoundException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

public class UserDAO extends AbstractDataAccessObject {
	public Connection con = null;
	public PreparedStatement ps = null, ps1 = null, ps2 = null, pstm = null;
	public ResultSet rs = null, rs1 = null, rs2 = null;
	Cipher cipher;
	SecureRandom random;
	KeyPairGenerator generator;
	KeyPair pair;
	Key pubKey;
	Key privKey;

	public ArrayList<KeyServerBean> generatePairedKeys(String userid)
			throws DataNotFoundException, ConnectionException {

		ArrayList<KeyServerBean> al = new ArrayList<KeyServerBean>();
		KeyServerBean sb;
		String sql = SqlConstants._USER_KEYS;
		String sql1 = SqlConstants._VIEW_KEY_SERVERS;
		String sql2 = SqlConstants._ADD_PAIREDKEY;

		try {
			con = new AbstractDataAccessObject().getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userid);
			System.out.println("1");
			rs = ps.executeQuery();
			System.out.println("2");
			if (rs.next()) {
				ps = con.prepareStatement(sql);
				ps.setString(1, userid);
				System.out.println("1");
				rs = ps.executeQuery();
				while (rs.next()) {
					byte[] bArray = null, bArray1 = null;
					System.out.println("3");
					sb = new KeyServerBean();
					sb.setKeyServerId(rs.getInt(1));
					bArray = rs.getBytes(2);
					bArray1 = rs.getBytes(3);
					InputStream input = new ByteArrayInputStream(bArray);
					ObjectInputStream ois = new ObjectInputStream(input);
					Key pubK = (Key) ois.readObject();
					System.out.println(pubK);
					InputStream input1 = new ByteArrayInputStream(bArray1);
					ObjectInputStream ois1 = new ObjectInputStream(input1);
					Key priK = (Key) ois1.readObject();
					System.out.println(priK);
					sb.setPrivKey(priK);
					sb.setPubKey(pubK);
					// sb.setPrivateKey(rs.getString(2));
					// sb.setPublicKey(rs.getString(3));
					al.add(sb);
				}
			} else {
				System.out.println("in else............");
				try {
					Security
							.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
					cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
					random = SecureRandom.getInstance("SHA1PRNG", "SUN");
					generator = KeyPairGenerator.getInstance("RSA");
					generator.initialize(1024, random);
					pair = generator.generateKeyPair();
					System.out.println("pair====" + pair);
					pubKey = pair.getPublic();
					System.out.println("Public Key====" + pubKey.toString());
					privKey = pair.getPrivate();
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ObjectOutputStream objOstream = new ObjectOutputStream(baos);
					objOstream.writeObject(pubKey);
					byte[] bArray = baos.toByteArray();

					ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
					ObjectOutputStream objOstream1 = new ObjectOutputStream(
							baos1);
					objOstream1.writeObject(privKey);
					byte[] bArray1 = baos1.toByteArray();
					String s = privKey.toString();
					System.out.println("Private Key====" + privKey);
					ps = con.prepareStatement(sql1);
					rs = ps.executeQuery();
					while (rs.next()) {
						System.out
								.println(rs.getInt(1) + "........rs" + userid);
						ps1 = con.prepareStatement(sql2);
						ps1.setInt(1, rs.getInt(1));
						ps1.setString(2, userid);
						// ps1.setString(3,privKey.toString());
						// ps1.setString(4,privKey.toString());
						// System.out.println("Private Key===="+privKey);
						ps1.setBytes(3, bArray);
						ps1.setBytes(4, bArray1);
						int rows = ps1.executeUpdate();
						if (rows > 0) {
							sb = new KeyServerBean();
							sb.setKeyServerId(rs.getInt(1));
							sb.setPrivateKey(privKey.toString());
							sb.setPublicKey(privKey.toString());
							sb.setPrivKey(privKey);
							sb.setPubKey(pubKey);
							System.out.println("Private Key====" + privKey
									+ sb.getPrivateKey());
							al.add(sb);
						} else {
							con.rollback();
						}

					}

				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchProviderException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchPaddingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		} catch (Exception e) {
			System.out.println("4");
			e.printStackTrace();
			throw new DataNotFoundException("Sorry no data .....");
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					throw new ConnectionException(
							"Sorry for Technical Problem .....");
				}
			}
		}
		System.out.println(al.size());

		return al;

	}

	public Vector<RegisterBean> viewCredentials(String user)
			throws DataNotFoundException, ConnectionException {

		Vector<RegisterBean> al = new Vector<RegisterBean>();
		RegisterBean kb;
		String sql = SqlConstants._VIEW_CRC;
		try {
			con = new AbstractDataAccessObject().getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, user);
			rs = ps.executeQuery();
			while (rs.next()) {
				kb = new RegisterBean();
				kb.setUserName(rs.getString(1));
				kb.setScretkey(rs.getInt(2));
				kb.setPrkey(rs.getString(3));
				kb.setPubkey(rs.getString(4));
				kb.setHashfunction(rs.getString(5));
				kb.setPreference(rs.getString(6));
				al.add(kb);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DataNotFoundException("Sorry no data .....");
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					throw new ConnectionException(
							"Sorry for Technical Problem .....");
				}
			}
		}
		System.out.println(al.size());

		return al;

	}

	
	
	
	
	
	
	public Vector<RegisterBean> viewCredentials1(String user)
	throws DataNotFoundException, ConnectionException {

Vector<RegisterBean> al = new Vector<RegisterBean>();
RegisterBean kb;
String sql = SqlConstants._VIEW_CRCONE;
try {
	con = new AbstractDataAccessObject().getConnection();
	ps = con.prepareStatement(sql);
	ps.setString(1, user);
	rs = ps.executeQuery();
	while (rs.next()) {
		kb = new RegisterBean();
		kb.setUserName(rs.getString(1));
		kb.setFullName(rs.getString(2));
		kb.setDob(rs.getString(3));
		kb.setAge(rs.getInt(4));
		kb.setEmail(rs.getString(5));
		kb.setPhoneNo(rs.getString(6));
		kb.setUserid(rs.getInt(7));
		al.add(kb);
	}

} catch (Exception e) {
	e.printStackTrace();
	throw new DataNotFoundException("Sorry no data .....");
} finally {
	if (con != null) {
		try {
			con.close();
		} catch (Exception e) {
			throw new ConnectionException(
					"Sorry for Technical Problem .....");
		}
	}
}
System.out.println(al.size());

return al;

}
	
	
	
	
	
	
	
	
	
	
	
	
	public ArrayList<RegisterBean> viewtime() throws DataNotFoundException,
			ConnectionException {

		ArrayList<RegisterBean> al = new ArrayList<RegisterBean>();
		RegisterBean kb;
		String sql = SqlConstants._VIEW_TIME;
		try {
			con = new AbstractDataAccessObject().getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				kb = new RegisterBean();
				kb.setSign(rs.getInt(1));
				kb.setAge(rs.getInt(2));
				al.add(kb);

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DataNotFoundException("Sorry no data .....");
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					throw new ConnectionException(
							"Sorry for Technical Problem .....");
				}
			}
		}
		System.out.println(al.size());

		return al;

	}

	public boolean uploadData(String fileName, String userName) {
		boolean flag = false;
		Key pubK = null;
		Key priK = null;
		int no_of_servers = 0;
		int block = 0;
		String file = fileName.substring(fileName.lastIndexOf("\\") + 1,
				fileName.length());
		try {
			con = new AbstractDataAccessObject().getConnection();
			System.out.println("Con..." + con + "..." + userName
					+ "...........file........." + fileName + "''';''" + file);
			String sql = SqlConstants._NO_OF_SERVERS;
			String sql1 = SqlConstants._USER_KEYS;
			String sql2 = SqlConstants._ADD_CLOUDSERVER_DATA;
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				no_of_servers = rs.getInt(1);
			}
			ps1 = con.prepareStatement(sql1);
			ps1.setString(1, userName);
			rs1 = ps1.executeQuery();
			if (rs1.next()) {

				byte[] bArray = null, bArray1 = null;
				System.out.println("3");
				bArray = rs1.getBytes(2);
				bArray1 = rs1.getBytes(3);
				InputStream input = new ByteArrayInputStream(bArray);
				ObjectInputStream ois = new ObjectInputStream(input);
				pubK = (Key) ois.readObject();
				System.out.println(pubK);
				InputStream input1 = new ByteArrayInputStream(bArray1);
				ObjectInputStream ois1 = new ObjectInputStream(input1);
				priK = (Key) ois1.readObject();
				// System.out.println(priK);

				FileReader fr = new FileReader(fileName);
				BufferedReader br = new BufferedReader(fr);
				String strLine = "";
				String fileData = "";
				System.out.println("........");
				System.out.println("........");
				while ((strLine = br.readLine()) != null) {

					fileData = fileData + strLine;

				}
				byte[] filesize = fileData.getBytes();
				RSA rsa = new RSA();
				byte[] b;
				int loops, si, sj;
				// String st="";
				int size = filesize.length;
				// Encrypt data into number of blocks
				for (loops = 0, si = 0; size > 0; loops++) {
					System.out.println("blocks...." + loops);
					// String st="st"+loops;
					String st = "";
					if (size > 116)
						sj = si + 116;
					else
						sj = si + size;
					st = fileData.substring(si, sj);
					b = rsa.RSA_Enc(st, pubK);
					String codeword = Base64.encode(b);
					int temp = no_of_servers;
					// Replicate data
					for (int i = 0; temp > 0; i++) {
						// PreparedStatement pstm=null;

						pstm = con.prepareStatement(sql2);
						pstm.setInt(1, temp);
						pstm.setString(2, userName);
						pstm.setString(3, file);
						pstm.setInt(4, loops);
						pstm.setBytes(5, b);
						pstm.setString(6, codeword);
						int rows = pstm.executeUpdate();
						if (rows > 0)
							flag = true;
						else
							flag = false;

						System.out.println("loops...." + loops + "...." + i);

						System.out.println("server...." + no_of_servers);
						temp = temp - 1;
						System.out.println("serveraaa...." + no_of_servers);
					}

					size = size - 116;
					si = sj;
				}
			}
			/*
			 * ps=con.prepareStatement(sql); ps.setString(1,userName); int
			 * rows=ps.executeUpdate(); if(rows>0){ flag=true;
			 * System.out.println("....."); }
			 */

		} catch (NullPointerException ne) {
			ne.printStackTrace();
			// throw new DataAcessException("Data base connection Failed");
		} catch (Exception se) {
			se.printStackTrace();
			// throw new
			// DataNotFoundException("Sql exception riser/datanot found");
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return flag;
	}

	public ArrayList<RegisterBean> retreveCategory() throws SQLException {

		ArrayList<RegisterBean> awf = null;

		con = new AbstractDataAccessObject().getConnection();
		pstm = con
				.prepareStatement("select distinct gname from usergroup  order by gname");

		rs = pstm.executeQuery();
		awf = new ArrayList<RegisterBean>();

		while (rs.next()) {

			RegisterBean wf = new RegisterBean();

			System.out.println("--------" + rs.getString(1));

			wf.setGroupName(rs.getString(1));

			awf.add(wf);

		}

		return awf;

	}

	public ArrayList<RegisterBean> retreveGroup() throws SQLException {

		ArrayList<RegisterBean> awf = null;

		con = new AbstractDataAccessObject().getConnection();
		pstm = con
				.prepareStatement("select u.userid,u.username,u.lid,g.gname,u.isignature,g.signature,u.yos,u.role,u.doc from userdata u ,usergroup g where u.gname=g.gname order by g.gname");

		rs = pstm.executeQuery();
		awf = new ArrayList<RegisterBean>();

		while (rs.next()) {

			RegisterBean wf = new RegisterBean();

			System.out.println("--------" + rs.getString(1));
			wf.setUserid(rs.getInt(1));
			wf.setUserName(rs.getString(2));
			wf.setLicence(rs.getString(3));
			wf.setGroupName(rs.getString(4));
			wf.setIsignature(rs.getLong(5));
			wf.setSignature(rs.getLong(6));
			wf.setYos(rs.getInt(7));
			wf.setUserrole(rs.getString(8));
			wf.setDoc(rs.getString(9));

			awf.add(wf);

		}

		return awf;

	}

	public ArrayList<RegisterBean> retreveUser(int userid) throws SQLException {

		ArrayList<RegisterBean> awf = null;

		con = new AbstractDataAccessObject().getConnection();
		pstm = con
				.prepareStatement("select u.userid,u.username,u.lid,g.gname,u.isignature,g.signature,u.yos,u.role,u.doc,u.age from userdata u ,usergroup g where u.gname=g.gname  and u.userid=?");
		pstm.setInt(1, userid);
		rs = pstm.executeQuery();
		awf = new ArrayList<RegisterBean>();

		while (rs.next()) {

			RegisterBean wf = new RegisterBean();

			System.out.println("--------" + rs.getString(1));
			wf.setUserid(rs.getInt(1));
			wf.setUserName(rs.getString(2));
			wf.setLicence(rs.getString(3));
			wf.setGroupName(rs.getString(4));
			wf.setIsignature(rs.getLong(5));
			wf.setSignature(rs.getLong(6));
			wf.setYos(rs.getInt(7));
			wf.setUserrole(rs.getString(8));
			wf.setDoc(rs.getString(9));
			wf.setAge(rs.getInt(10));

			awf.add(wf);

		}

		return awf;

	}

	public int uploadDataa(String fileName, String userName) {
		boolean flag = false;
		int blocks = -1;
		Key pubK = null;
		Key priK = null;
		int no_of_servers = 0;
		int block = 0;
		String file = fileName.substring(fileName.lastIndexOf("\\") + 1,
				fileName.length());
		try {
			con = new AbstractDataAccessObject().getConnection();
			System.out.println("Con..." + con + "..." + userName
					+ "...........file........." + fileName + "''';''" + file);
			String sql = SqlConstants._NO_OF_SERVERS;
			String sql1 = SqlConstants._USER_KEYS;
			String sql2 = SqlConstants._ADD_CLOUDSERVER_DATA;
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				no_of_servers = rs.getInt(1);
			}
			ps1 = con.prepareStatement(sql1);
			ps1.setString(1, userName);
			rs1 = ps1.executeQuery();
			if (rs1.next()) {

				byte[] bArray = null, bArray1 = null;
				System.out.println("3");
				bArray = rs1.getBytes(2);
				bArray1 = rs1.getBytes(3);
				InputStream input = new ByteArrayInputStream(bArray);
				ObjectInputStream ois = new ObjectInputStream(input);
				pubK = (Key) ois.readObject();
				System.out.println(pubK);
				InputStream input1 = new ByteArrayInputStream(bArray1);
				ObjectInputStream ois1 = new ObjectInputStream(input1);
				priK = (Key) ois1.readObject();
				// System.out.println(priK);

				FileReader fr = new FileReader(fileName);
				BufferedReader br = new BufferedReader(fr);
				String strLine = "";
				String fileData = "";
				System.out.println("........");
				System.out.println("........");
				while ((strLine = br.readLine()) != null) {

					fileData = fileData + strLine;

				}
				byte[] filesize = fileData.getBytes();
				RSA rsa = new RSA();
				byte[] b;
				int loops, si, sj;
				// String st="";
				int size = filesize.length;
				// Encrypt data into number of blocks
				for (loops = 0, si = 0; size > 0; loops++) {
					System.out.println("blocks...." + loops);
					blocks = loops;
					// String st="st"+loops;
					String st = "";
					if (size > 116)
						sj = si + 116;
					else
						sj = si + size;
					st = fileData.substring(si, sj);
					b = rsa.RSA_Enc(st, pubK);
					String codeword = Base64.encode(b);
					int temp = no_of_servers;
					// Replicate data
					for (int i = 0; temp > 0; i++) {
						// PreparedStatement pstm=null;

						pstm = con.prepareStatement(sql2);
						pstm.setInt(1, temp);
						pstm.setString(2, userName);
						pstm.setString(3, file);
						pstm.setInt(4, loops);
						pstm.setBytes(5, b);
						pstm.setString(6, codeword);
						int rows = pstm.executeUpdate();
						if (rows > 0)
							flag = true;
						else
							flag = false;

						System.out.println("loops...." + loops + "...." + i);

						System.out.println("server...." + no_of_servers);
						temp = temp - 1;
						System.out.println("serveraaa...." + no_of_servers);
					}

					size = size - 116;
					si = sj;
				}
				blocks = blocks + 1;
				System.out.println("blockssssssssssss" + blocks);
			}

		} catch (NullPointerException ne) {
			ne.printStackTrace();
			// throw new DataAcessException("Data base connection Failed");
		} catch (Exception se) {
			se.printStackTrace();
			// throw new
			// DataNotFoundException("Sql exception riser/datanot found");
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return blocks;
	}

	public ArrayList<DataBean> viewListOfFiles(String userid)
			throws DataNotFoundException, ConnectionException {

		ArrayList<DataBean> al = new ArrayList<DataBean>();
		DataBean kb;
		String sql = SqlConstants._VIEW_FILES_LIST;
		try {
			con = new AbstractDataAccessObject().getConnection();
			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				kb = new DataBean();
				kb.setFileName(rs.getString(1));
				al.add(kb);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DataNotFoundException("Sorry no data .....");
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					throw new ConnectionException(
							"Sorry for Technical Problem .....");
				}
			}
		}
		System.out.println(al.size());

		return al;

	}

	public ArrayList<DataBean> viewListOfForwardedFiles(String userid)
			throws DataNotFoundException, ConnectionException {

		ArrayList<DataBean> al = new ArrayList<DataBean>();
		DataBean kb;
		String sql = SqlConstants._VIEW_FORWARDEDFILES_LIST;
		try {
			con = new AbstractDataAccessObject().getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userid);
			rs = ps.executeQuery();
			while (rs.next()) {
				kb = new DataBean();
				kb.setFileName(rs.getString(1));
				kb.setUserid(rs.getInt(2));
				al.add(kb);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DataNotFoundException("Sorry no data .....");
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					throw new ConnectionException(
							"Sorry for Technical Problem .....");
				}
			}
		}
		System.out.println(al.size());

		return al;

	}

	public ArrayList<DataBean> viewData(String userid, String fileName)
			throws DataNotFoundException, ConnectionException {

		ArrayList<DataBean> al = new ArrayList<DataBean>();
		System.out.println(userid);
		DataBean kb;
		String sql1 = SqlConstants._USER_KEYS;
		String sql2 = SqlConstants._USER_DATA;
		String sql = SqlConstants._NO_OF_SERVERS;
		Key pubK = null;
		Key priK = null;
		int no_of_servers = 0;
		try {

			con = new AbstractDataAccessObject().getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				no_of_servers = rs.getInt(1);
				System.out.println("no_of_servers" + no_of_servers);
			}
			ps1 = con.prepareStatement(sql1);
			ps1.setString(1, userid);
			rs1 = ps1.executeQuery();
			if (rs1.next()) {

				byte[] bArray = null, bArray1 = null;
				System.out.println("3");
				bArray = rs1.getBytes(2);
				bArray1 = rs1.getBytes(3);
				InputStream input = new ByteArrayInputStream(bArray);
				ObjectInputStream ois = new ObjectInputStream(input);
				pubK = (Key) ois.readObject();
				// System.out.println(pubK);
				InputStream input1 = new ByteArrayInputStream(bArray1);
				ObjectInputStream ois1 = new ObjectInputStream(input1);
				priK = (Key) ois1.readObject();
				System.out.println(priK);
			}
			ps2 = con.prepareStatement(sql2);
			ps2.setString(1, userid);
			ps2.setString(2, fileName);
			ps2.setInt(3, no_of_servers);
			System.out.println(fileName + "iiiii" + userid + ",,,"
					+ no_of_servers);
			rs2 = ps2.executeQuery();
			DataBean sb;
			System.out.println(rs2);
			while (rs2.next()) {
				byte[] bArray = null;
				sb = new DataBean();
				bArray = rs2.getBytes(1);
				System.out.println("enc...." + bArray.toString());
				System.out.println("bArray...." + bArray);
				String codeword = rs2.getString(5);
				System.out.println("codeword........." + codeword);
				byte decodedData[] = Base64.decode(codeword);
				RSA rsa = new RSA();
				String s = rsa.RSA_Dnc(decodedData, priK);
				System.out.println("decp...." + s + "...." + rs2.getInt(4));
				sb.setDec_msg(s);
				sb.setStorageid(no_of_servers);
				sb.setUserName(userid);
				sb.setFileName(fileName);
				al.add(sb);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DataNotFoundException("Sorry no data .....");
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					throw new ConnectionException(
							"Sorry for Technical Problem .....");
				}
			}
		}
		System.out.println(al.size());

		return al;

	}

	public ArrayList<DataBean> viewForwardData(int userid, String fileName)
			throws DataNotFoundException, ConnectionException {

		ArrayList<DataBean> al = new ArrayList<DataBean>();
		DataBean kb;
		String sql1 = SqlConstants._USER_FORWARDED_KEYS;
		String sql2 = SqlConstants._USER_FORWARDED_DATA;
		String sql = SqlConstants._NO_OF_SERVERS;
		Key pubK = null;
		Key priK = null;
		int no_of_servers = 0;
		try {

			con = new AbstractDataAccessObject().getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				no_of_servers = rs.getInt(1);
				System.out.println("no_of_servers" + no_of_servers);
			}
			ps1 = con.prepareStatement(sql1);
			ps1.setInt(1, userid);
			rs1 = ps1.executeQuery();
			if (rs1.next()) {

				byte[] bArray = null, bArray1 = null;
				bArray = rs1.getBytes(2);
				bArray1 = rs1.getBytes(3);
				InputStream input = new ByteArrayInputStream(bArray);
				ObjectInputStream ois = new ObjectInputStream(input);
				pubK = (Key) ois.readObject();
				// System.out.println(pubK);
				InputStream input1 = new ByteArrayInputStream(bArray1);
				ObjectInputStream ois1 = new ObjectInputStream(input1);
				priK = (Key) ois1.readObject();
				System.out.println(priK);
			}
			ps2 = con.prepareStatement(sql2);
			ps2.setInt(1, userid);
			ps2.setString(2, fileName);
			ps2.setInt(3, no_of_servers);
			System.out.println(fileName + "iiiii" + userid + ",,,"
					+ no_of_servers);
			rs2 = ps2.executeQuery();
			DataBean sb;
			while (rs2.next()) {
				byte[] bArray = null;
				sb = new DataBean();
				String encodedCodeWord = rs2.getString(5);
				System.out.println("encodedCodeWord.........."
						+ encodedCodeWord);
				byte ChiperMsg[] = Base64.decode(encodedCodeWord);
				bArray = rs2.getBytes(1);
				System.out.println("enc...." + bArray.toString());
				System.out.println("bArray...." + bArray);
				RSA rsa = new RSA();
				String s = rsa.RSA_Dnc(ChiperMsg, priK);
				System.out.println("decp...." + s + "...." + rs2.getInt(4));
				sb.setDec_msg(s);
				sb.setStorageid(no_of_servers);
				// sb.setUserName(userid);
				sb.setFileName(fileName);
				al.add(sb);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DataNotFoundException("Sorry no data .....");
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					throw new ConnectionException(
							"Sorry for Technical Problem .....");
				}
			}
		}
		System.out.println(al.size());

		return al;

	}

	public ArrayList<RegisterBean> viewUsers(String userid)
			throws DataNotFoundException, ConnectionException {

		ArrayList<RegisterBean> al = new ArrayList<RegisterBean>();
		RegisterBean kb;
		String sql = SqlConstants._VIEW_USERS_LIST;
		try {
			con = new AbstractDataAccessObject().getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userid);
			rs = ps.executeQuery();
			while (rs.next()) {
				kb = new RegisterBean();
				kb.setUserid(rs.getInt(1));
				kb.setUserName(rs.getString(2));
				al.add(kb);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DataNotFoundException("Sorry no data .....");
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					throw new ConnectionException(
							"Sorry for Technical Problem .....");
				}
			}
		}
		System.out.println(al.size());

		return al;

	}

	public boolean forwardData(DataBean db) {
		boolean flag = false;
		try {
			con = new AbstractDataAccessObject().getConnection();
			System.out.println("Con..." + con + "." + db.getFileName() + "."
					+ db.getReceiver() + "." + db.getSender() + "."
					+ db.getStorageid());
			String sql = SqlConstants._FORWARD_DATA;
			ps = con.prepareStatement(sql);
			ps.setString(1, db.getSender());
			ps.setInt(2, db.getReceiver());
			ps.setInt(3, db.getStorageid());
			ps.setString(4, db.getFileName());
			int rows = ps.executeUpdate();
			if (rows > 0) {
				flag = true;
				System.out.println(".....");
			}
		} catch (NullPointerException ne) {
			ne.printStackTrace();
			// throw new DataAcessException("Data base connection Failed");
		} catch (SQLException se) {
			se.printStackTrace();
			// throw new
			// DataNotFoundException("Sql exception riser/datanot found");
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return flag;
	}

}
