package com.Decentralized.bean;

public class StorageServerBean {
	private int serverid;
	private String StorageServerName;
	public int getServerid() {
		return serverid;
	}
	public void setServerid(int serverid) {
		this.serverid = serverid;
	}
	public String getStorageServerName() {
		return StorageServerName;
	}
	public void setStorageServerName(String storageServerName) {
		StorageServerName = storageServerName;
	}
	private String userid;
	private String enc_Msg;
	private String codeword;
	private String fileName;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getEnc_Msg() {
		return enc_Msg;
	}
	public void setEnc_Msg(String encMsg) {
		enc_Msg = encMsg;
	}
	public String getCodeword() {
		return codeword;
	}
	public void setCodeword(String codeword) {
		this.codeword = codeword;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
