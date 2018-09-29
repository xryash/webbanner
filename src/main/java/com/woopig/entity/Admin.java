package com.woopig.entity;

import java.io.Serializable;

public class Admin implements Serializable {

    private String id;

    private String name;

    private String hash;

    public Admin() { }

    public Admin(String id, String name, String hash) {
        this.id = id;
        this.name = name;
        this.hash = hash;
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

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", hash='" + hash + '\'' +
                '}';
    }
}
