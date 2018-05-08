package com.wormwood.dao;

import com.google.common.base.Joiner;
import com.wormwood.DTO.ProjectLogin;
import com.wormwood.DTO.TokenDTO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by kasimodo on 2018-05-08.
 */
@Mapper
public interface ProjectLoginDao extends tk.mybatis.mapper.common.Mapper<ProjectLogin> {

    @SelectProvider(type = ProjectLoginDao.ProjectLoginDaoBuild.class, method = "buildFindProjectByIds")
    List<ProjectLogin> findAllProjectLogin();


    class ProjectLoginDaoBuild {
        public String buildFindProjectByIds() {

            String sql = new SQL() {{
                SELECT("pl.id as id," +
                        "pl.user_id as userId," +
                        "pl.project_id as projectId," +
                        "pl.login_name as loginName," +
                        "pl.login_pass as loginPass," +
                        "pl.login_role as loginRole," +
                        "pl.desc as `desc`," +
                        "p.project_name as projectName"

                );
                FROM("project_login pl");
                JOIN("project p on pl.project_id=p.project_id");
            }}.toString();
            return sql;
        }

    }
}
