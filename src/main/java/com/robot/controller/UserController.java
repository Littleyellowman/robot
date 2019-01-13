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

/**
 *
 * 类功能描述：用户管理
 *
 * <p>
 * 版权所有：小黄人
 * <p>
 * 未经小黄人许可，不得以任何方式复制或使用本程序任何部分
 * <p>
 *
 * @author <a href="mailto:287627997@qq.com">287627997</a>
 * @version WEBMAIL2.0
 * @since 2019-1-6
 *
 */
@RequestMapping("")
@Controller
public class UserController {

    private static Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @RequestMapping("getUser")
    @ResponseBody
    public String getUser(HttpServletRequest request, HttpServletResponse resp){
        resp.setCharacterEncoding("UTF-8");
        String queryArg = request.getQueryString();
        Map queryParam = null;
        try {
            queryParam = Url.paramToMap(queryArg);//参数转转码
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        logger.info("op<getUser> queryParam<" + queryParam +">");
        //以上是测试前端传过来的参数，以下是测试后端查询数据及返回
        User userInfo = userService.selectByPrimaryKey(287627997);
        String money = String.valueOf(userInfo.getMoney());
        return money;
    }

    @RequestMapping("test")
    @ResponseBody
    public String test(HttpServletRequest request, HttpServletResponse resp){
        resp.setCharacterEncoding("UTF-8");
        String queryArg = request.getQueryString();
        Map queryParam = null;
        try {
            queryParam = Url.paramToMap(queryArg);//参数转转码
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        logger.info("op<getUser> queryParam<" + queryParam +">");
        //以上是测试前端传过来的参数，以下是测试后端查询数据及返回

        return "bbbbb";
    }

}
