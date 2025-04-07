// package com.example.api.service;

// import com.example.api.model.Cart;
// import com.example.api.model.CartProduct;
// import com.example.api.model.Product;
// import com.example.api.repository.CartProductRepository;
// import com.example.api.repository.CartRepository;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// @Service
// public class CartProductService {

//     @Autowired
//     private CartProductRepository cartProductRepository;

//     @Autowired
//     private CartRepository cartRepository;

//     // Method to add or update a CartProduct
//     public CartProduct addOrUpdateCartProduct(Cart cart, Product product, double quantityInGrams) {
//         CartProduct existingCartProduct = cartProductRepository.findByCartAndProduct(cart, product);
//         if (existingCartProduct != null) {
//             existingCartProduct.setQuantityInGrams(quantityInGrams);
//             cartProductRepository.save(existingCartProduct);
//         } else {
//             CartProduct newCartProduct = new CartProduct(cart, product, quantityInGrams);
//             cartProductRepository.save(newCartProduct);
//         }
    
//         // Recalculate the cart's total macros after adding or updating the product
//         recalculateCartMacros(cart);
    
//         return existingCartProduct != null ? existingCartProduct : new CartProduct(cart, product, quantityInGrams);
//     }

//     // Method to remove a CartProduct
//     public void removeCartProduct(CartProduct cartProduct) {
//         cartProductRepository.delete(cartProduct);
//     }

//     public void recalculateCartMacros(Cart cart) {
//         double totalCalories = 0;
//         double totalProteins = 0;
//         double totalFibers = 0;
//         double totalCarbohydrates = 0;

//         // Iterate through each CartProduct to calculate totals
//         for (CartProduct cartProduct : cart.getCartProducts()) {
//             Product product = cartProduct.getProduct();
//             double quantityInGrams = cartProduct.getQuantityInGrams();

//             // Multiply product macros by the quantity in grams
//             totalCalories += (product.getCalories() * (quantityInGrams / 100));
//             totalProteins += (product.getProteins() * (quantityInGrams / 100));
//             totalFibers += (product.getFibers() * (quantityInGrams / 100));
//             totalCarbohydrates += (product.getCarbohydrates() * (quantityInGrams / 100));
//         }

//         // Update the cart's total macros
//         cart.setTotalCalories(totalCalories);
//         cart.setTotalProteins(totalProteins);
//         cart.setTotalFibers(totalFibers);
//         cart.setTotalCarbohydrates(totalCarbohydrates);

//         // Save the updated cart to the repository
//         cartRepository.save(cart);
//     }
// }