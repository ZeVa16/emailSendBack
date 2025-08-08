package com.example.sendEmail.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carrito")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean payed = false;

    @OneToOne
    @JoinColumn(name = "userModel_id")
    private UserModel userModel;

    @OneToMany(mappedBy = "carModel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarItemModel> items = new ArrayList<>();


    public double getTotal() {
        return items.stream()
                .mapToDouble(CarItemModel::getSubtotal)
                .sum();

}
}
