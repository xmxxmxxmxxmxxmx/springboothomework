package com.connext.springboot.service;

import com.connext.springboot.model.User;

import javax.servlet.http.HttpSession;



public interface UserService {
	public Integer addUser(String code, User user, HttpSession session);
	public Integer queryIfexist(User user);
	public Integer login(User user);
	public void insertuser(User user);
}
