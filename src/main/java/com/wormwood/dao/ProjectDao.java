package com.wormwood.dao;

import com.wormwood.DTO.Project;
import com.wormwood.DTO.ProjectLogin;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by kasimodo on 2017-07-06.
 */
@Mapper
public interface ProjectDao extends tk.mybatis.mapper.common.Mapper<Project> {

    @Select("SELECT project_id,project_name as projectName,project_link as projectLink,description as description,project_type as projectType FROM project where project_type=#{projectType}")
    @Results(
            {@Result(id = true, column = "project_id", property = "projectId"),
                    @Result(property = "loginList", column = "project_id", javaType = List.class, many = @Many(select = "getAllLoginList"))
            })
    List<Project> findByProjectType(String projectType);


    @Select("SELECT project_id as projectId, login_name as loginName,login_pass as loginPass,login_role as loginRole,`desc` as `desc` from project_login where project_id=#{projectId}")
    List<ProjectLogin> getAllLoginList(Integer projectId);
}
