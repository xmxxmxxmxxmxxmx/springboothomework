package com.connext.springboot.service.serviceimp;

import com.connext.springboot.mapper.MessageMapper;
import com.connext.springboot.model.Message;
import com.connext.springboot.service.MessageService;
import com.connext.springboot.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;


@Service
public class MessageServiceImp implements MessageService {

	@Autowired
	private MessageMapper messagemapper;
	
	public Page queryMessageByphone(Integer currPage, String phone) {

		Page page=new Page();
		if(currPage == null) currPage=1;
		page.setCurrPage(currPage);
		page.setTotalCount(this.messagemapper.MessageNum(phone));
		page.init();
		PageHelper.startPage(currPage, page.PAGE_SIZE);
		page.setData(this.messagemapper.queryMessageByphone(phone));
		return page;
	}

	public void deleteone(Integer messageid) {
        this.messagemapper.deleteone(messageid);
	}

	public Message editone(Integer messageid) {
		return this.messagemapper.editone(messageid);
	}

	public void updatemessage(Message message) {
		this.messagemapper.updatemessage(message);
	}

	public void addmessage(Message message) {
		this.messagemapper.addmessage(message);
		
	}

}
