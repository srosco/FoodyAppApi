package com.example.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


@Data
public class UserDto {
    private long id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("salt")
    private String salt;

    @JsonProperty("aimed_maccros")
    private double aimed_maccros;

    @JsonProperty("current_maccros")
    private double current_maccros;

    @JsonProperty("cart_id")
    private long cartId;

    public UserDto(long id, String firstName, String lastName, String email, String password, String salt,
    double aimed_maccros, double current_maccros, long cartId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.current_maccros = current_maccros;
        this.aimed_maccros = aimed_maccros;
        this.cartId = cartId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public void setMail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
    
    public double getAimedMaccros() {
        return aimed_maccros;
    }
    
    public void setAimedMaccros(double aimed_maccros) {
        this.aimed_maccros = aimed_maccros;
    }

    public double getCurrentMaccros() {
        return current_maccros;
    }
    
    public void setCurrentMaccros(double current_maccros) {
        this.current_maccros = current_maccros;
    }

    public long getCartId() {
        return cartId;
    }
    
    public void setCartId(long cartId) {
        this.cartId = cartId;
    }
}
