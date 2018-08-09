package com.Decentralized.bean;

public class DataBean {
	
	private int storageid;
	private int cloudStorageid;
	private int block;
	private int userid;
	private String userName;
	private String groupName;
	private long signature;
	public long getSignature() {
		return signature;
	}
	public void setSignature(long signature) {
		this.signature = signature;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	private String enc_msg;
	private String dec_msg;
	private int receiver;
	private String sender;
	public int getReceiver() {
		return receiver;
	}
	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getDec_msg() {
		return dec_msg;
	}
	public void setDec_msg(String decMsg) {
		dec_msg = decMsg;
	}
	private String fileName;
	public int getStorageid() {
		return storageid;
	}
	public void setStorageid(int storageid) {
		this.storageid = storageid;
	}
	public int getCloudStorageid() {
		return cloudStorageid;
	}
	public void setCloudStorageid(int cloudStorageid) {
		this.cloudStorageid = cloudStorageid;
	}
	public int getBlock() {
		return block;
	}
	public void setBlock(int block) {
		this.block = block;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEnc_msg() {
		return enc_msg;
	}
	public void setEnc_msg(String encMsg) {
		enc_msg = encMsg;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
