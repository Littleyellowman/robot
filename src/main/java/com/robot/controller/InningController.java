package com.robot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping("")
@Controller
public class InningController {

    @RequestMapping("createInning")
    @ResponseBody
    public void createInning(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("UTF-8");


        resp.getWriter().write("您好中国hello");
    }
}
