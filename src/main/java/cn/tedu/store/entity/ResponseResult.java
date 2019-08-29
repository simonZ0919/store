package cn.tedu.store.entity;

import java.io.Serializable;
/**
 * 
 * @author simon
 * @param <T> return data type
 */
public class ResponseResult<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8794912675355335729L;
	private Integer state;
	private String message;
	//T: template placeholder
	private T data;
	
	public ResponseResult() {
		super();
	}
	
	public ResponseResult(Integer state) {
		super();
		setState(state);
	}

	public ResponseResult(Integer state, String message) {			
		this(state);
		setMessage(message);
	}

	public ResponseResult(Integer state, T data) {
		this(state);
		setData(data);
	}
	
	public ResponseResult(Integer state, Exception e) {
		this(state,e.getMessage());
	}

	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
}
