package com.ast.vulnapp.controller;

import com.ast.vulnapp.entity.User;
import com.ast.vulnapp.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class UserController {

    private final UserServiceImpl users;

    public UserController(UserServiceImpl users) {
        this.users = users;
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String create(@RequestParam("email") String email,
                         @RequestParam("password") String password,
                         @RequestParam("fullName") String fullName) {

        User user = this.users.create(email, password, fullName);
        if (user == null) {
            return "register";
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        HttpServletRequest request) {
        Optional<User> user = this.users.validateUser(email, password);
        if (user.isEmpty()) {
            return "login";
        }

        request.getSession().setAttribute("user", user.get());
        return "home";
    }

    @GetMapping("/")
    public String home(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        return "home";
    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/login";
    }

}
