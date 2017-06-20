package com.wormwood.DTO;

/**
 * Created by Donnie on 2017/4/24.
 */
public class Project {
    private String id;
    private String name;
    private String link;
    private String desc;

    public Project() {
        super();
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
