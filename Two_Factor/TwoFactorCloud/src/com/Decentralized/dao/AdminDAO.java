package com.Decentralized.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.Decentralized.bean.KeyServerBean;
import com.Decentralized.bean.RegisterBean;
import com.Decentralized.bean.StorageServerBean;
import com.Decentralized.db.AbstractDataAccessObject;
import com.Decentralized.db.SqlConstants;
import com.Decentralized.exception.ConnectionException;
import com.Decentralized.exception.DataNotFoundException;



public class AdminDAO extends AbstractDataAccessObject{
	public Connection con=null;
	public PreparedStatement ps=null;
	public ResultSet rs=null;
	public ArrayList<StorageServerBean> viewStorageServers()throws DataNotFoundException,ConnectionException {
		
		 ArrayList<StorageServerBean> al= new ArrayList<StorageServerBean>();
		 StorageServerBean sb;
		 String sql=SqlConstants._VIEW_STORAGE_SERVERS;
		 try {
			 con=new AbstractDataAccessObject().getConnection();
			 ps=con.prepareStatement(sql);
			 rs=ps.executeQuery();
			 while(rs.next())
			 {
				 sb=new StorageServerBean();
					sb.setServerid(rs.getInt(1));
					sb.setStorageServerName(rs.getString(2));	
			    al.add(sb);

			 }

		} catch (Exception e) {
			e.printStackTrace();
			throw new DataNotFoundException("Sorry no data .....");
		}
		finally{
			if(con!=null)
			{
				try{
				con.close();
				}catch (Exception e) {
					throw new ConnectionException("Sorry for Technical Problem .....");
				}
			}
		}
		 System.out.println(al.size());
		 
		return al;  
		   
	}
	
	public ArrayList<StorageServerBean> viewStorageDetails(int serverid)throws DataNotFoundException,ConnectionException {
		
		 ArrayList<StorageServerBean> al= new ArrayList<StorageServerBean>();
		 StorageServerBean sb;
		 String sql=SqlConstants._VIEW_STORAGE_SERVERS_DETAILS;
		 try {
			 con=new AbstractDataAccessObject().getConnection();
			 ps=con.prepareStatement(sql);
			 ps.setInt(1,serverid);
			 rs=ps.executeQuery();
			 while(rs.next())
			 {
				 sb=new StorageServerBean();
				    sb.setFileName(rs.getString(2));
				    byte[] encMsg=rs.getBytes(3);
				    System.out.println(encMsg.toString());
				    sb.setEnc_Msg(new String(encMsg));
				    sb.setCodeword(rs.getString(4));
					sb.setUserid(rs.getString(5));
			    al.add(sb);

			 }
			 
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataNotFoundException("Sorry no data .....");
		}
		finally{
			if(con!=null)
			{
				try{
				con.close();
				}catch (Exception e) {
					throw new ConnectionException("Sorry for Technical Problem .....");
				}
			}
		}
		 System.out.println(al.size());
		 
		return al;  
		   
	}
	
	public ArrayList<KeyServerBean> viewKeyStorageDetails(int serverid)throws DataNotFoundException,ConnectionException {
		
		 ArrayList<KeyServerBean> al= new ArrayList<KeyServerBean>();
		 KeyServerBean sb;
		 String sql=SqlConstants._VIEW_KEY_SERVERS_DETAILS;
		 try {
			 con=new AbstractDataAccessObject().getConnection();
			 ps=con.prepareStatement(sql);
			 ps.setInt(1,serverid);
			 rs=ps.executeQuery();
			 while(rs.next())
			 {
				 sb=new KeyServerBean();
				    byte[] sec_msg=rs.getBytes(2);
				    System.out.println(sec_msg.toString());
				    sb.setPrivateKey(new String(sec_msg));
					sb.setUserid(rs.getString(3));
			    al.add(sb);

			 }
			 
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataNotFoundException("Sorry no data .....");
		}
		finally{
			if(con!=null)
			{
				try{
				con.close();
				}catch (Exception e) {
					throw new ConnectionException("Sorry for Technical Problem .....");
				}
			}
		}
		 System.out.println(al.size());
		 
		return al;  
		   
	}
	
	public ArrayList<RegisterBean> viewUsers()throws DataNotFoundException,ConnectionException {
		 ArrayList<RegisterBean> al= new ArrayList<RegisterBean>();
		 RegisterBean sb;
		 String sql=SqlConstants._VIEW_USERS;
		 try {
			 con=new AbstractDataAccessObject().getConnection();
			 ps=con.prepareStatement(sql);
			 rs=ps.executeQuery();
			 while(rs.next())
			 {
				    sb=new RegisterBean();
					sb.setUserid(rs.getInt(1));
					sb.setFullName(rs.getString(2));
					sb.setUserName(rs.getString(3));
					sb.setPhoneNo(rs.getString(4));
					sb.setEmail(rs.getString(5));
					sb.setScretkey(rs.getInt(6));
					sb.setPrkey(rs.getString(7));
					sb.setPubkey(rs.getString(8));
					sb.setHashfunction(rs.getString(9));
					al.add(sb);

			 }
			 
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataNotFoundException("Sorry no data .....");
		}
		finally{
			if(con!=null)
			{
				try{
				con.close();
				}catch (Exception e) {
					throw new ConnectionException("Sorry for Technical Problem .....");
				}
			}
		}
		 System.out.println(al.size());
		 
		return al;  
		   
	}
	
	public ArrayList<RegisterBean> viewKdcTwoUsers()throws DataNotFoundException,ConnectionException {
		 ArrayList<RegisterBean> al= new ArrayList<RegisterBean>();
		 RegisterBean sb;
		 String sql=SqlConstants._VIEW_KDC_USERS;
		 try {
			 con=new AbstractDataAccessObject().getConnection();
			 ps=con.prepareStatement(sql);
			 rs=ps.executeQuery();
			 while(rs.next())
			 {
				    sb=new RegisterBean();
					sb.setUserid(rs.getInt(1));
					sb.setFullName(rs.getString(2));
					sb.setUserName(rs.getString(3));
					sb.setPhoneNo(rs.getString(4));
					sb.setEmail(rs.getString(5));
					sb.setScretkey(rs.getInt(6));
					sb.setPrkey(rs.getString(7));
					sb.setPubkey(rs.getString(8));
					sb.setHashfunction(rs.getString(9));
					al.add(sb);

			 }
			 
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataNotFoundException("Sorry no data .....");
		}
		finally{
			if(con!=null)
			{
				try{
				con.close();
				}catch (Exception e) {
					throw new ConnectionException("Sorry for Technical Problem .....");
				}
			}
		}
		 System.out.println(al.size());
		 
		return al;  
		   
	}
	
	
	
	
	public ArrayList<RegisterBean> viewKdcThreeUsers()throws DataNotFoundException,ConnectionException {
		 ArrayList<RegisterBean> al= new ArrayList<RegisterBean>();
		 RegisterBean sb;
		 String sql=SqlConstants._VIEW_KDCTHRREE_USERS;
		 try {
			 con=new AbstractDataAccessObject().getConnection();
			 ps=con.prepareStatement(sql);
			 rs=ps.executeQuery();
			 while(rs.next())
			 {
				    sb=new RegisterBean();
					sb.setUserid(rs.getInt(1));
					sb.setFullName(rs.getString(2));
					sb.setUserName(rs.getString(3));
					sb.setPhoneNo(rs.getString(4));
					sb.setEmail(rs.getString(5));
					sb.setScretkey(rs.getInt(6));
					sb.setPrkey(rs.getString(7));
					sb.setPubkey(rs.getString(8));
					sb.setHashfunction(rs.getString(9));
					al.add(sb);

			 }
			 
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataNotFoundException("Sorry no data .....");
		}
		finally{
			if(con!=null)
			{
				try{
				con.close();
				}catch (Exception e) {
					throw new ConnectionException("Sorry for Technical Problem .....");
				}
			}
		}
		 System.out.println(al.size());
		 
		return al;  
		   
	}
	
	
	
	
	
	
	
	
	public ArrayList<RegisterBean> viewUsers1()throws DataNotFoundException,ConnectionException {
		 ArrayList<RegisterBean> al= new ArrayList<RegisterBean>();
		 RegisterBean sb;
		 String sql=SqlConstants._VIEW_USERS1;
		 try {
			 con=new AbstractDataAccessObject().getConnection();
			 ps=con.prepareStatement(sql);
			 rs=ps.executeQuery();
			 while(rs.next())
			 {
				    sb=new RegisterBean();
					sb.setUserid(rs.getInt(1));
					sb.setFullName(rs.getString(2));
					sb.setUserName(rs.getString(3));
					sb.setPhoneNo(rs.getString(4));
					sb.setEmail(rs.getString(5));
					sb.setScretkey(rs.getInt(6));
					sb.setPrkey(rs.getString(7));
					sb.setPubkey(rs.getString(8));
					sb.setHashfunction(rs.getString(9));
					al.add(sb);

			 }
			 
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataNotFoundException("Sorry no data .....");
		}
		finally{
			if(con!=null)
			{
				try{
				con.close();
				}catch (Exception e) {
					throw new ConnectionException("Sorry for Technical Problem .....");
				}
			}
		}
		 System.out.println(al.size());
		 
		return al;  
		   
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public ArrayList<KeyServerBean> viewKeyServers()throws DataNotFoundException,ConnectionException {
		
		 ArrayList<KeyServerBean> al= new ArrayList<KeyServerBean>();
		 KeyServerBean kb;
		 String sql=SqlConstants._VIEW_KEY_SERVERS;
		 try {
			 con=new AbstractDataAccessObject().getConnection();
			 ps=con.prepareStatement(sql);
			 rs=ps.executeQuery();
			 while(rs.next())
			 {
				 kb=new KeyServerBean();
					kb.setKeyServerId(rs.getInt(1));
					kb.setKeyServerName(rs.getString(2));	
			    al.add(kb);

			 }
			 
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataNotFoundException("Sorry no data .....");
		}
		finally{
			if(con!=null)
			{
				try{
				con.close();
				}catch (Exception e) {
					throw new ConnectionException("Sorry for Technical Problem .....");
				}
			}
		}
		 System.out.println(al.size());
		 
		return al;  
		   
	}
	
	
	
	
	
	public boolean addServer(String serverName) {
		boolean flag=false;
		 try{
			 con=new AbstractDataAccessObject().getConnection();
			 System.out.println("Con..."+con);
			 String sql=SqlConstants._ADD_SERVER;
			  ps=con.prepareStatement(sql);
			  ps.setString(1,serverName);
			  int rows=ps.executeUpdate();
			  if(rows>0){
				  flag=true;
				  System.out.println(".....");
			  }
		  }catch(NullPointerException ne){
			   ne.printStackTrace();
			   //throw new DataAcessException("Data base connection Failed");
		   }catch(SQLException se){
			   se.printStackTrace();
			   //throw new DataNotFoundException("Sql exception riser/datanot found");
		   }finally{
			   try{
				   ps.close();
				   con.close();
			   }catch(Exception e){
				   e.printStackTrace();
			   }
		   }
		
		return flag;
	}
	public boolean addKeyServer(String serverName) {
		boolean flag=false;
		 try{
			 con=new AbstractDataAccessObject().getConnection();
			 System.out.println("Con..."+con);
			 String sql=SqlConstants._ADD_KEYSERVER;
			  ps=con.prepareStatement(sql);
			  ps.setString(1,serverName);
			  int rows=ps.executeUpdate();
			  if(rows>0){
				  flag=true;
				  System.out.println(".....");
			  }
		  }catch(NullPointerException ne){
			   ne.printStackTrace();
			   //throw new DataAcessException("Data base connection Failed");
		   }catch(SQLException se){
			   se.printStackTrace();
			   //throw new DataNotFoundException("Sql exception riser/datanot found");
		   }finally{
			   try{
				   ps.close();
				   con.close();
			   }catch(Exception e){
				   e.printStackTrace();
			   }
		   }
		
		return flag;
	}


}
