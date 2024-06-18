package com.example.Project.controllers;

import com.example.Project.entities.Equipment;
import com.example.Project.entities.Models;
import com.example.Project.services.EquipmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/equipment")
@Tag(name = "Equipment API", description = "API для управления техникой")
public class EquipmentController {

    private final EquipmentService equipmentService;

    @Autowired
    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }
    @Operation(summary = "Получить все виды техники")
    @GetMapping
    public List<Equipment> getAllEquipment() {
        return equipmentService.getAllEquipment();
    }

    @Operation(summary = "Получить технику по ID")
    @GetMapping("/{id}")
    public Equipment getEquipmentById(@PathVariable Long id) {
        return equipmentService.getEquipmentById(id);
    }

    @Operation(summary = "Сортировка техники(по имени)")
    @GetMapping("/sort")
    public ResponseEntity<List<Equipment>> sortEquipment(
            @RequestParam(required = false, defaultValue = "name") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortOrder) {

        List<Equipment> sortedEquipment = equipmentService.sortEquipment(sortBy, sortOrder);
        return ResponseEntity.ok(sortedEquipment);
    }
    @Operation(summary = "Сортировка техники(по цене)")
    @GetMapping("/sortModelsByPrice")
    public ResponseEntity<List<Equipment>> sortModelsByPrice(
            @RequestParam(required = false, defaultValue = "asc") String sortOrder) {

        List<Equipment> sortedEquipment = equipmentService.sortModelsByPrice(sortOrder);
        return ResponseEntity.ok(sortedEquipment);
    }


    @Operation(summary = "Поиск моделей по фильтрам")
    @GetMapping("/search")
    public ResponseEntity<List<Models>> searchModels(
            @RequestParam(required = false) String equipmentType,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {

        List<Models> filteredModels = equipmentService.searchModelsByFilters(equipmentType, name, color, minPrice, maxPrice);
        return ResponseEntity.ok(filteredModels);
    }

    @Operation(summary = "Создать новую технику")
    @PostMapping
    public ResponseEntity<Equipment> createEquipment(@RequestBody Equipment equipment) {
        Equipment createdEquipment = equipmentService.createEquipment(equipment);
        return ResponseEntity.ok(createdEquipment);
    }

    @Operation(summary = "Редактирование существующей техники")
    @PutMapping("/{id}")
    public ResponseEntity<Equipment> updateEquipment(@PathVariable Long id, @RequestBody Equipment equipmentDetails) {
        Equipment updatedEquipment = equipmentService.updateEquipment(id, equipmentDetails);
        return ResponseEntity.ok(updatedEquipment);
    }

    @Operation(summary = "Добавление новой модели к существующей технике")
    @PostMapping("/{equipmentId}/models")
    public ResponseEntity<Models> addModelToEquipment(@PathVariable Long equipmentId, @RequestBody Models model) {
        Models createdModel = equipmentService.addModelToEquipment(equipmentId, model);
        return ResponseEntity.ok(createdModel);
    }

    @Operation(summary = "Обновление существующих моделей по их ID")
    @PutMapping("/{equipmentId}/models")
    public ResponseEntity<Equipment> updateModels(@PathVariable Long equipmentId, @RequestBody List<Models> updatedModels) {
        Equipment updatedEquipment = equipmentService.updateModels(equipmentId, updatedModels);
        return ResponseEntity.ok(updatedEquipment);
    }

}
