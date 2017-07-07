package com.wormwood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by kasimodo on 2017-07-07.
 */
@Controller
@RequestMapping("/loadPage")
public class CommonPageController {

    @RequestMapping(value = "/footer", method = RequestMethod.GET)
    public String footer() {
        return "layout/footer";
    }

    @RequestMapping(value = "/leftMenu", method = RequestMethod.GET)
    public String leftMenu() {
        return "layout/leftMenu";
    }

    @RequestMapping(value = "/topNav", method = RequestMethod.GET)
    public String topNav() {
        return "layout/top";
    }

}
