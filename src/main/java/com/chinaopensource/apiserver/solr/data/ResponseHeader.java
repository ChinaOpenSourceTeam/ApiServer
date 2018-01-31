package com.chinaopensource.apiserver.solr.data;

public class ResponseHeader {
	private int status;
    private int QTime;
    private Params params;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getQTime() {
		return QTime;
	}
	public void setQTime(int qTime) {
		QTime = qTime;
	}
	public Params getParams() {
		return params;
	}
	public void setParams(Params params) {
		this.params = params;
	}
    
    
}
