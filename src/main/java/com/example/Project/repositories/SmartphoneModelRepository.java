package com.example.Project.repositories;

import com.example.Project.entities.SmartphoneModels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmartphoneModelRepository extends JpaRepository<SmartphoneModels, Long> {
}
