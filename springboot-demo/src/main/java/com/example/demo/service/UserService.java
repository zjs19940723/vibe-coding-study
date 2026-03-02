package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * 用户服务接口
 */
public interface UserService {

    /** 获取所有用户 */
    List<User> findAll();

    /** 根据ID查找用户 */
    Optional<User> findById(Long id);

    /** 创建用户 */
    User create(User user);

    /** 更新用户 */
    User update(Long id, User user);

    /** 删除用户 */
    void delete(Long id);
}
