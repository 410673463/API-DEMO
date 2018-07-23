package com.ws.test;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="guide")
public class Guide implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3948791482730031338L;
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer guId;
	private String Name;
	private Date createTime;
	public Integer getGuId() {
		return guId;
	}
	public void setGuId(Integer guId) {
		this.guId = guId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
