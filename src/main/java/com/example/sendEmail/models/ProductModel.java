package com.example.sendEmail.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "productos")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long image;
    private String name;
    private Float price;

}
