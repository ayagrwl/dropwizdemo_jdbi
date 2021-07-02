package com.flipkart.bean;

import com.google.common.base.Objects;
import org.jdbi.v3.core.mapper.reflect.ColumnName;

import java.beans.ConstructorProperties;

public class Auth {

    String uid;
    String name;
    String age;

    public Auth(){}

    @ConstructorProperties({"id", "name", "age"})
    public Auth(@ColumnName("id") String uid, String name, String age) {
        this.uid = uid;
        this.name = name;
        this.age = age;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auth auth = (Auth) o;
        return uid == auth.uid && age == auth.age && Objects.equal(name, auth.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uid, name, age);
    }
}

