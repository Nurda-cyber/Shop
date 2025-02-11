package org.example.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Entity
    @Table(name = "users")
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public class User {
        @Id
        @GeneratedValue
        @Column(name = "id")
        private Long id;
        private String username;
        private String email;
        private String password;
        private String role;
}
