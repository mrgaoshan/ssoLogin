package com.wormwood.controller;

import com.google.common.base.Optional;
import com.wormwood.response.Response;
import com.wormwood.result.ResultEnum;
import com.wormwood.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * Created by kasimodo on 2017-07-05.
 */

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "/validate", method = RequestMethod.GET)
    public ResponseEntity<Response> validateToken(String token) {
        Integer i = tokenService.checkToken(token);
        if (null != i && i != 0) {
            return new Response(ResultEnum.SUCCESS).build();
        }
        return new Response(ResultEnum.FAIL).build();
    }

}
