package com.Decentralized.db;

public class SqlConstants {
	
	 public static final String _LOGIN_CHECK="select role from userdetails where username=? and password=?";

	 public static final String _REGISTER_USER="INSERT INTO userdetails VALUES((select nvl(max(userid),10)+1 from userdetails),?,?,?,?,?,?)";
	
	 public static final String _REGISTER_USER1="INSERT INTO userdetails VALUES((select nvl(max(userid),1000)+1 from userdetails),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	 
	 public static final String _UPDATE_USER="update userdata set username=? ,   age=? ,   lid=? ,   isignature=? , role=? , doc=? where userid=?";
	 
	 public static final String _ADD_SERVER="INSERT INTO CLOUDS VALUES((select nvl(max(CLOUDSTORAGEID),0)+1 from CLOUDS),?)";
	 
	 public static final String _ADD_KEYSERVER="INSERT INTO KEYSERVERS VALUES((select nvl(max(KEYSERVERID),0)+1 from KEYSERVERS),?)";
	
     public static final String _CHANGE_PASSWORD="update userdata set password=? where username=? and password=? ";

	 public static final String _VIEW_STORAGE_SERVERS ="SELECT CLOUDSTORAGEID,CLOUDSTORAGENAME FROM CLOUDS ORDER BY CLOUDSTORAGEID";
	 
	 public static final String _VIEW_STORAGE_SERVERS_DETAILS ="SELECT CLOUDSTORAGE.USERID,CLOUDSTORAGE.FILENAME,CLOUDSTORAGE.ENCRPT_MSG,CLOUDSTORAGE.CODEWORD,USERDETAILS.USERNAME FROM CLOUDSTORAGE,USERDETAILS WHERE CLOUDSTORAGE.BLOCK=0 AND CLOUDSTORAGE.CLOUDSTORAGEID=? AND USERDETAILS.USERID=CLOUDSTORAGE.USERID";
	 
	 public static final String _VIEW_KEY_SERVERS_DETAILS="SELECT KEYSERVERDATA.USERID,KEYSERVERDATA.SECRETKEY,USERDETAILS.USERNAME FROM KEYSERVERDATA,USERDETAILS WHERE KEYSERVERDATA.KEYSERVERIDREF=? AND USERDETAILS.USERID=KEYSERVERDATA.USERID";
	 
	 public static final String _VIEW_KEY_SERVERS ="SELECT KEYSERVERID,KEYSERVERNAME FROM KEYSERVERS ORDER BY KEYSERVERID";
	 
	 public static final String _VIEW_TIME ="select count(ISIGNATURE),count(distinct SIGNATURE) from userdata";
	 
	 public static final String _VIEW_CRC="select username,scretkey,privatekey,publickey ,hashfunction,preference from userdetails  where username=?";
	 public static final String _VIEW_CRCONE="select username,fullname,dob,age,email,phonenumber,userid from userdetails  where username=?";

	 public static final String _CHECK_AVAILABILITY = "select username from userdetails where username=?"; 
	 public static final String _VIEW_THREE_USERS="";
	 public static final String _VIEW_USERS ="SELECT USERID,FULLNAME,USERNAME,PHONENUMBER,EMAIL,scretkey,privatekey,publickey,hashfunction FROM USERDETAILS where kdc='kdc1'";
	 public static final String _VIEW_KDC_USERS="SELECT USERID,FULLNAME,USERNAME,PHONENUMBER,EMAIL,scretkey,privatekey,publickey,hashfunction FROM USERDETAILS where kdc='kdc2'";
	 
	 public static final String  _VIEW_KDCTHRREE_USERS="SELECT USERID,FULLNAME,USERNAME,PHONENUMBER,EMAIL,scretkey,privatekey,publickey,hashfunction FROM USERDETAILS where kdc='kdc3'";
	 
	 
	 public static final String _VIEW_USERS1="SELECT USERID,FULLNAME,USERNAME,PHONENUMBER,EMAIL,scretkey,privatekey,publickey,hashfunction FROM USERDETAILS where kdc='kdc2'";
	 public static final String _VIEW_KEYS ="SELECT KEYSERVERIDREF,SECRETKEY,PUBLICKEY FROM KEYSERVERDATA";
    
	 public static final String _ADD_PAIREDKEY="INSERT INTO KEYSERVERDATA VALUES(?,(select userid from userdata where username=?),?,?)";
	 
	 public static final String _NO_OF_SERVERS="SELECT count(rownum) from clouds";
	 
	 public static final String _USER_KEYS="SELECT KEYSERVERDATA.KEYSERVERIDREF,KEYSERVERDATA.SECRETKEY,KEYSERVERDATA.PUBLICKEY from KEYSERVERDATA where keyserverdata.USERID=(select userid from userdata where username=?)";
	 
	 public static final String _USER_FORWARDED_KEYS="SELECT KEYSERVERDATA.KEYSERVERIDREF,KEYSERVERDATA.SECRETKEY,KEYSERVERDATA.PUBLICKEY from KEYSERVERDATA where keyserverdata.USERID=?";
	 
	 public static final String _ADD_CLOUDSERVER_DATA="INSERT INTO CLOUDSTORAGE VALUES((select nvl(max(STORAGEID),0)+1 from CLOUDSTORAGE),?,(select userid from userdata WHERE USERNAME=?),?,?,?,?)";

	 public static final String _ADD_CLOUD_DATA="INSERT INTO CLOUDDATA VALUES((select nvl(max(CID),555)+1 from CLOUDDATA),?,?,sysdate)";

	 public static final String _VIEW_FILES_LIST="SELECT DISTINCT FILENAME FROM CLOUDSTORAGE";
	 
	 public static final String _VIEW_FORWARDEDFILES_LIST="SELECT DISTINCT FILENAME,SENDFROM FROM FORWARDDATA WHERE SENDTO=(SELECT DISTINCT USERID FROM KEYSERVERDATA WHERE USERID=(select userid from userdetails where username=?))";
	 
	// public static final String _VIEW_FORWARDEDFILES_LIST="SELECT DISTINCT FILENAME,SENDFROM FROM FORWARDDATA WHERE SENDTO=(select userid from userdetails where username=upper(?))";
	 
	 
	 public static final String _USER_DATA="SELECT CLOUDSTORAGE.ENCRPT_MSG,CLOUDSTORAGE.BLOCK,CLOUDSTORAGE.CLOUDSTORAGEID,CLOUDSTORAGE.STORAGEID,CLOUDSTORAGE.CODEWORD from CLOUDSTORAGE where CLOUDSTORAGE.USERID=(select userid from userdata where username=?) and CLOUDSTORAGE.FILENAME=? and CLOUDSTORAGE.CLOUDSTORAGEID=? order by CLOUDSTORAGE.BLOCK";
	 
	 public static final String _USER_FORWARDED_DATA="SELECT CLOUDSTORAGE.ENCRPT_MSG,CLOUDSTORAGE.BLOCK,CLOUDSTORAGE.CLOUDSTORAGEID,CLOUDSTORAGE.STORAGEID,CLOUDSTORAGE.CODEWORD from CLOUDSTORAGE where CLOUDSTORAGE.USERID=? and CLOUDSTORAGE.FILENAME=? and CLOUDSTORAGE.CLOUDSTORAGEID=? order by CLOUDSTORAGE.BLOCK";
		
	 public static final String _VIEW_USERS_LIST="SELECT USERID,USERNAME FROM USERDETAILS WHERE USERNAME <> UPPER(?) AND LOGINTYPE <> 'admin' ";
	 
	 public static final String _FORWARD_DATA="INSERT INTO FORWARDDATA VALUES((select userid from userdetails where username=?),?,?,?)";
	 
    }
