package com.wormwood;

import com.wormwood.util.TokenUtil;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kasimodo on 2017-07-04.
 */
public class TokenTest {


    public static void main(String aa[]) {
        Map<String,Object> map = new HashMap<>();
        map.put(TokenUtil.CLAIM_KEY_SYSTEM,"PF");
        map.put(TokenUtil.CLAIM_KEY_CREATED, new Date());
        System.out.println(TokenUtil.generateToken(map));

        map.put(TokenUtil.CLAIM_KEY_SYSTEM,"HW");
        map.put(TokenUtil.CLAIM_KEY_CREATED, new Date().getTime());
        System.out.println(TokenUtil.generateToken(map));

        map.put(TokenUtil.CLAIM_KEY_SYSTEM,"GF");
        map.put(TokenUtil.CLAIM_KEY_CREATED, new Date());
        System.out.println(TokenUtil.generateToken(map));

    }
}
