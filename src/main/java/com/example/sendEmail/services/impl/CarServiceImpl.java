package com.example.sendEmail.services.impl;

import com.example.sendEmail.models.*;
import com.example.sendEmail.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarServiceImpl {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CarRepository carRepository;
    private final CarItemRepository carItemRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository ordenItemRepository;

    // 1. Obtener o crear carrito activo
    public CarModel getOrCreateCart(Long userId) {
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (user.getCar() != null && !user.getCar().isPayed()) {
            return user.getCar();
        }

        CarModel newCar = new CarModel();
        newCar.setUserModel(user);
        newCar.setPayed(false);
        carRepository.save(newCar);

        user.setCar(newCar);
        userRepository.save(user);

        return newCar;
    }

    // 2. Agregar producto al carrito
    public CarModel addProductToCart(Long userId, Long productId, int quantity) {
        CarModel cart = getOrCreateCart(userId);
        ProductModel product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Buscar si ya existe en el carrito
        Optional<CarItemModel> existingItem = cart.getItems().stream()
                .filter(item -> item.getProductModel().getId().equals(productId))
                .findFirst();

        if (existingItem.isPresent()) {
            existingItem.get().setAmount(existingItem.get().getAmount() + quantity);
        } else {
            CarItemModel newItem = new CarItemModel();
            newItem.setCarModel(cart);
            newItem.setProductModel(product);
            newItem.setAmount(quantity);
            cart.getItems().add(newItem);
        }

        return carRepository.save(cart);
    }

    // 3. Eliminar producto del carrito
    public CarModel removeProductFromCart(Long userId, Long productId) {
        CarModel cart = getOrCreateCart(userId);
        cart.getItems().removeIf(item -> item.getProductModel().getId().equals(productId));
        return carRepository.save(cart);
    }

    // 4. Pagar carrito y generar orden
    public OrderModel payCart(Long userId) {
        CarModel cart = getOrCreateCart(userId);

        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("El carrito está vacío");
        }

        cart.setPayed(true);
        carRepository.save(cart);

        // Crear orden
        OrderModel order = new OrderModel();
        order.setUserModel(cart.getUserModel());
        order.setOrderDate(LocalDateTime.now());
        order.setTotalPrice(cart.getTotal());
        orderRepository.save(order);

        // Crear items de la orden
       for (CarItemModel carItem : cart.getItems()) {
           OrderItemModel orderItem = new OrderItemModel();
           orderItem.setOrderModel(order);
           orderItem.setProductModel(carItem.getProductModel());
           orderItem.setAmount(carItem.getAmount());
           orderItem.setUnitPrice(
                   carItem.getProductModel().getPrice() != null
                   ? carItem.getProductModel().getPrice().doubleValue():0.0
           );
            ordenItemRepository.save(orderItem);
       }

        return order;
    }
}
