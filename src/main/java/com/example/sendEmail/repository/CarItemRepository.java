package com.example.sendEmail.repository;

import com.example.sendEmail.models.CarItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarItemRepository extends JpaRepository<CarItemModel,Long> {
}
