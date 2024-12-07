package org.example.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class UserController {

    @GetMapping("/Login")
    public String User(){

        return "Login";
    }

    @GetMapping("/Registration")
    public String Registration(){

        return "Registration";
    }
    @GetMapping("/Home")
    public String Home(){

        return "Home";
    }
}
