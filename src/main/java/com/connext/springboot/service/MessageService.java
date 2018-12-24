package com.connext.springboot.service;


import com.connext.springboot.model.Message;
import com.connext.springboot.util.Page;

public interface MessageService {
    //���ݵ�¼���ֻ��Ž��в�ѯ������ص���Ϣ
	public Page queryMessageByphone(Integer currPage, String phone);
	
	//ɾ��ĳһ����Ϣ 
	public void deleteone(Integer messageid);
	
	//�༭ĳһ����Ϣ
	public Message editone(Integer messageid);
	
	//�޸�ĳһ����¼
	public void updatemessage(Message message);
	
	//������Ϣ
	public void addmessage(Message message);
}
