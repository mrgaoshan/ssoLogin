package com.wormwood.controller;

import com.wormwood.client.DingDingClient;
import com.wormwood.response.Response;
import com.wormwood.result.ResultEnum;
import com.wormwood.vo.user.UserListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kasimodo on 2017-07-10.
 */
@Controller
@RequestMapping("/user")
public class UserController extends DDBaseController {

    @Autowired
    private DingDingClient dingDingClient;

    @RequestMapping(value = "/findUserList", method = RequestMethod.GET)
    @ResponseBody
    public Response<UserListVO> findUserList(String token) {
        UserListVO userListVo = dingDingClient.findUserList(getToken().getAccess_token(), "1");
        if (userListVo.getErrcode() != 0) {
            return new Response<>(userListVo, ResultEnum.FAIL);
        }
        return new Response<>(userListVo, ResultEnum.SUCCESS);
    }

    @RequestMapping("/toStaffList")
    public String toStaffList() {
        return "user/staffList";
    }
}
