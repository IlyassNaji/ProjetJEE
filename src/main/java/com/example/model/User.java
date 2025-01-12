package com.example.model;

public class User {
    private Long id;
    private String email;
    private String password;
    private String secretKey;

    public User(Long id, String email, String password, String secretKey) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.secretKey = secretKey;
    }

    public User(String email, String password, String secretKey) {
        this(null, email, password, secretKey);
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getSecretKey() { return secretKey; }
    public void setSecretKey(String secretKey) { this.secretKey = secretKey; }
}