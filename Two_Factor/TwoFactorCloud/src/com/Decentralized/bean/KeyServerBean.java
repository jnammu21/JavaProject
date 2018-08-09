package com.Decentralized.bean;

public class KeyServerBean {
	
	private String keyServerName;
	
	public String getKeyServerName() {
		return keyServerName;
	}

	public void setKeyServerName(String keyServerName) {
		this.keyServerName = keyServerName;
	}

	public int getKeyServerId() {
		return keyServerId;
	}

	public void setKeyServerId(int keyServerId) {
		this.keyServerId = keyServerId;
	}

	private int keyServerId;
	private String publicKey;
	private String privateKey;
	private Object s;
	private Object privKey;
	private Object pubKey;

	public Object getPrivKey() {
		return privKey;
	}

	public void setPrivKey(Object privKey) {
		this.privKey = privKey;
	}

	public Object getPubKey() {
		return pubKey;
	}

	public void setPubKey(Object pubKey) {
		this.pubKey = pubKey;
	}

	public Object getS() {
		return s;
	}

	public void setS(Object s) {
		this.s = s;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	private String userid;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
}
