package com.example.Project.repositories;

import com.example.Project.entities.TvModels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TVModelRepository extends JpaRepository<TvModels, Long> {
}
