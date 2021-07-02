package com.flipkart.dao;

import com.flipkart.bean.Auth;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

public interface AuthDAO {

    @SqlQuery("select name from auth where id= :id")
    String findNameById(@Bind("id") String id);

    @SqlQuery("insert into auth (id, name, age) values (:uid, :name, :age)")
    String insertPerson(@BindBean Auth auth);

    @SqlQuery("select * from auth where id= :id")
    @RegisterConstructorMapper(Auth.class)
//    @RegisterBeanMapper(Auth.class)
    Auth findById(@Bind("id") String id);
}
