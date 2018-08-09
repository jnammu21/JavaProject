package com.Decentralized.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import com.Decentralized.Rsa.CryptMsgUtil;
import com.Decentralized.bean.RegisterBean;
import com.Decentralized.db.AbstractDataAccessObject;
import com.Decentralized.db.SqlConstants;
import com.Decentralized.util.DateWrapper;

public class LoginDAO extends AbstractDataAccessObject {
	
	
	

	public Connection con = null;
	public PreparedStatement ps = null, ps1 = null, ps2 = null;
	public ResultSet rs = null, rs1 = null, rs2 = null;
	
	 private static final String CHAR_LIST =
	        "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	    private static final int RANDOM_STRING_LENGTH = 10;
	
	      public String generateRandomString(){
            StringBuffer randStr = new StringBuffer();
	        for(int i=0; i<RANDOM_STRING_LENGTH; i++){
	            int number = getRandomNumber();
	            char ch = CHAR_LIST.charAt(number);
	            randStr.append(ch);
	        }
	        return randStr.toString();
	    }
	     
	    /**
	     * This method generates random numbers
	     * @return int
	     */
	    private int getRandomNumber() {
	        int randomInt = 0;
	        Random randomGenerator = new Random();
	        randomInt = randomGenerator.nextInt(CHAR_LIST.length());
	        if (randomInt - 1 == -1) {
	            return randomInt;
	        } else {
	            return randomInt - 1;
	        }
	    }
	
	
	
	
	
	
	
	
	
	
	

	public String loginCheck(String userName, String password) {
		String sql = SqlConstants._LOGIN_CHECK;
		String role = null;
		System.out.println("userName" + userName + "password" + password);
		try {
			con = new AbstractDataAccessObject().getConnection();
			System.out.println("con" + con);
			ps = con.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				role = rs.getString(1);
				System.out.println("role" + role);
			}
		} catch (SQLException e) {

		} catch (Exception e) {

		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return role;
	}
	public boolean getUserId(String userid) {
		boolean flag = false;
		try {
			con = new AbstractDataAccessObject().getConnection();
			System.out.println("Con..." + con);
     	ps = con
					.prepareStatement("select username from userdetails  where username=?");
			ps.setString(1, userid);
			rs = ps.executeQuery();
			if (rs.next()) {
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

	public boolean deleteUser(String userid) {
		boolean flag = false;
		try {
			con = new AbstractDataAccessObject().getConnection();
			System.out.println("Con..." + con);
			ps = con
					.prepareStatement("update logindetails set USERSTATUS='deActive' where userid=?");
			ps.setInt(1, Integer.parseInt(userid));
			int x = ps.executeUpdate();
			if (x > 0) {
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

	public boolean changePassword(String userid, String oldPass, String newPass) {
		boolean flag = false;
		try {
			con = new AbstractDataAccessObject().getConnection();
			System.out.println("Con..." + con + ".." + userid + ".." + oldPass
					+ ".." + newPass);
			String sql = SqlConstants._CHANGE_PASSWORD;
			ps = con.prepareStatement(sql);
			ps.setString(1, newPass);
			ps.setString(2, userid);
			ps.setString(3, oldPass);
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

	public boolean registerUser(RegisterBean rb) {
		boolean flag = false;
		try {
			con = new AbstractDataAccessObject().getConnection();
			System.out.println("Con..." + con);
			String sql = SqlConstants._REGISTER_USER;
			ps = con.prepareStatement(sql);
			ps.setString(1, rb.getUserName());
			ps.setString(2, rb.getPassword());
			ps.setString(3, rb.getPhoneNo());
			ps.setString(4, rb.getEmail());
			ps.setString(5, rb.getFullName());
			ps.setString(6, rb.getRole());
			int x = ps.executeUpdate();
			if (x > 0) {
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

	public String registerUser1(RegisterBean rb) {
		
		
		
		boolean flag = false;
		String it = null;
		String doc = null;
		long signature = 0;
		long isignature = 0;
		int uid = 0;
		String lid = null;
		StringBuffer sb = new StringBuffer();
		try {
			con = new AbstractDataAccessObject().getConnection();

			System.out.println("Con..." + con);
			int age = rb.getAge();
			Random random = new Random();
			isignature = (long) (Math.random() * 100000 + 5123400000L);
			int randage = random.nextInt(99);
			// System.out.println(signature);
			System.out.println(randage);

			System.out.println(age * randage);
			lid = "LID-" + uid;
			it = "[" + lid + "," + age + "," + age * randage + "," + signature
					+ "]";

			LoginDAO ld = new LoginDAO();
			String privatekey = ld.nextSessionId();
			System.out.println("Private key   value" + privatekey);

			CryptMsgUtil s = new CryptMsgUtil("passPhrase");
			String cip = s.encrypt(privatekey);

            StringBuffer sb2 = new StringBuffer();
			sb2.append(cip);
        	String n= generateRandomString();
		    System.out.println("Hash Dynamic  Creation:::"+n);
			
			
			
			
			
		   // Hash Genaration

			StringBuffer hexString = new StringBuffer();

			String password = "12";

			MessageDigest md;
			try {
				md = MessageDigest.getInstance("SHA-256");

				md.update(password.getBytes());

				byte byteData[] = md.digest();

				// convert the byte to hex format method 1
				sb = new StringBuffer();
				for (int i = 0; i < byteData.length; i++) {
					sb.append(Integer
							.toString((byteData[i] & 0xff) + 0x100, 16)
							.substring(1));
				}

				System.out.println("Hex format : " + sb.toString());

				// convert the byte to hex format method 2

			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 
			System.out.println("cipher  value" + cip);
			String dob = DateWrapper.parseDate(rb.getDob());
			String urole = rb.getUserrole();
			System.out.println(" in dao dob" + dob);
			String sql = SqlConstants._REGISTER_USER1;
			ps = con.prepareStatement(sql);
			ps.setString(1, rb.getUserName());
			ps.setString(2, rb.getPassword());
			ps.setString(3, rb.getFullName());
			ps.setString(4, dob);
			ps.setInt(5, rb.getAge());
			ps.setString(6, rb.getEmail());
			ps.setString(7, rb.getPhoneNo());
			ps.setString(8, rb.getRole());
			System.out.println("Role:::::::" + rb.getRole());
			ps.setString(9, rb.getKdc());
			ps.setInt(10, randage);
			ps.setString(11, privatekey);
			ps.setString(12, cip);
			ps.setString(13, n);
			ps.setString(14, rb.getRadio());
			System.out.println("Radio Button  Value" + rb.getRadio());
			ps.setLong(15, isignature);
			int x = ps.executeUpdate();
			if (x > 0) {
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

		return it;
	}

	public String nextSessionId() {
		Random random = new Random();
		return new BigInteger(130, random).toString(32);

	}

	public boolean updateUser(RegisterBean rb) {
		boolean flag = false;

		try {
			con = new AbstractDataAccessObject().getConnection();

			System.out.println("Con..." + con);
			String sql = SqlConstants._UPDATE_USER;
			ps = con.prepareStatement(sql);
			ps.setString(1, rb.getUserName());
			ps.setInt(2, rb.getAge());
			ps.setString(3, rb.getLicence());
			ps.setLong(4, rb.getIsignature());
			ps.setString(5, rb.getUserrole());
			ps.setString(6, rb.getDoc());
			ps.setInt(7, rb.getUserid());
			int x = ps.executeUpdate();
			if (x > 0) {
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
