package com.Decentralized.bean;

import java.sql.Blob;
public class FileTo {
	
	private Blob file;
	private double kb;
	private double cost;
	private long time;
	private String prvkey;
	private String pubkey;
	private String tmpstamp;
	
	public long getTime() {
		return time;
	}
	public String getPrvkey() {
		return prvkey;
	}
	public void setPrvkey(String prvkey) {
		this.prvkey = prvkey;
	}
	public String getPubkey() {
		return pubkey;
	}
	public void setPubkey(String pubkey) {
		this.pubkey = pubkey;
	}
	public String getTmpstamp() {
		return tmpstamp;
	}
	public void setTmpstamp(String tmpstamp) {
		this.tmpstamp = tmpstamp;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public double getKb() {
		return kb;
	}
	public void setKb(double kb) {
		this.kb = kb;
	}
	public Blob getFile() {
		return file;
	}
	public void setFile(Blob blob) {
		this.file = blob;
	}
        public String getEfile() {
		return efile;
	}
	public void setEfile(String efile) {
		this.efile = efile;
	}
		private String efile;

	private int fileid;
	
	public int getFileid() {
		return fileid;
	}
	public void setFileid(int fileid) {
		this.fileid = fileid;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
	private String ip;
	private String server;


private int port;
public int getPort() {
	return port;
}
public void setPort(int port) {
	this.port = port;
}



private  String image;
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
private String upfile;
public String getUpfile() {
	
	return upfile;
}
public void setUpfile(String upfile) {
	this.upfile = upfile;
}

private int block;
public int getBlock() {
	return block;
}
public void setBlock(int block) {
	this.block = block;
}

private String data;
public String getData() {
	return data;
}
public void setData(String data) {
	this.data = data;
}
private String filename;
public String getFilename() {
	return filename;
}
public void setFilename(String filename) {
	this.filename = filename;
}
private String username;
public String getServer() {
	return server;
}
public void setServer(String server) {
	this.server = server;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
private int key;
public int getKey() {
	return key;
}
public void setKey(int key) {
	this.key = key;
}
private String data1;
private String data2;
private String data3;
private String tpadata;
public String getData1() {
	return data1;
}
public void setData1(String data1) {
	this.data1 = data1;
}
public String getData2() {
	return data2;
}
public void setData2(String data2) {
	this.data2 = data2;
}
public String getData3() {
	return data3;
}
public void setData3(String data3) {
	this.data3 = data3;
}
public String getTpadata() {
	return tpadata;
}
public void setTpadata(String tpadata) {
	this.tpadata = tpadata;
}
private int transferblock;
private int pendingblock;
private String baddata;
private String nextserver;
public int getTransferblock() {
	return transferblock;
}
public void setTransferblock(int transferblock) {
	this.transferblock = transferblock;
}
public int getPendingblock() {
	return pendingblock;
}
public void setPendingblock(int pendingblock) {
	this.pendingblock = pendingblock;
}
public String getBaddata() {
	return baddata;
}
public void setBaddata(String baddata) {
	this.baddata = baddata;
}
public String getNextserver() {
	return nextserver;
}
public void setNextserver(String nextserver) {
	this.nextserver = nextserver;
}
private String requestedserver;
private String helpingserver;
public String getRequestedserver() {
	return requestedserver;
}
public void setRequestedserver(String requestedserver) {
	this.requestedserver = requestedserver;
}
public String getHelpingserver() {
	return helpingserver;
}
public void setHelpingserver(String helpingserver) {
	this.helpingserver = helpingserver;
}
private String filepath;
public String getFilepath() {
	return filepath;
}
public void setFilepath(String filepath) {
	this.filepath = filepath;
}
private String finaldata;
public String getFinaldata() {
	return finaldata;
}
public void setFinaldata(String finaldata) {
	this.finaldata = finaldata;
} 
private String d;
public String getD() {
	return d;
}
public void setD(String d) {
	this.d = d;
}


private String role;
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
public String getServername() {
	return servername;
}
public void setServername(String servername) {
	this.servername = servername;
}


private String servername;

public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}


private String  type;


private String mkey;
public String getMkey() {
	return mkey;
}
public void setMkey(String mkey) {
	this.mkey = mkey;
}

}
