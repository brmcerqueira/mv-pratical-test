package com.resourceaccount.dto;

public class UserListDto {
    private final String name;

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    private final String login;
    private final String email;

    public UserListDto(String name, String login, String email) {
        this.name = name;
        this.login = login;
        this.email = email;
    }
}
