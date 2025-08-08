package com.example.sendEmail.models;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderDate;

    private Double totalPrice;


    @ManyToOne
    @JoinColumn(name = "userModel_id")
    private UserModel userModel;

    @OneToMany(mappedBy = "orderModel",cascade = CascadeType.ALL)
    private List<OrderItemModel> items;





}
