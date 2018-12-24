package com.connext.springboot.controller;

import com.connext.springboot.annotation.AopAnnotation;
import com.connext.springboot.model.User;
import com.connext.springboot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/usercontroller")
public class UserController {
    int count=0;

    @Resource
    private UserService userservice;
    @Resource
    private JedisPool jedisPool;

    @RequestMapping("/tologin")
    public String tologin() {
        return "login";
    }

    @RequestMapping("/toregister")
    public String toregister() {
        return "register";
    }

    @RequestMapping("ifexist")
    @ResponseBody
    public Integer ifexist(User user) {
        // 0:不可以 1:可以
        Integer flag = 0;
        if (this.userservice.queryIfexist(user) == 0) {
            flag = 1;
        }
        return flag;
    }

    //注册之前的验证
    @RequestMapping("adduser")
    @ResponseBody
    public Integer adduser(User user, String code, HttpSession session) {
        // 0:验证码失效 1：验证码出错 2：成功注册 其余的看前端界面吧

        return this.userservice.addUser(code, user, session);
    }

    @RequestMapping("adduser1")
    @ResponseBody
    public Map<String, String> adduser1(User user, String code, HttpSession session) {
		/*{"1"="aaa",}
		CheckDto code name
		List<CheckDto> list = null;
		CheckDto checkDto = new CheckDto();
		checkDto.setCode("1");
		checkDto.setName("aaa");
		list.add(checkDto);*/
        Map<String, String> map = new HashMap<String, String>();
        Integer flag = this.userservice.addUser(code, user, session);
        map.put(flag.toString(), "123");
        return map;
    }

    //验证通过才可以调用添加用户的方法
    @RequestMapping("insertuser")
    public void insertuser(User user) {
        this.userservice.insertuser(user);
    }

    @RequestMapping("sessionexist")
    @ResponseBody
    public void sessionexist(String providecode, HttpSession session) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
        } catch (RuntimeException e) {
            if(jedis != null ) {
                jedisPool.returnBrokenResource(jedis);
            }
        } finally {
            // 正确释放资源
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
        }
        }
        jedis.set("sessionprovidecode", providecode);
        jedis.expire("sessionprovidecode", 60);
    }


    @RequestMapping("login")
    @ResponseBody
    public Integer login(User user,HttpSession session) {
        int flag=this.userservice.login(user);
        if(flag==1) {
            session.setAttribute("loginphone", user.getPhone());
        }
        if(flag==2||flag==3) {
            count++;
            if(count % 3==0) {
                flag=4;
            }
        }
        return flag;
    }

    //用户注销
    @RequestMapping("cancel")
    public String cancel(HttpSession session) {
        session.invalidate();
        return "login";
    }
}
