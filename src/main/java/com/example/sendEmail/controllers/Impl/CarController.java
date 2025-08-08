package com.example.sendEmail.controllers.Impl;

import com.example.sendEmail.models.CarModel;
import com.example.sendEmail.models.OrderModel;
import com.example.sendEmail.services.impl.CarServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/cart/")
@RequiredArgsConstructor
public class CarController {

    private final CarServiceImpl carService;

    @PostMapping("/{userId}/add/{productId}")
    public ResponseEntity<CarModel> addProduct(@PathVariable Long userId, @PathVariable Long productId, @RequestParam int quantity) {
        return ResponseEntity.ok(carService.addProductToCart(userId, productId, quantity));
    }

    @DeleteMapping("/{userId}/remove/{productId}")
    public ResponseEntity<CarModel> removeProduct(@PathVariable Long userId, @PathVariable Long productId) {
        return ResponseEntity.ok(carService.removeProductFromCart(userId, productId));
    }

    @PostMapping("/{userId}/pay")
    public ResponseEntity<OrderModel> payCart(@PathVariable Long userId) {
        return ResponseEntity.ok(carService.payCart(userId));
    }

}
