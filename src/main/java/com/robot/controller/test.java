package com.robot.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class test {

    public static void main(String[] args) {
        //getURLInfo();
        decode();
    }

    public static void decode(){
        String cc = java.net.URLDecoder.decode("%E7%9A%84");
        System.out.println(cc);
    }

    public static void getURLInfo()  {
        StringBuffer document = new StringBuffer();
        try{
            URL url = new URL("http://kaijiang.500.com/bjk3.shtml");//远程url
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null)
                document.append(line + " ");
            reader.close();
        }catch(MalformedURLException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        String xml = document.toString();//返回值
        System.out.println(xml);
    }
}
