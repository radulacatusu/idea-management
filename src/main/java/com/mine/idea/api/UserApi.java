package com.mine.idea.api;

import javax.validation.constraints.NotNull;

/**
 * @stefanl
 */
public class UserApi {
    @NotNull
    private String name;
    @NotNull
    private String email;

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


    @Override
    public String toString() {
        return "User{" +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
