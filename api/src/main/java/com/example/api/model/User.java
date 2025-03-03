package com.example.api.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
    @Entity
    @Table(name = "users")
    public class User{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @Column(name="firstName")
        private String firstName;

        @Column(name="lastName")
        private String lastName;

        @Column(name="email")
        private String mail;

        @Column(name="cart_id")
        private String cartId;
        
        @Column(name="password")
        private String password;
        
        @Column(name="salt")
        private String salt;

    @OneToOne
    @JoinColumn(name = "bank_account_id")
    private BankAccount bankAccount;

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public User() {

        }

    public User(long id,String firstName,  String lastName, String mail, String password, String salt) {
            this.firstName = firstName;
            this.id = id;
            this.lastName = lastName;
            this.mail = mail;
            this.password = password;
            this.salt = salt;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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
}
