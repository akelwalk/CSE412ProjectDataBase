package com.example.javafxdemo;

public interface IDatabaseOperations {
    String checkLogin(String email, String password);
    String registerUser(String email, String firstName, String lastName, String password, String phoneNumber);

}
