package com.wormwood.controller;

import com.wormwood.DTO.Project;
import com.wormwood.response.Response;
import com.wormwood.result.ResultEnum;
import com.wormwood.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * Created by kasimodo on 2017-07-06.
 */

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping("/toAddProject")
    public String toAddProject() {
        return "projectMgmt/addProject";
    }


    @RequestMapping(value = "/addProject", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Response> addProject(@RequestBody @Valid Project project) {
        project.setCrtBy("admin");
        project.setCrtOn(new Date());
        projectService.insertProject(project);
        return new Response(ResultEnum.SUCCESS).build();
    }

}
