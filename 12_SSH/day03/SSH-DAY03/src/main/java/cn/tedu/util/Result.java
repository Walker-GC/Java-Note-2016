package cn.tedu.util;

import java.io.Serializable;

/*
 * 结果对象
 * {"status"：0,"msg":"成功","data":XXX}
 */
public class Result implements Serializable{
	private int status;//0 表示成功，其它数字表示失败
	private String msg;//表示消息
	private Object data;//表示返回数据
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
