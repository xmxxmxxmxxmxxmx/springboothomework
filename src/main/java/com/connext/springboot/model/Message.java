package com.connext.springboot.model;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

public class Message {
	private Integer id;
	private String phone;
	private String messagetitle;
	private String messagedetail;
	private String date;
	private String photo;
	
	
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMessagetitle() {
		return messagetitle;
	}
	public void setMessagetitle(String messagetitle) {
		this.messagetitle = messagetitle;
	}
	public String getMessagedetail() {
		return messagedetail;
	}
	public void setMessagedetail(String messagedetail) {
		this.messagedetail = messagedetail;
	}
	@Override
	public String toString() {
		return "Message [id=" + id + ", phone=" + phone + ", messagetitle=" + messagetitle + ", messagedetail="
				+ messagedetail + ", date=" + date + "]";
	}
	
}
