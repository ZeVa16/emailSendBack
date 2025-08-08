package com.example.sendEmail.repository;

import com.example.sendEmail.models.CarItemModel;
import com.example.sendEmail.models.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<CarModel, Long> {
}
