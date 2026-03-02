package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户 REST 控制器
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 获取所有用户
     * GET /api/users
     */
    @GetMapping
    public Result<List<User>> list() {
        return Result.success(userService.findAll());
    }

    /**
     * 根据ID获取用户
     * GET /api/users/{id}
     */
    @GetMapping("/{id}")
    public Result<User> getById(@PathVariable Long id) {
        return userService.findById(id)
                .map(Result::success)
                .orElse(Result.error(404, "用户不存在"));
    }

    /**
     * 创建用户
     * POST /api/users
     */
    @PostMapping
    public Result<User> create(@RequestBody User user) {
        return Result.success("用户创建成功", userService.create(user));
    }

    /**
     * 更新用户
     * PUT /api/users/{id}
     */
    @PutMapping("/{id}")
    public Result<User> update(@PathVariable Long id, @RequestBody User user) {
        return Result.success("用户更新成功", userService.update(id, user));
    }

    /**
     * 删除用户
     * DELETE /api/users/{id}
     */
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return Result.success("用户删除成功", null);
    }
}
