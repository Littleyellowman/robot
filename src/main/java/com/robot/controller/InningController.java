package com.robot.controller;

import com.robot.service.InningService;
import com.robot.util.Url;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@RequestMapping("")
@Controller
public class InningController {

    private static Logger logger = Logger.getLogger(InningController.class);

    @Autowired
    InningService inningService;

    @RequestMapping("createInning")
    @ResponseBody
    public void createInning(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("UTF-8");
        String queryArg = request.getQueryString();
        Map queryParam = null;
        try {
            queryParam = Url.paramToMap(queryArg);//参数转转码
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        logger.info("222222222222    " + queryParam.get("cfrobotqqnum"));

        inningService.createInning(queryParam.get("cfrobotqqnum"), queryParam.get("guessnum"), queryParam.get("toplimit"));
        resp.getWriter().write("您好中国hello");
    }
}
