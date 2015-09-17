package com.mingzhi.samattendance.action.framework;

public class TransIntoAccoutAuditModel {
	private String num;
	private String password;
	private String saasFlag;
	private String imei;
	private String versions;
	private String userId;
	private String filePath;


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getVersions() {
		return versions;
	}

	public void setVersions(String versions) {
		this.versions = versions;
	}

	public String getSaasFlag() {
		return saasFlag;
	}

	public void setSaasFlag(String saasFlag) {
		this.saasFlag = saasFlag;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
