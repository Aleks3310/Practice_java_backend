package com.example.Project.repositories;

import com.example.Project.entities.VacuumModels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacuumModelRepository extends JpaRepository<VacuumModels, Long> {
}
