package com.alantr.hrms_api.model;

public class User {
    private Long id;
    private String name;
    private String email;

    // Constructor rỗng (No-args constructor)
    public User() {
    }

    // Constructor đầy đủ (All-args constructor)
    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters và Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

