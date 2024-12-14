package com.flipkartProduct.product.cart.service;

import com.flipkartProduct.product.Repository.ProductRepository;
import com.flipkartProduct.product.cart.DTO.CartDto;
import com.flipkartProduct.product.cart.model.Cart;
import com.flipkartProduct.product.cart.model.CartItem;
import com.flipkartProduct.product.cart.repository.CartRepository;
import com.flipkartProduct.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements CartServiceInterface {

    @Autowired
    @Qualifier("primaryMongoTemplate")
    private MongoTemplate primaryMongoTemplate;

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<CartDto> getCart(String userId) {
        return cartRepository.findCartByCustomerId(userId);
    }

    @Override
    public ResponseEntity<String> addToCart(Cart cart, String userId) {
        List<CartItem> cartItems = cart.getProductList();
        long quantity = cartItems.stream().mapToLong(CartItem::getQuantity).sum();
        String dataProductName = cartItems.stream().map(CartItem::getDataProductName).findFirst().toString();
        Optional<Product> product = Optional.ofNullable(productRepository.findBydataProductNameForProduct(dataProductName));
        if(product.isPresent()) {
            System.out.println(product.get().toString());
            if (quantity > product.get().getQuantity()) {
                return ResponseEntity.badRequest().body("Quantity exceeded");
            }
        }

        cartRepository.save(new Cart(userId,cart.getProductList()));
        return ResponseEntity.ok().body("Product added to Cart");
    }

    @Override
    public ResponseEntity<String> removeFromCart(Cart cart) {
        return null;
    }

    @Override
    public ResponseEntity<String> updateCart(Cart cart) {
        return null;
    }
}
