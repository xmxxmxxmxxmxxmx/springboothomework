package com.connext.springboot;

import com.connext.springboot.model.Message;
import com.connext.springboot.service.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageControllerTest {

    @Resource
    private MessageService messageService;

    @Test
    public void deleteone() {
        Message message=new Message();
        message.setId(39);
        this.messageService.deleteone(message.getId());
    }


    @Test
    public void updatemessage() {
        Message message=new Message();
        message.setId(39);
        message.setMessagetitle("今天我去爬山");
        message.setMessagedetail("爬到美人山，看见小美人，把她带回家，给她嗑瓜子");
        this.messageService.updatemessage(message);
    }
}