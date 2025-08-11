package com.example.sendEmail.models;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name= "email",unique = true)
    private String email;
    private String password;
    private Integer points;
    @OneToOne(mappedBy = "userModel", cascade = CascadeType.ALL)
    private CarModel car;
    @OneToMany(mappedBy = "userModel")
    private List<OrderModel> orders;

}
