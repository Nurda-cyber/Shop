package org.example.Service;

import lombok.RequiredArgsConstructor;
import org.example.Entity.User;
import org.example.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User addUser(String name, String email, String password, String role) {
        if (userRepository.findByEmail(email) != null) {
            throw new RuntimeException("Бұл email тіркелген!");
        }

        User newUser = new User();
        newUser.setUsername(name);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setRole(role);

        return userRepository.save(newUser);
    }
}
