package com.web2.trabalhoFinal.infrastructure.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "isSuperUser")
    private boolean isSuperUser;

    @Column(name = "isAtive")
    private boolean isAtive;

    public UserEntity() {}

    public UserEntity(String name, String email, String password, boolean isSuperUser, boolean isAtive) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.isSuperUser = isSuperUser;
        this.isAtive = isAtive;
    }
    
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isSuperUser() {
        return isSuperUser;
    }

    public boolean isAtive() {
        return isAtive;
    }
}
