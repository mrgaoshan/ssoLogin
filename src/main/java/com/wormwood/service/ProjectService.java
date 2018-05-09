package com.wormwood.service;

import com.wormwood.DTO.Project;
import com.wormwood.dao.ProjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by kasimodo on 2017-07-06.
 */
public class ProjectService {

    @Autowired
    private ProjectDao projectDao;

    public List<Project> getAllProject() {
        return projectDao.selectAll();
    }

    public List<Project> findByProjectType(String projectType) {

        List<Project> projects = projectDao.findByProjectType(projectType);
        return projects;
    }

    public void insertProject(Project project) {
        projectDao.insert(project);
    }

    public void updateProject(Project project) {
        projectDao.updateByPrimaryKeySelective(project);
    }

    public void deleteProject(Integer projectId) {
        projectDao.deleteByPrimaryKey(projectId);
    }

    public Project findProjectById(Integer projectId) {
        return projectDao.selectByPrimaryKey(projectId);
    }

    public Integer findTotalProject() {
        return projectDao.selectCount(new Project());
    }
}
