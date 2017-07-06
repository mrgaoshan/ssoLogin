package com.wormwood.DTO;

import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Donnie on 2017/4/24.
 */
@NameStyle(value = Style.camelhumpAndUppercase)
public class Project implements Serializable {
    private static final long serialVersionUID = -9150193155401406256L;
    private String projectId;
    private String projectName;
    private String projectLink;
    private String description;
    private String projectType;
    private String crtBy;
    private Date crtOn;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectLink() {
        return projectLink;
    }

    public void setProjectLink(String projectLink) {
        this.projectLink = projectLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getCrtBy() {
        return crtBy;
    }

    public void setCrtBy(String crtBy) {
        this.crtBy = crtBy;
    }

    public Date getCrtOn() {
        return crtOn;
    }

    public void setCrtOn(Date crtOn) {
        this.crtOn = crtOn;
    }
}
