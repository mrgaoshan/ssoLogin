package com.wormwood.config;

import com.google.common.collect.Maps;
import com.wormwood.service.TokenService;
import com.wormwood.util.TokenUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;


public class AddHeaderFilter implements Filter {
    private final Logger log = LoggerFactory.getLogger(AddHeaderFilter.class);

    private TokenService tokenService;

    public AddHeaderFilter(TokenService tokenService){
        this.tokenService=tokenService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String projName = request.getParameter("project");
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.sendRedirect(getProjectUrl(projName));


    }

    private String getProjectUrl(String projName) {
        Map<String, Object> map = Maps.newHashMap();
        map.put(TokenUtil.CLAIM_KEY_SYSTEM, projName);
        map.put(TokenUtil.CLAIM_KEY_CREATED, new Date());
        String token = TokenUtil.generateToken(map);
        String ipAddress = "";
        if ("pf".equalsIgnoreCase(projName)) ipAddress = "http://139.196.39.16:7002/pf/?ww_token=" + token;
        if ("gf".equalsIgnoreCase(projName)) ipAddress = "http://139.196.39.16:7002/gf/?ww_token=" + token;
        if ("hotwork".equalsIgnoreCase(projName)) ipAddress = "http://139.196.39.16:7003/hotwork/outEntry?ww_token=" + token;
        if(StringUtils.isNotBlank(ipAddress)){
            tokenService.updateOrInsert(token,projName);
            return ipAddress;
        }else{
            return "error";
        }
    }


    @Override
    public void destroy() {

    }
}
