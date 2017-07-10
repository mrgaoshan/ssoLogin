package com.wormwood.controller;

import com.wormwood.client.DingDingClient;
import com.wormwood.response.Response;
import com.wormwood.result.ResultEnum;
import com.wormwood.vo.user.UserListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kasimodo on 2017-07-10.
 */
@RestController
@RequestMapping("/user")
public class UserController extends DDBaseController {

    @Autowired
    private DingDingClient dingDingClient;

    @RequestMapping(value = "/findUserList", method = RequestMethod.GET)
    public UserListVO findUserList(String token) {
        UserListVO userList = dingDingClient.findUserList(getToken().getAccess_token(), "1");
        return userList;
    }
}
