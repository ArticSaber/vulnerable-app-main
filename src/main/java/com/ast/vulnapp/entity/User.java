package com.ast.vulnapp.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.io.ObjectStreamField;

@Entity(name="users")
public class User implements Serializable {
    @Id
    private String id;

    private String email;

    @Column(length=7)
    private String password;

    private String fullName;

    public User() {}

    public User(String id, String email, String password, String fullName) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
    }

    public User(User user) {
        this.id = user.id;
        this.email = user.email;
        this.fullName = user.fullName;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
