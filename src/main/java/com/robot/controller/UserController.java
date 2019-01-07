package com.robot.controller;

import com.robot.pojo.User;
import com.robot.service.UserService;
import com.robot.util.Url;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@RequestMapping("")
@Controller
public class UserController {

    private static Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @RequestMapping("getUser")
    @ResponseBody
    public String getUser(HttpServletRequest request, HttpServletResponse resp){
        String queryArg = request.getQueryString();
        Map queryParam = null;
        try {
            queryParam = Url.paramToMap(queryArg);//参数转转码
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        logger.info("222222222222    " + queryParam);

        User userInfo = userService.selectByPrimaryKey(287627997);
        String money = String.valueOf(userInfo.getMoney());
        return money;
    }
}
