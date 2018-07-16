package com.resourceaccount.persistence;

import com.resourceaccount.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {
    @Insert("insert into user(name, login, email, password) values(#{user.name}, #{user.login}, #{user.email}, #{user.password})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    void create(@Param("user") User user);
}
