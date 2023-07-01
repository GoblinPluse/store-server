package com.ki.utils;

import com.ki.entity.UserInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SessionUtil {
    private static Map<String , UserInfo> userInfoMap = new HashMap<>();

    /**
     * 缓存用户信息
     * @param token
     * @param userInfo
     */
    public static void addUserInfo(String token , UserInfo userInfo){
        userInfoMap.put(token , userInfo);
    }

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    public static UserInfo getUserInfo(String token){
        return userInfoMap.get(token);
    }

    /**
     * 生成token
     * @return
     */
    public static String createToken(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
