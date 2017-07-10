package com.wormwood.client;

import com.wormwood.vo.DDToken;
import com.wormwood.vo.user.UserListVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by kasimodo on 2017-07-10.
 */
@FeignClient(name = "dingdingAPI", url = "https://oapi.dingtalk.com")
public interface DingDingClient {

    @RequestMapping(method = RequestMethod.GET, value = "gettoken")
    DDToken getToken(@RequestParam(value = "corpid") String corpid, @RequestParam(value = "corpsecret") String corpsecret);

    @RequestMapping(method = RequestMethod.GET, value = "user/list")
    UserListVO findUserList(@RequestParam(value = "access_token") String access_token, @RequestParam(value = "department_id") String department_id);
}
