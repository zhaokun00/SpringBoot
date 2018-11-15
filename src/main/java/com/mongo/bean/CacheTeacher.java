package com.mongo.bean;

import java.io.Serializable;

public class CacheTeacher implements Serializable {

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CacheTeacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
