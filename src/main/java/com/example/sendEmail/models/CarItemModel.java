package com.example.sendEmail.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "carrito_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "carrito_id")
    private CarModel carModel;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductModel productModel;

    private int amount;

    public double getSubtotal() {
        return productModel.getPrice() * amount;
    }

}
