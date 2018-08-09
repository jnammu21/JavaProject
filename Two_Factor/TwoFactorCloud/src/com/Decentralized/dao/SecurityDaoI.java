package com.Decentralized.dao;

import com.Decentralized.bean.ChangePasswordTo;
import com.Decentralized.bean.RegisterTo;
import com.Decentralized.exception.ConnectionException;


public interface SecurityDaoI {
	
	
	public String loginCheck(RegisterTo lt)throws ConnectionException;
	
	
	
	
	

}
