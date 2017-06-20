package com.wormwood.config;

import com.wormwood.DTO.User;
import com.wormwood.helper.AddHeaderRequestWrapper;
import com.wormwood.helper.AddParamHttpRequestWrapper;
import com.wormwood.helper.Constant;
import com.wormwood.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Donnie on 2017/4/25.
 */

public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final Logger log = LoggerFactory.getLogger(LoginSuccessHandler.class);
    @Autowired
    private UserService userService;



    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        org.springframework.security.core.userdetails.User userdetails =
                (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

        User user = loadUserByUsername(userdetails.getUsername());

        if (Constant.MPAConstant.ROLETYPE_MPA.equals(user.getRoleType())) {

            AddHeaderRequestWrapper requestWrapper = new AddHeaderRequestWrapper(request);
            log.debug("------mpa user login verify success");
            requestWrapper.addHeader("sm_user", user.getUsername());
            requestWrapper.addHeader("member", user.getRole());
            requestWrapper.addHeader("mpa_email", "liuzhongyin@wormwood.com.sg;zhaofang@wormwood.com.sg");

        }
        if (Constant.MPAConstant.ROLETYPE_MAR.equals(user.getRoleType())) {
            AddParamHttpRequestWrapper requestWrapper = new AddParamHttpRequestWrapper(request);
            requestWrapper.addParameter("sessid", String.valueOf(user.getId()));
            request = requestWrapper;
            request.getRequestDispatcher("/index").forward(request, response);
            log.debug("------marinet user login verify success");
        }
    }



    private User loadUserByUsername(String username) {
        return userService.findByName(username);
    }
}
