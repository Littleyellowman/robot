package com.robot.util;

import cn.hutool.core.date.DateUtil;
import com.robot.pojo.Lottery;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LittleYellowMan {



    public static String getURLInfo(String data)  {
        try {
            String url = "http://kaijiang.500.com/static/info/kaijiang/xml/bjk3/"+data+".xml";
            //使用Jsoup连接目标页面,并执行请求,获取服务器响应内容
            String html = Jsoup.connect(url).execute().body();
            //打印页面内容
//            System.out.println(html);
            return html;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 读取第一行数据
     * 返回详细数据
     *
     */
    public static Map readLine(String string){
        String[] ss = string.split("\n");
//        System.out.println(ss[0]);
        int expectindex=ss[0].indexOf("expect=\"");
        int expectend=ss[0].indexOf("\"",expectindex+8);
        String expect=ss[0].substring(expectindex+8,expectend);
        System.out.println(expect);
        int opencodeindex=ss[0].indexOf("opencode=\"");
        int opencodeend=ss[0].indexOf("\"",opencodeindex+10);
        String opencode=ss[0].substring(opencodeindex+10,opencodeend);
        System.out.println(opencode);
        int opentimeindex=ss[0].indexOf("opentime=\"");
        int opentimeend=ss[0].indexOf("\"",opentimeindex+10);
        String opentime=ss[0].substring(opentimeindex+10,opentimeend);
        System.out.println(opentime);
        Map lottery=new HashMap();
        lottery.put("PeriodNum",Integer.valueOf(expect));
        lottery.put("Result",opencode);
        lottery.put("Time",opentime);
        return lottery;
    }

    public static String Intercept(String html,String start,String end,int x){
        int expectindex=html.indexOf(start);
        int expectend=html.indexOf(end,expectindex+x);
        String expect=html.substring(expectindex+x,expectend);
        expect=expect.replaceAll("\\s*", "");
        return expect;
    }

    public static Map caijuzi(){
        try {
            String html=TrustSSL.caijuzi();
//            System.out.println(html);
            String Raw_material=Intercept(html,"laytable-cell-1-qh\">","<td data-field=\"opentime\"",20);
            int start=Raw_material.indexOf("<",0);
            String qh=Raw_material.substring(0,start);
            Raw_material=Intercept(html,"cell-1-opentime\">","<td data-field=\"num\"",17);
            start=Raw_material.indexOf("<",0);
            String opentime=Raw_material.substring(0,start);
            Raw_material=Intercept(html,"cell-1-num\">","data-index",12);
            start=Raw_material.indexOf("</s></div>",0);
            String num=Raw_material.substring(0,start);
            String num1=Intercept(num,"\"b","i",2);
            String num2=Intercept(num,"><sclass=\"b","i",11);
            String nums=Intercept(num,"><sclass=\"b","\">",20);
            String num3=Intercept(nums,"\"b","i",2);
            num=num1+num2+num3;
            System.out.println(qh);
            System.out.println(opentime);
            System.out.println(num);
            Map caijuzi=new HashMap();
            caijuzi.put("qh",qh);
            caijuzi.put("opentime",qh);
            caijuzi.put("num",Integer.parseInt(num));
            return caijuzi;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args)  {
//        String LittleYellowMan=getURLInfo("20190120");
//        readLine(LittleYellowMan);
        caijuzi();
    }
}