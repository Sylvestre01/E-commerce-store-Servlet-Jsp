package dev.koorius.demo.model;

public class User {
    private static Long userId;
    private static String username, phoneNumber, email, password;
    private static Role userRole;


    public User() {
    }

    public  Long getUserId() {
        return userId;
    }

    public  void setUserId(Long userId) {
        User.userId = userId;
    }

    public  String getUsername() {
        return username;
    }

    public  void setUsername(String username) {
        User.username = username;
    }

    public  String getPhoneNumber() {
        return phoneNumber;
    }

    public  void setPhoneNumber(String phoneNumber) {
        User.phoneNumber = phoneNumber;
    }

    public  String getEmail() {
        return email;
    }

    public  void setEmail(String email) {
        User.email = email;
    }

    public  String getPassword() {
        return password;
    }

    public  void setPassword(String password) {
        User.password = password;
    }

    public  Role getUserRole() {
        return userRole;
    }

    public  void setUserRole(Role userRole) {
        User.userRole = userRole;
    }
}
