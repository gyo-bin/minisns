package kr.hs.dgsw.web0325.Controller;


import kr.hs.dgsw.web0325.Domain.User;
import kr.hs.dgsw.web0325.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public List<User> list() {
        return userService.list();
    }

    @GetMapping("/user/{id}")
    public User view(@PathVariable long id) {
        return userService.view(id).orElse(null);
    }

    @PostMapping("/user")
    public User add(@RequestBody User user) {
        return userService.add(user);
    }

    @PutMapping("/user/{id}")
    public User update(@PathVariable long id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping("/user/{id}")
    public boolean delete(@PathVariable long id) {
        return userService.delete(id);
    }
}