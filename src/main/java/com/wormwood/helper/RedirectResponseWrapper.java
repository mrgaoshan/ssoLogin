package com.wormwood.helper;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


/**
 * 
 * @author mengxuanliang 2017-04-05 to resolve PRD issue that redirect action auto changed to HTTP
 * to resolve issue for MPA prd web server auto switched https to http when redirect
 *
 */
public class RedirectResponseWrapper extends HttpServletResponseWrapper {
	
	private static Logger logger = LoggerFactory.getLogger(RedirectResponseWrapper.class);
	
	private HttpServletRequest request;
	private HttpServletResponse response;

	public RedirectResponseWrapper(HttpServletRequest inRequest, HttpServletResponse response) {
		super(response);
		this.request = inRequest;
		this.response = response;
	}

	@Override
	public void sendRedirect(final String pLocation) throws IOException {

		
		 String requestReferer   =   request.getHeader("Referer");
		 
		 logger.info("requestReferer is:"+requestReferer);
		 
		
		if (StringUtils.isBlank(pLocation)) {
			super.sendRedirect(pLocation);
			return;
		}
	
		logger.info("send redirect orignal url:"+pLocation);
		
		try {
			final URI uri = new URI(pLocation);
			if (uri.getScheme() != null) {
				super.sendRedirect(pLocation);
				return;
			}
		} catch (URISyntaxException ex) {
			super.sendRedirect(pLocation);
		}
		
		
		String finalurl = "";
        if (this.request.getServerName().equals("marinet.mpa.gov.sg") || this.request.getServerName().equals("119.75.209.77")) {
            if(!pLocation.startsWith(request.getContextPath()+ "/")){
            	finalurl = "https://" + this.request.getServerName() + ":443" + request.getContextPath() + pLocation;
            }else{
            	finalurl = "https://" + this.request.getServerName() + ":443" + pLocation;
            }
        }else {

            finalurl = request.getScheme() + "://" + this.request.getServerName();
            if (request.getServerPort() != 80 && request.getServerPort() != 443) {
                finalurl += ":" + request.getServerPort();
            }
            String contextPath = request.getContextPath();

            if (finalurl.indexOf(contextPath + "/") < -1) {
                finalurl += contextPath;
            }

            finalurl += pLocation;
        }
        
        
        if(!finalurl.contains("JSESSIONID")){
        	if(finalurl.contains("?")){
        		finalurl += "&JSESSIONID="+request.getSession().getId();
        	}else{
        		finalurl += "?JSESSIONID="+request.getSession().getId();
        	}
        }
        
		logger.info("send redirect final url::"+finalurl);
		super.sendRedirect(finalurl);
	}
	
}
