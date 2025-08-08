package com.example.sendEmail.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer amount;

    private Double unitPrice;

    @ManyToOne
    @JoinColumn(name = "orderModel_id")
    private OrderModel orderModel;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductModel productModel;

}
