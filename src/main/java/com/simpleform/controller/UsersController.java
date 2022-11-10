package com.simpleform.controller;

import com.simpleform.model.UsersModel;
import com.simpleform.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) throws InterruptedException {
        //usersService.makeApplicationWait();
        model.addAttribute("registerRequest", new UsersModel());
        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) throws InterruptedException {
        //usersService.makeApplicationWait();
        model.addAttribute("loginRequest", new UsersModel());
        return "login_page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UsersModel usersModel) throws InterruptedException {
        System.out.println("register request " + usersModel);
        //usersService.makeApplicationWait();
        UsersModel registeredUser = usersService.registerUser(usersModel.getUsername(), usersModel.getPassword(), usersModel.getEmail());
        return registeredUser == null ? "error_page" : "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UsersModel usersModel, Model model) throws InterruptedException {
        System.out.println("login request " + usersModel);
        //usersService.makeApplicationWait();
        UsersModel authenticated = usersService.authenticate(usersModel.getUsername(), usersModel.getPassword());
        if(authenticated != null) {
            model.addAttribute("userUsername", authenticated.getUsername());
            return "personal_page";
        }
        else {
            return "error_page";
        }
    }


}
