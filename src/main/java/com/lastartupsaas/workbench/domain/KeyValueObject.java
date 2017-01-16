package com.lastartupsaas.workbench.domain;

/**
 * 用于存放key-value的简单对象
 * 
 * @author lifeilong
 * @date 2017-01-12
 */
public class KeyValueObject extends BaseObject {

	private Object key;
	private Object value;

	public KeyValueObject(Object key, Object value) {
		super();
		this.key = key;
		this.value = value;
	}

	public Object getKey() {
		return key;
	}

	public void setKey(Object key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
