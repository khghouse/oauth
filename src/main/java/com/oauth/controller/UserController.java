package com.oauth.controller;

import com.oauth.model.User;
import com.oauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    public List<User> listUser() {
        return userService.findAll();
    }

    @PostMapping("/user")
    public User create(@RequestBody User user) {
        return userService.save(user);
    }

    @DeleteMapping("/user/{id}")
    public String delete(@PathVariable Long id) {
        userService.delete(id);
        return "success";
    }
}

