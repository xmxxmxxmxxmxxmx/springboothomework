package com.connext.springboot.mapper;


import com.connext.springboot.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
	//??????
	public void addUser(User user);
	//???????????????????ú???
	public Integer queryIfexist(User user);
	//???????
	public User ifcanlogin(User user);
}
