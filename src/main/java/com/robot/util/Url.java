package com.robot.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * 类功能描述：Url工具
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
public class Url {
    public static Map<String, String> paramToMap(String paramStr) throws UnsupportedEncodingException {
        String[] params = paramStr.split("&");
        Map<String, String> resMap = new HashMap<String, String>();
        for (int i = 0; i < params.length; i++) {
            String[] param = params[i].split("=");
            if (param.length >= 2) {
                String key = param[0];
                String value;
                //如果是自定义参数，需要进行url解码
                if (key!="cfrobotqqauth" && key!="cfrobotgroupnum" && key!="checksign" && key!="cfrobotmsgid" && key!="myhwnd" && key!="cfrobotmsgtype" && key!="cfrobotselfnum" && key!="cfandroidflag" && key!="version" && key!="cfrobotqqnum"){
                    value = URLDecoder.decode(param[1],"UTF-8");
                } else {
                    value = param[1];
                }
                for (int j = 2; j < param.length; j++) {
                    value += "=" + param[j];
                }
                resMap.put(key, value);
            }
        }
        return resMap;
    }

}
