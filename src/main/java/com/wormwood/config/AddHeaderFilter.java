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

    private Map urlMaps;

    private TokenService tokenService;

    public AddHeaderFilter(TokenService tokenService, Map map) {
        this.tokenService = tokenService;
        this.urlMaps = map;
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

    private String getProjectUrl(String projectName) {
        Map<String, Object> map = Maps.newHashMap();
        map.put(TokenUtil.CLAIM_KEY_SYSTEM, projectName);
        map.put(TokenUtil.CLAIM_KEY_CREATED, new Date());
        String token = TokenUtil.generateToken(map);
        String ipAddress = "";
        Map urlMaps = this.getUrlMaps();
        if (urlMaps.containsKey(projectName)) {
            ipAddress = (String) urlMaps.get(projectName) + "?ww_token=" + token;
        } else {
            return "error";
        }
        tokenService.updateOrInsert(token, projectName);
        return ipAddress;

    }


    @Override
    public void destroy() {

    }

    public Map getUrlMaps() {
        return urlMaps;
    }

    public void setUrlMaps(Map urlMaps) {
        this.urlMaps = urlMaps;
    }
}
