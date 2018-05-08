package com.wormwood.controller;

import com.google.common.collect.Maps;
import com.wormwood.DTO.Project;
import com.wormwood.DTO.ProjectLogin;
import com.wormwood.response.Response;
import com.wormwood.result.ResultEnum;
import com.wormwood.service.ProjectLoginService;
import com.wormwood.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by kasimodo on 2017-07-06.
 */

@Controller
@RequestMapping("/projectPass")
public class ProjectPassController {

    @Autowired
    private ProjectLoginService projectLoginService;

    @Autowired
    private ProjectService projectService;

    @RequestMapping("/toAddProjectLogin")
    public ModelAndView toAddProjectLogin() {

        List<Project> allProject = projectService.getAllProject();
        Map<String, Object> model = Maps.newHashMap();
        model.put("data", allProject);
        return new ModelAndView("projectPass/addProjectLogin", model);
    }

    @RequestMapping("/toProjectLoginList")
    public String toProjectLoginList() {
        return "projectPass/projectLoginList";
    }


    @RequestMapping(value = "/addProjectLogin", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Response> addProject(@RequestBody @Valid ProjectLogin projectLogin) {
        projectLoginService.insertProjectLogin(projectLogin);
        return new Response(ResultEnum.SUCCESS).build();
    }

    @RequestMapping(value = "/getAllProjectLoginList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Response<List<ProjectLogin>>> getAllProjectList() {

        List<ProjectLogin> allProjectLogin = projectLoginService.getAllProjectLogin();
        return new Response(allProjectLogin, ResultEnum.SUCCESS).build();
    }


    @RequestMapping(value = "/removeProjectLogin/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Response> removeProject(@PathVariable("id") Integer id) {
        projectLoginService.deleteProjectLogin(id);
        return new Response(ResultEnum.SUCCESS).build();
    }


    @RequestMapping(value = "/getProjectLoginById/{id}", method = RequestMethod.GET)
    public ModelAndView getProjectById(@PathVariable("id") Integer id) {
        ProjectLogin projectLoginById = projectLoginService.findProjectLoginById(id);
        List<Project> allProject = projectService.getAllProject();
        Map<String, Object> model = Maps.newHashMap();
        model.put("data", projectLoginById);
        model.put("projectData", allProject);

        return new ModelAndView("projectPass/editProjectLogin", model);
    }


    @RequestMapping(value = "/updateProjectLogin", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Response> updateProject(@RequestBody @Valid ProjectLogin projectLogin) {
        projectLoginService.updateProjectLogin(projectLogin);
        return new Response(ResultEnum.SUCCESS).build();
    }


}
