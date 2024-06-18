package com.example.Project.repositories;

import com.example.Project.entities.Models;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelsRepository extends JpaRepository<Models, Long> {

    @Query("SELECT m FROM Models m " +
            "WHERE (:equipmentType IS NULL OR m.equipment.name = :equipmentType) " +
            "AND (:name IS NULL OR :name = '' OR LOWER(m.name) LIKE LOWER(CONCAT('%', :name, '%'))) " +
            "AND (:color IS NULL OR :color = '' OR LOWER(m.color) = LOWER(:color)) " +
            "AND (:minPrice IS NULL OR m.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR m.price <= :maxPrice)")
    List<Models> findModelsByFilters(@Param("equipmentType") String equipmentType,
                                     @Param("name") String name,
                                     @Param("color") String color,
                                     @Param("minPrice") Double minPrice,
                                     @Param("maxPrice") Double maxPrice);
}
