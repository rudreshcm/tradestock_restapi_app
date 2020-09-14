package com.genpact.paypal.practice.tradestock.dto;

import com.genpact.paypal.practice.tradestock.model.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDTO {
    @NotNull
    @Size(min=4, max = 65)
    private String firstName;
    @NotNull
    @Size(min=4, max = 65)
    private String lastName;
    @NotNull
    @Size(min=4, max = 65)
    @Email
    private String email;
    @NotNull
    @Size(min=6, max = 65)
    private String password;
    @NotNull
    @Size(max = 65)
    private String country;



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public static User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        return user;
    }
}
