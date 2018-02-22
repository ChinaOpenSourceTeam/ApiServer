package com.chinaopensource.apiserver.common.util.jwt;

import com.chinaopensource.apiserver.ApiServerApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * create by lzl ON 2018/02/22
 */
public class JwtTest extends ApiServerApplicationTests {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Test
    public void testJwtGenerate(){
        String userName = "lzl";
        String token = jwtTokenUtil.generateToken(userName);
        System.out.println(token);
        String userNameNew = jwtTokenUtil.getUsernameFromToken(token);
        System.out.println(userName.equals(userNameNew));
    }
}
