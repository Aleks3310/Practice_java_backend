package com.example.Project.repositories;

import com.example.Project.entities.FridgeModels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FridgeModelRepository extends JpaRepository<FridgeModels, Long> {
}
