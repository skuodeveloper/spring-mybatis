package com.nhga.spring.mybatis.control;

import com.nhga.spring.mybatis.bean.User;
import com.nhga.spring.mybatis.mapper.UserMapper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "int")
    @RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable Integer id) {
        User user = null;

        try {
            // 读取配置文件
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);

            // 通过SqlSessionFactoryBuilder构建一个SqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            //打开SqlSession,true表示自动提交事务
            SqlSession sqlSession = sqlSessionFactory.openSession(true);

            //执行查询
            //获取动态创建的UserDao的实现(映射器)
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            user = mapper.getUserById(id);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return user;
    }

    @ApiOperation(value = "获取用户列表", notes = "获取所有用户列表")
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public List<User> getUserList() {
        List<User> user = null;

        try {
            // 读取配置文件
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);

            // 通过SqlSessionFactoryBuilder构建一个SqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            //打开SqlSession,true表示自动提交事务
            SqlSession sqlSession = sqlSessionFactory.openSession(true);

            //执行查询
            //获取动态创建的UserDao的实现(映射器)
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            user = mapper.getUserList();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return user;
    }

    @ApiIgnore
    @RequestMapping(value = "/getUserMap")
    public List<Map<String, Object>> getUserMap() {
        List<Map<String, Object>> user = null;

        try {
            // 读取配置文件
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);

            // 通过SqlSessionFactoryBuilder构建一个SqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            //打开SqlSession,true表示自动提交事务
            SqlSession sqlSession = sqlSessionFactory.openSession(true);

            //执行查询
            //获取动态创建的UserDao的实现(映射器)
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            user = mapper.getMapList();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return user;
    }

    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser() {
        User user = new User("5", "宋6", "7788", "0", "南湖");
        try {
            // 读取配置文件
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);

            // 通过SqlSessionFactoryBuilder构建一个SqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            //打开SqlSession,true表示自动提交事务
            SqlSession sqlSession = sqlSessionFactory.openSession(true);

            //执行查询
            //获取动态创建的UserDao的实现(映射器)
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            mapper.addUser(user);
        } catch (Exception ex) {
            System.out.println(ex);
            return ex.getMessage();
        }
        return "successful";
    }

    @ApiOperation(value = "更新用户详细信息", notes = "根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "int"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @RequestBody User user) {
        return "success";
    }

    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "int")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable int id) {
        return "success";
    }
}
