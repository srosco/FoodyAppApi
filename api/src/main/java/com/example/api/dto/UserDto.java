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
    private double aimedMaccros;

    @JsonProperty("aimed_carbohydrates")
    private double aimedCarbohydrates;

    @JsonProperty("aimed_fibers")
    private double aimedFibers;

    @JsonProperty("aimed_proteins")
    private double aimedProteins;

    @JsonProperty("aimed_calories")
    private double aimedCalories;

    @JsonProperty("current_maccros")
    private double currentMaccros;

    @JsonProperty("cart_id")
    private long cartId;

    public UserDto(long id, String firstName, String lastName, String email, String password, String salt,
    double aimedMaccros, double currentMaccros, double aimedCarbohydrates, double aimedFibers, double aimedProteins, double aimedCalories, long cartId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.currentMaccros = currentMaccros;
        this.aimedMaccros = aimedMaccros;
        this.aimedCarbohydrates = aimedCarbohydrates;
        this.aimedFibers = aimedFibers;
        this.aimedProteins = aimedProteins;
        this.aimedCalories = aimedCalories;
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
        return aimedMaccros;
    }
    
    public void setAimedMaccros(double aimedMaccros) {
        this.aimedMaccros = aimedMaccros;
    }

    public double getAimedCarbohydrates() {
        return aimedCarbohydrates;
    }
    
    public void setAimedCarbohydrates(double aimedCarbohydrates) {
        this.aimedCarbohydrates = aimedCarbohydrates;
    }

    public double getAimedFibers() {
        return aimedFibers;
    }
    
    public void setAimedFibers(double aimedFibers) {
        this.aimedFibers = aimedFibers;
    }
    
    public double getAimedProteins() {
        return aimedProteins;
    }
    
    public void setAimedProteins(double aimedProteins) {
        this.aimedProteins = aimedProteins;
    }

    public double getAimedCalories() {
        return aimedCalories;
    }
    
    public void setAimedCalories(double aimedCalories) {
        this.aimedCalories = aimedCalories;
    }

    public double getCurrentMaccros() {
        return currentMaccros;
    }
    
    public void setCurrentMaccros(double currentMaccros) {
        this.currentMaccros = currentMaccros;
    }

    public long getCartId() {
        return cartId;
    }
    
    public void setCartId(long cartId) {
        this.cartId = cartId;
    }
}
