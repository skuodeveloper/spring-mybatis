package com.nhga.spring.mybatis.mapper;

import com.nhga.spring.mybatis.bean.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    List<User> getUserList();

    List<Map<String, Object>> getMapList();

    @Select("SELECT * FROM USER WHERE ID = #{id}")
    @ResultMap("com.nhga.spring.mybatis.mapper.UserMapper.userResultMap")
    User getUserById(@Param("id") int id);

    void addUser(User user);
}
