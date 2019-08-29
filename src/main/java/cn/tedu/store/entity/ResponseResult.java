package cn.tedu.store.entity;

public class ResponseResult<T> {
	private Integer state;
	private String message;
	//T: template placeholder
	private T data;
}
