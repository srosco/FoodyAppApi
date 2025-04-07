package com.example.api.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "cart_id")
    private long cartId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "salt")
    private String salt;

    @Column(name = "aimed_maccros")
    private double aimedMaccros;

    @Column(name = "current_maccros")
    private double currentMaccros;

    @OneToMany(mappedBy = "user")
    private List<Cart> carts;

    public User() {

    }

    public User(long id, String firstName, String lastName, String email, String password, String salt,
            double aimedMaccros, double currentMaccros, long cartId) {
        this.firstName = firstName;
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.aimedMaccros = aimedMaccros;
        this.currentMaccros = currentMaccros;
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

    public void setEmail(String email) {
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
