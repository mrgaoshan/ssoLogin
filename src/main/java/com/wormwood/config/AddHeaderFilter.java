package com.wormwood.config;

import com.google.common.collect.Maps;
import com.wormwood.DTO.Project;
import com.wormwood.service.ProjectService;
import com.wormwood.service.TokenService;
import com.wormwood.util.TokenUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;


public class AddHeaderFilter implements Filter {
    private final Logger log = LoggerFactory.getLogger(AddHeaderFilter.class);

    private TokenService tokenService;

    private ProjectService projectService;


    public AddHeaderFilter(TokenService tokenService, ProjectService projectService) {
        this.tokenService = tokenService;
        this.projectService = projectService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String projectId = request.getParameter("projectId");
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.sendRedirect(getProjectUrl(projectId));
    }

    private String getProjectUrl(String projectId) {
        Map<String, Object> map = Maps.newHashMap();
        map.put(TokenUtil.CLAIM_KEY_SYSTEM, projectId);
        map.put(TokenUtil.CLAIM_KEY_CREATED, new Date());
        String token = TokenUtil.generateToken(map);
        String ipAddress = "";
        Project project = projectService.findProjectById(Integer.valueOf(projectId));
        if (null != project) {
            ipAddress = project.getProjectLink() + "?ww_token=" + token;
        } else {
            return "error";
        }
        tokenService.updateOrInsert(token, project.getProjectName());
        return ipAddress;

    }


    @Override
    public void destroy() {

    }

}
