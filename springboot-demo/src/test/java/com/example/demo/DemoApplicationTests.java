package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Spring Boot 集成测试
 */
@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        // 验证 Spring 上下文正常加载
    }

    @Test
    void testFindAllUsers() {
        List<User> users = userService.findAll();
        assertNotNull(users);
        // 初始化数据有3条
        assertEquals(3, users.size());
        System.out.println("所有用户: " + users);
    }

    @Test
    void testCreateUser() {
        User user = new User(null, "测试用户", "test@example.com", "13900139000");
        User saved = userService.create(user);
        assertNotNull(saved.getId());
        assertEquals("测试用户", saved.getName());
        System.out.println("创建用户成功: " + saved);
    }
}
