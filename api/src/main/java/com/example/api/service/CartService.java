package com.example.api.service;

import com.example.api.dto.CartDto;
import com.example.api.dto.ProductInCartDto;
import com.example.api.model.Cart;
import com.example.api.model.CartProduct;
import com.example.api.model.Product;
import com.example.api.model.User;
import com.example.api.repository.CartProductRepository;
import com.example.api.repository.CartRepository;
import com.example.api.repository.ProductRepository;
import com.example.api.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class CartService {

    private final CartProductRepository cartProductRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    CartService(CartProductRepository cartProductRepository) {
        this.cartProductRepository = cartProductRepository;
    }

    // Method to add product to cart
    public void addProductToCart(long cartId, long productId, double quantityInGrams) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartProduct cartProduct = new CartProduct(cart, product, quantityInGrams);

        // Save CartProduct, which will update cart's macros via lifecycle hooks
        cart.getCartProducts().add(cartProduct);
        cartRepository.save(cart); // Save cart (which triggers @PrePersist on CartProduct)
    }

    // Conversion from ProductDto to Product
    // private Product convertToProduct(ProductDto productDto) {
    // // Fetch the product entity from the database using the Product ID from the
    // DTO
    // return productService.findProductById(productDto.getId());
    // }

    @Autowired
    private CartRepository cartRepository;

    public Cart convertToEntity(CartDto cartDto) {
        User user = userRepository.findById(cartDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new Cart(cartDto.getId(), cartDto.getName(), cartDto.getCreationDate(), cartDto.getTotalCalories(),
                cartDto.getTotalCarbohydrates(), cartDto.getTotalFibers(), cartDto.getTotalProteins(), user);
    }

    public CartDto getCart(final Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart not found"));
        return convertToDto(cart);
    }

    public List<CartDto> getCarts() {
        return cartRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public boolean deleteCart(Long id) {
        cartRepository.deleteById(id);
        return true;
    }

    public CartDto createCart(CartDto cartDto) {
        // Find the user by ID
        User user = userRepository.findById(cartDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Create a new Cart
        Cart cart = new Cart();
        cart.setCreationDate(cartDto.getCreationDate());
        cart.setName(cartDto.getName());
        cart.setUser(user);

        List<CartProduct> cartProducts = new ArrayList<>();
        // for (ProductDto productDto : cartDto.getProducts()) {
        for (ProductInCartDto productInCartDto : cartDto.getProducts()) {
            // Fetch the product entity from the database
            Product product = productRepository.findById(productInCartDto.getId())
                    .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productInCartDto.getId()));
            System.out.println("Received product: " + product);

            // Debugging: Print the fetched Product details
            // System.out.println("Fetched Product: " + product);

            // Now, populate the ProductDto with the relevant values from the Product entity
            productInCartDto.setName(product.getName());
            productInCartDto.setCategory(product.getCategory());
            productInCartDto.setProteins(product.getProteins());
            productInCartDto.setFibers(product.getFibers());
            productInCartDto.setCalories(product.getCalories());
            productInCartDto.setCarbohydrates(product.getCarbohydrates());

            // Debugging: Print the populated ProductDto details
            // System.out.println("Populated ProductDto: " + productInCartDto);

            // Now, create the CartProduct with the product details and quantity_in_grams
            CartProduct cartProduct = new CartProduct(cart, product, productInCartDto.getQuantityInGrams());
            cartProducts.add(cartProduct);
        }

        // Set the cart products
        cart.setCartProducts(cartProducts);

        // Initialize totals to zero
        double totalCalories = 0.0;
        double totalProteins = 0.0;
        double totalFibers = 0.0;
        double totalCarbohydrates = 0.0;

        // Loop through all the products in the cart
        for (CartProduct cartProduct : cart.getCartProducts()) {
            Product product = cartProduct.getProduct(); // Get the product entity
            double quantityInGrams = cartProduct.getQuantityInGrams(); // Get the quantity in grams for this product

            // Calculate the macros based on the quantity
            totalCalories += (product.getCalories() * quantityInGrams) / 100;
            totalProteins += (product.getProteins() * quantityInGrams) / 100;
            totalFibers += (product.getFibers() * quantityInGrams) / 100;
            totalCarbohydrates += (product.getCarbohydrates() * quantityInGrams) / 100;
        }

        // Set the calculated totals in the Cart entity
        cart.setTotalCalories(totalCalories);
        cart.setTotalProteins(totalProteins);
        cart.setTotalFibers(totalFibers);
        cart.setTotalCarbohydrates(totalCarbohydrates);

        // Save the cart
        cart = cartRepository.save(cart);

        // Return the CartDto with the calculated totals
        return convertToDto(cart);
    }

    public CartDto convertToDtoForUpdate(Cart cart) {
        List<ProductInCartDto> productInCartDtos = cart.getCartProducts().stream()
                .map(cartProduct -> {
                    Product product = cartProduct.getProduct();
                    ProductInCartDto productInCartDto = new ProductInCartDto(
                            product.getId(),
                            product.getName(),
                            product.getCategory(),
                            product.getProteins(),
                            product.getFibers(),
                            product.getCalories(),
                            product.getCarbohydrates(),
                            cartProduct.getQuantityInGrams());
                    return productInCartDto;
                })
                .collect(Collectors.toList());

        return new CartDto(
                cart.getId(),
                cart.getName(),
                cart.getCreationDate(),
                cart.getTotalCalories(),
                cart.getTotalCarbohydrates(),
                cart.getTotalFibers(),
                cart.getTotalProteins(),
                cart.getUser().getId(),
                productInCartDtos);
    }

    public CartDto convertToDto(Cart cart) {
        // System.out.println("getCartProducts ==> " + cart.getCartProducts().size());
        List<CartProduct> cartProducts = cart.getCartProducts();
        List<ProductInCartDto> productInCartDtos = cartProducts.stream() // Use getCartProducts instead of getProductList
        .map(cartProduct -> {
            Product product = cartProduct.getProduct();
            if (product == null) {
                throw new RuntimeException("Product is null in CartProduct");
            }

            return new ProductInCartDto(
                    product.getId(),
                    product.getName(),
                    product.getCategory(),
                    product.getProteins(),
                    product.getFibers(),
                    product.getCalories(),
                    product.getCarbohydrates(),
                    cartProduct.getQuantityInGrams());
        })
        .collect(Collectors.toList());

        // System.out.println("3");
        return new CartDto(
                cart.getId(),
                cart.getName(),
                cart.getCreationDate(),
                cart.getTotalCalories(),
                cart.getTotalCarbohydrates(),
                cart.getTotalFibers(),
                cart.getTotalProteins(),
                cart.getUser().getId(),
                productInCartDtos // Now passing the list of ProductDto objects
        );
    }

    public Cart findCartById(long id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart not found with ID: " + id));
    }

    // Helper function to add a new product to the cart if it doesn't exist
    private void addProductToCartIfNotExists(Cart existingCart, ProductInCartDto productInCartDto) {
        // Check if the product already exists in the cart
        Optional<CartProduct> existingCartProductOpt = existingCart.getCartProducts().stream()
                .filter(cartProduct -> cartProduct.getProduct().getId() == productInCartDto.getId())
                .findFirst();

        if (!existingCartProductOpt.isPresent()) {
            // If the product does not exist in the cart, add it
            Product product = productRepository.findById(productInCartDto.getId())
                    .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productInCartDto.getId()));

            CartProduct newCartProduct = new CartProduct();
            newCartProduct.setCart(existingCart); // Set the cart
            newCartProduct.setProduct(product); // Set the product
            newCartProduct.setQuantityInGrams(productInCartDto.getQuantityInGrams()); // Set the quantity

            existingCart.getCartProducts().add(newCartProduct); // Add to the cart's product list
        }
    }

    private void recalculateCartTotals(Cart existingCart) {
        double totalCalories = 0.0;
        double totalProteins = 0.0;
        double totalFibers = 0.0;
        double totalCarbohydrates = 0.0;

        for (CartProduct cartProduct : existingCart.getCartProducts()) {
            Product product = cartProduct.getProduct();
            double quantityInGrams = cartProduct.getQuantityInGrams();

            totalCalories += (product.getCalories() * quantityInGrams) / 100;
            totalProteins += (product.getProteins() * quantityInGrams) / 100;
            totalFibers += (product.getFibers() * quantityInGrams) / 100;
            totalCarbohydrates += (product.getCarbohydrates() * quantityInGrams) / 100;
        }

        existingCart.setTotalCalories(totalCalories);
        existingCart.setTotalProteins(totalProteins);
        existingCart.setTotalFibers(totalFibers);
        existingCart.setTotalCarbohydrates(totalCarbohydrates);
    }

    @Transactional
    public CartDto addProductsToCart(long cartId, List<ProductInCartDto> productDtos) {
        // Fetch the cart entity
        Cart existingCart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        System.out.println("Error: For loop");
        // Iterate over the list of Product IDs from the payload
        for (ProductInCartDto productDto : productDtos) {

            // Check if the product already exists in the cart
            Optional<CartProduct> existingCartProduct = existingCart.getCartProducts().stream()
                    .filter(cp -> {
                        System.out.println("product ID from endpoint ===> " + productDto.getId());
                        // Ensure product is not null before checking its ID
                        return cp.getProduct() != null && cp.getProduct().getId() == productDto.getId();

                    })
                    .findFirst();

            if (existingCartProduct.isPresent()) {
                // Update existing CartProduct
                CartProduct cartProduct = existingCartProduct.get();
                cartProduct.setQuantityInGrams(productDto.getQuantityInGrams());
                cartProductRepository.save(cartProduct);
            } else {
                // Create new CartProduct if not already in the cart
                // Ensure product is fetched properly before creating the CartProduct
                Product product = productRepository.findById(productDto.getId())
                    .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productDto.getId()));
                System.out.println("Product fetched: " + product);
                System.out.println("Product QUANTITY IN GRAMS fetched: " +  productDto.getQuantityInGrams());
                // Now create the CartProduct and associate it with the product and cart
                CartProduct newCartProduct = new CartProduct(existingCart, product, productDto.getQuantityInGrams());

                existingCart.getCartProducts().add(cartProductRepository.save(newCartProduct));

                System.out.println(
                        "Creating CartProduct with Product ID: " + product.getId() + " and Name: " + product.getName());
                System.out.println("Product ID ==> " + newCartProduct.getProduct().getId());
                System.out.println("Product Name ==> " + newCartProduct.getProduct().getName());
                System.out.println("Current cart Total Calories ==> " + existingCart.getTotalCalories());
            }
        }

        // Recalculate the cart totals after adding products
        recalculateCartTotals(existingCart);

        // Save the updated cart (with the new or updated CartProducts)
        cartRepository.save(existingCart);

        // Return the updated CartDto
        return convertToDto(existingCart);
    }

    // Function to delete specific products inside a cart by their product IDs
    public CartDto removeProductsFromCart(long cartId, List<Long> productIds) {
        // Fetch the cart entity
        Cart existingCart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        // Iterate over the list of product IDs and remove the corresponding
        // CartProducts
        for (Long productId : productIds) {
            // Find the CartProduct with the given product ID
            CartProduct cartProductToRemove = existingCart.getCartProducts().stream()
                    .filter(cartProduct -> cartProduct.getProduct().getId() == productId)
                    .findFirst()
                    .orElse(null);

            if (cartProductToRemove != null) {
                // Remove the CartProduct from the cart's product list
                existingCart.getCartProducts().remove(cartProductToRemove);

                // Delete the CartProduct entity from the database
                cartProductRepository.delete(cartProductToRemove);
            }
        }

        recalculateCartTotals(existingCart);

        // Save the updated cart (after removal of products)
        existingCart = cartRepository.save(existingCart);

        return convertToDto(existingCart);
    }

    public CartDto updateCart(long cartId, CartDto cartDto) {
        // Fetch the existing Cart entity
        Cart existingCart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        User updatedUser = userRepository.findById(cartDto.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));;
        // Set basic cart details
        existingCart.setName(cartDto.getName());
        existingCart.setUser(updatedUser);
        existingCart.setCreationDate(cartDto.getCreationDate());

        // // Get the list of new product IDs sent in the payload
        // List<Long> newProductIds = cartDto.getProducts().stream()
        //         .map(ProductInCartDto::getId)
        //         .collect(Collectors.toList());

        // Iterate over the new products in the payload
        for (ProductInCartDto productInCartDto : cartDto.getProducts()) {
            // First, try to add the product to the cart if it's not already present
            addProductToCartIfNotExists(existingCart, productInCartDto);
        }

        // Update quantities for existing products
        for (ProductInCartDto productInCartDto : cartDto.getProducts()) {
            Optional<CartProduct> existingCartProductOpt = existingCart.getCartProducts().stream()
                    .filter(cartProduct -> cartProduct.getProduct().getId() == productInCartDto.getId())
                    .findFirst();

            if (existingCartProductOpt.isPresent()) {
                // Update the quantity of the existing product in the cart
                CartProduct existingCartProduct = existingCartProductOpt.get();
                existingCartProduct.setQuantityInGrams(productInCartDto.getQuantityInGrams());
            }
        }

        // Recalculate totals
        double totalCalories = 0.0;
        double totalProteins = 0.0;
        double totalFibers = 0.0;
        double totalCarbohydrates = 0.0;

        for (CartProduct cartProduct : existingCart.getCartProducts()) {
            Product product = cartProduct.getProduct();
            double quantityInGrams = cartProduct.getQuantityInGrams();

            // Ensure product is not null before calculating totals
            if (product != null) {
                totalCalories += (product.getCalories() * quantityInGrams) / 100;
                totalProteins += (product.getProteins() * quantityInGrams) / 100;
                totalFibers += (product.getFibers() * quantityInGrams) / 100;
                totalCarbohydrates += (product.getCarbohydrates() * quantityInGrams) / 100;
            } else {
                System.out.println("Error: Product is null during recalculation.");
            }
        }

        // Set the updated totals to the cart
        existingCart.setTotalCalories(totalCalories);
        existingCart.setTotalProteins(totalProteins);
        existingCart.setTotalFibers(totalFibers);
        existingCart.setTotalCarbohydrates(totalCarbohydrates);

        // Save the updated cart
        Cart updatedCart = cartRepository.save(existingCart);

        return convertToDto(updatedCart); // Convert to DTO and return
    }

}
