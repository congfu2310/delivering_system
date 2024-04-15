package com.group.deliveringsystem.controller;

import com.group.deliveringsystem.commonutils.R;
import com.group.deliveringsystem.entity.User;
import com.group.deliveringsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("login")
    public R login(@RequestBody User user) {
        User newUser = userService.getUser(user);
        return R.ok().data("user", newUser);
    }

    @PostMapping("register")
    public R register(@RequestBody User user) {
        userService.addUser(user);
        return R.ok();
    }

    @PutMapping("update")
    public R updatePassword(@RequestBody User user) {
        userService.updateUser(user);
        return R.ok();
    }

    @PostMapping("delete")
    public R deleteUser(String id) {
        userService.deleteUser(id);
        return R.ok();
    }

}
