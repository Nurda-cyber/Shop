package org.example.Controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.Entity.User;
import org.example.Repository.UserRepository;
import org.example.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/Login")
    public String loginPage() {
        return "Login";
    }

    @GetMapping("/Register")
    public String registerPage() {
        return "Registration";
    }

    @PostMapping("/Register")
    public String registerUser(@ModelAttribute User user, Model model) {
        try {
            userService.addUser(user.getUsername(), user.getEmail(), user.getPassword(), "User");
            return "redirect:/Login";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "Registration";
        }
    }



    @PostMapping("/Login")
    public String loginUser(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        User user = userRepository.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("loggedUser", user);
            return "redirect:/Home";
        } else {
            model.addAttribute("error", "Қате email немесе пароль!");
            return "Login";
        }
    }

    @GetMapping("/Logout")
    public String logoutUser(HttpSession session) {
        session.invalidate();
        return "redirect:/Login";
    }

    @GetMapping("/Home")
    public String homePage(HttpSession session, Model model) {
        model.addAttribute("user", session.getAttribute("loggedUser"));
        return "Home";
    }

    @GetMapping("/Profile")
    public String profilePage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedUser");
        if (user == null) {
            return "redirect:/Login";
        }
        model.addAttribute("user", user);
        return "ProfilePage";
    }
}
