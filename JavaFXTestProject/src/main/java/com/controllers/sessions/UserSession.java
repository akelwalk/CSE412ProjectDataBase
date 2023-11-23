package com.controllers.sessions;

public class UserSession {
    private static UserSession instance;

    private int userID;
    private String email;
    private String role;

    public UserSession(int userID, String email, String role) {
        this.userID = userID;
        this.email = email; 
        this.role = role;
    }

    public static UserSession getInstance(int userID, String email, String role) {
        if (instance == null) {
            instance = new UserSession(userID, email, role);
        }
        return instance;
    }

    // New method to get the existing instance without creating a new one
    public static UserSession getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Session not created yet");
        }
        return instance;
    }

    public int getUserID() {
        return userID;
    }

    public String getRole() {
        return role;
    }

    public String getEmail()
    {
        return email;
    }

    public static void cleanUserSession() {
        instance = null;
    }
}

