package com.wormwood.config;

import com.wormwood.DTO.User;
import com.wormwood.helper.AddHeaderRequestWrapper;
import com.wormwood.helper.AddParamHttpRequestWrapper;
import com.wormwood.helper.Constant;
import com.wormwood.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

/**
 * Created by Donnie on 2017/4/26.
 */
public class AddHeaderFilter extends HttpServlet implements Filter {
    private final Logger log = LoggerFactory.getLogger(AddHeaderFilter.class);
    private UserService userService;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        userService = ctx.getBean(UserService.class);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = loadUserByUsername(userDetails.getUsername());

        String projName = request.getParameter("project");
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.sendRedirect(getProjectUrl(projName));


        /*if (Constant.MPAConstant.ROLETYPE_MPA.equals(user.getRoleType())) {

            AddHeaderRequestWrapper requestWrapper = new AddHeaderRequestWrapper((HttpServletRequest) request);
            log.debug("------mpa user login verify success");
            requestWrapper.addHeader("sm_user", user.getUsername());
            requestWrapper.addHeader("member", user.getRole());
            requestWrapper.addHeader("mpa_email", "liuzhongyin@wormwood.com.sg;zhaofang@wormwood.com.sg");

            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.sendRedirect(getProjectUrl(projName));
//            chain.doFilter(requestWrapper, response);
        }
        if (Constant.MPAConstant.ROLETYPE_MAR.equals(user.getRoleType())) {
            AddParamHttpRequestWrapper requestWrapper = new AddParamHttpRequestWrapper((HttpServletRequest) request);
            requestWrapper.addParameter("sessid", String.valueOf(user.getId()));
//            request = requestWrapper;
            //todo
//            request.getRequestDispatcher("/index").forward(request, response);
//            chain.doFilter(requestWrapper, response);
        }*/
    }

    private String getProjectUrl(String projName) {
        if ("pf".equals(projName)) return "http://139.196.39.16:7002/pf/";
        if ("gf".equals(projName)) return "http://139.196.39.16:7002/gf/";
        if ("hotwork".equals(projName)) return "http://139.196.39.16:7003/hotwork/outEntry";
        return "error";
    }

    private User loadUserByUsername(String username) {
        return userService.findByName(username);
    }

    @Override
    public void destroy() {

    }
}
