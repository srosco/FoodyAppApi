package com.example.api.dto;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.api.model.User;
// import com.example.api.model.Cart;
import com.example.api.repository.CartRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


@Data
public class CartDto {

    @Autowired
    private CartRepository cartRepository;
    private long id;

    @JsonProperty("creation_date")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    private Date creation_date;

    @JsonProperty("name")
    private String name;

    @JsonProperty("total_calories")
    private double total_calories;

    @JsonProperty("total_proteins")
    private double total_proteins;

    @JsonProperty("total_fibers")
    private double total_fibers;

    @JsonProperty("total_carbohydrates")
    private double total_carbohydrates;

    @JsonProperty("products")
    private List<ProductDto> products;

    @JsonProperty("user_id")
    private User user_id;

    public CartDto(long id, String name, Date creation_date, double total_calories, double total_carbohydrates, double total_fibers, double total_proteins, User user_id) {
        this.id = id;
        this.name = name;
        this.creation_date = creation_date;
        this.total_carbohydrates = total_carbohydrates;
        this.total_calories = total_calories;
        this.total_fibers = total_fibers;
        this.total_proteins = total_proteins;
        this.user_id = user_id;
    }

    // private CartDto convertToDto(Cart cart) {
    //     return new CartDto(cart.getId(), cart.getName(), cart.getCreationDate(), cart.getTotalCalories(), cart.getTotal_carbohydrates(), cart.getTotal_fibers(), cart.getTotalProteins(), cart.getUserId());
    //     // return new CartDto(cart.getId(), cart.getName(), cart.getCreationDate(), cart.getTotalCalories(), cart.getTotal_carbohydrates(), cart.getTotal_fibers(), cart.getTotalProteins());
    // }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creation_date;
    }

    public void setCreationDate(Date creation_date) {
        this.creation_date = creation_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUserId() {
        return user_id;
    }

    public void setUserId(User user_id) {
        this.user_id = user_id;
    }

    public double getTotalCalories() {
        return total_calories;
    }

    public void setTotalCalories(double total_calories) {
        this.total_calories = total_calories;
    }

    public double getTotalCarbohydrates() {
        return total_calories;
    }

    public void setTotalCarbohydrates(double total_calories) {
        this.total_calories = total_calories;
    }

    public double getTotalFibers() {
        return total_fibers;
    }

    public void setTotalFibers(double total_fibers) {
        this.total_fibers = total_fibers;
    }

    public double getTotalProteins() {
        return total_proteins;
    }

    public void setTotalProteins(double total_proteins) {
        this.total_proteins = total_proteins;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }
}
