package com.tuz.coffemachine.models;

import com.tuz.coffemachine.models.exception.IncorrectArgumentException;

public class User {
    private int id_user;
    private String login;
    private String password;
    private String role;
    private int total_sum;
    private String pin;

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) throws IncorrectArgumentException {
        if(login==null || login.isEmpty()){
            throw new IncorrectArgumentException("Login can't be empty or null");
        }
        this.login = login;
    }

    public String getPassword() throws IncorrectArgumentException {
        if(password==null || password.isEmpty()){
            throw new IncorrectArgumentException("Password can't be empty or null");
        }
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) throws IncorrectArgumentException {
        if(role==null || role.isEmpty()){
            throw new IncorrectArgumentException("Role can't be empty or null");
        }
        this.role = role;
    }

    public int getTotal_sum() {
        return total_sum;
    }

    public void setTotal_sum(int total_sum) {
        this.total_sum = total_sum;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) throws IncorrectArgumentException {
        if(pin==null || pin.isEmpty()){
            throw new IncorrectArgumentException("PIN can't be empty or null");
        }
        this.pin = pin;
    }
}
