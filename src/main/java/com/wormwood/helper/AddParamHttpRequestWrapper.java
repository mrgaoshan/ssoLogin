package com.wormwood.helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;

/**
 * @author Donnie
 */
public class AddParamHttpRequestWrapper extends HttpServletRequestWrapper {

    private HashMap params = new HashMap();

    public AddParamHttpRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    public String getParameter(String name) {
        // if we added one, return that one
        if ( params.get( name ) != null ) {
            return (String) params.get( name );
        }
        // otherwise return what's in the original request
        HttpServletRequest req = (HttpServletRequest) super.getRequest();
        return req.getParameter( name );
    }

    public void addParameter(String name, String value ) {
        params.put( name, value );
    }

}