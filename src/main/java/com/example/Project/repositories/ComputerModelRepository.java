package com.example.Project.repositories;

import com.example.Project.entities.ComputerModels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputerModelRepository extends JpaRepository<ComputerModels, Long> {
}