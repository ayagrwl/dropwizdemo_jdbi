package com.flipkart.services;

import com.flipkart.bean.Auth;
import com.flipkart.dao.AuthDAO;

import javax.validation.constraints.NotNull;

public class PersonService {

//    @NotNull
    private final AuthDAO authDAO;

    public PersonService(AuthDAO authDAO){
        this.authDAO = authDAO;
    }

    public String getNameById(String id){
        return authDAO.findNameById(id);
    }

    public String insertPerson(String id, String name, String age){
        try {
            Auth auth = new Auth(id, name, age);

            return authDAO.insertPerson(auth);
        } catch (Exception e){
            return e.getMessage();
        }

    }

    public Auth getPersonById(String id){
        return authDAO.findById(id);
    }
}
