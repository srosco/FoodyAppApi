// package com.example.api.controller;

// import com.example.api.dto.CartDto;
// import com.example.api.dto.ProductDto;
// import com.example.api.model.Cart;
// import com.example.api.model.Product;
// import com.example.api.service.CartProductService;
// import com.example.api.service.CartService;
// import com.example.api.service.ProductService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/cartproducts")
// public class CartProductController {

//     @Autowired
//     private CartService cartService;

//     @Autowired
//     private ProductService productService;

//     @Autowired
//     private CartProductService cartProductService;

//     @PostMapping("/add")
//     public CartDto addProductToCart(@RequestBody AddProductToCartRequest request) {
//         // Fetch the cart by its ID using the CartService
//         Cart cart = cartService.findCartById(request.getCartDto().getId());
//         Product product = productService.findProductById(request.getProductDto().getId());

//         // Add or update the product in the cart
//         cartProductService.addOrUpdateCartProduct(cart, product, request.getProductDto().getQuantityInGrams());

//         // Return the updated cart as a CartDto
//         return cartService.convertToDto(cart);
//     }

//     // DTO to hold both CartDto and ProductDto for the request
//     public static class AddProductToCartRequest {
//         private CartDto cartDto;
//         private ProductDto productDto;

//         // Getters and Setters
//         public CartDto getCartDto() {
//             return cartDto;
//         }

//         public void setCartDto(CartDto cartDto) {
//             this.cartDto = cartDto;
//         }

//         public ProductDto getProductDto() {
//             return productDto;
//         }

//         public void setProductDto(ProductDto productDto) {
//             this.productDto = productDto;
//         }
//     }
// }
