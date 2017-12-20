package com.nhga.spring.mybatis.control;

import com.nhga.spring.mybatis.bean.User;
import com.nhga.spring.mybatis.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/users")
public class UserControl {
    /***
     * http://localhost:8080/users?message=124
     * @param message
     * @return
     */
    @RequestMapping(value = "")
    public String index(String message) {
        return message;
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getUser/{id}")
    public User getUserById(@PathVariable int id) {
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

    @RequestMapping(value = "/getUser")
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

    @RequestMapping(value = "/addUser")
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
}
