package com.connext.springboot.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.connext.springboot.annotation.AopAnnotation;
import com.connext.springboot.model.Message;
import com.connext.springboot.service.MessageService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("messagecontroller")
public class MessageController {
    @Resource
    private MessageService messageservice;

    @Value("${image.store-path}")
    private String imagePath;

    @RequestMapping("tomessageadd")
    public String toMessageAdd() {
        return "message_add";
    }


    @RequestMapping("querymessage")
    public String querymessage(Model model, HttpSession session, Integer currPage) {
        String loginPhone = session.getAttribute("loginphone").toString();
        model.addAttribute("messagelist", this.messageservice.queryMessageByphone(currPage, loginPhone));
        return "message_list";
    }

    @RequestMapping("deleteone")
    @AopAnnotation(value = "用户删除消息")
    public String deleteone(Integer messageid) {
        this.messageservice.deleteone(messageid);
        return "redirect:querymessage";
    }

    @RequestMapping("see")
    public String see(Integer messageid, Model model) {
        model.addAttribute("messagedetail", this.messageservice.editone(messageid));
        return "message_detail";
    }

    @RequestMapping("indeleteone")
    public String indeleteone(Integer messageid) {
        this.messageservice.deleteone(messageid);
        return "redirect:querymessage";
    }

    @RequestMapping("preupdate")
    public String preupdate(Integer messageid, Model model) {
        model.addAttribute("messagedetail", this.messageservice.editone(messageid));
        return "message_update";
    }

    @RequestMapping("update")
    @ResponseBody
    @AopAnnotation(value = "用户修改消息")
    public Integer updatemessage(Message message) {
        int flag = 0;
        this.messageservice.updatemessage(message);
        return flag;
    }

    @RequestMapping(value = "/addmessage")
    public String addmessage(@RequestParam("myFile") MultipartFile file, Message message, HttpSession session, HttpServletRequest request) throws Exception{
        String filename = file.getOriginalFilename();
        if (StringUtils.isNotEmpty(filename)) {
            String newfileName = UUID.randomUUID() + filename.substring(filename.lastIndexOf("."));
            String path = imagePath + File.separator + newfileName;
            try {
                FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path));
                message.setPhoto(newfileName);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        this.messageservice.addmessage(message);
        return "redirect:querymessage";
    }
}
