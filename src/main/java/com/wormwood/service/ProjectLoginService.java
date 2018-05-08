package com.wormwood.service;

import com.wormwood.DTO.ProjectLogin;
import com.wormwood.dao.ProjectLoginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by kasimodo on 2017-07-06.
 */
@Service
public class ProjectLoginService {

    @Autowired
    private ProjectLoginDao projectLoginDao;

    public List<ProjectLogin> getAllProjectLogin() {
        return projectLoginDao.findAllProjectLogin();
    }

    public List<ProjectLogin> findByProjectLoginId(Integer projectId) {
        Example example = new Example(ProjectLogin.class);
        example.createCriteria().andCondition("project_id=", projectId);
        List<ProjectLogin> projects = projectLoginDao.selectByExample(example);
        return projects;
    }

    public void insertProjectLogin(ProjectLogin project) {
        projectLoginDao.insert(project);
    }

    public void updateProjectLogin(ProjectLogin project) {
        projectLoginDao.updateByPrimaryKeySelective(project);
    }

    public void deleteProjectLogin(Integer projectId) {
        projectLoginDao.deleteByPrimaryKey(projectId);
    }

    public ProjectLogin findProjectLoginById(Integer projectId) {
        return projectLoginDao.selectByPrimaryKey(projectId);
    }

    public Integer findTotalProjectLogin() {
        return projectLoginDao.selectCount(new ProjectLogin());
    }
}
