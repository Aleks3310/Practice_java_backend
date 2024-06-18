package com.example.Project.services;

import com.example.Project.entities.*;
import com.example.Project.exceptions.ResourceNotFoundException;
import com.example.Project.repositories.EquipmentRepository;
import com.example.Project.repositories.ModelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final ModelsRepository modelsRepository;

    @Autowired
    public EquipmentService(EquipmentRepository equipmentRepository, ModelsRepository modelsRepository) {
        this.equipmentRepository = equipmentRepository;
        this.modelsRepository = modelsRepository;
    }

    public List<Equipment> sortEquipment(String sortBy, String sortOrder) {
        return equipmentRepository.findAll(Sort.by(Sort.Direction.fromString(sortOrder), sortBy));
    }

    public List<Equipment> sortModelsByPrice(String sortOrder) {
        List<Equipment> allEquipment = equipmentRepository.findAll();

        // Sort models within each equipment by price
        allEquipment.forEach(equipment -> {
            List<Models> sortedModels = equipment.getModels().stream()
                    .sorted(Comparator.comparingDouble(Models::getPrice))
                    .collect(Collectors.toList());
            equipment.setModels(sortedModels);
        });

        // Sort equipment by the sum of the prices of their models
        Comparator<Equipment> comparator = Comparator.comparingDouble(equipment ->
                equipment.getModels().stream().mapToDouble(Models::getPrice).sum());

        if ("desc".equalsIgnoreCase(sortOrder)) {
            comparator = comparator.reversed();
        }

        return allEquipment.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }

    public Equipment getEquipmentById(Long id) {
        return equipmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Equipment not found"));
    }

    public List<Models> searchModelsByFilters(String equipmentType, String name, String color, Double minPrice, Double maxPrice) {
        return modelsRepository.findModelsByFilters(equipmentType, name, color, minPrice, maxPrice);
    }

    public Equipment createEquipment(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    // Редактирование существующей техники
    public Equipment updateEquipment(Long id, Equipment equipmentDetails) {
        Equipment equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipment not found"));

        equipment.setName(equipmentDetails.getName());
        equipment.setManufacturerCountry(equipmentDetails.getManufacturerCountry());
        equipment.setManufacturerFirm(equipmentDetails.getManufacturerFirm());
        equipment.setOrderOnline(equipmentDetails.isOrderOnline());
        equipment.setInstallmentAvailable(equipmentDetails.isInstallmentAvailable());

        return equipmentRepository.save(equipment);
    }

    // Добавление новой модели к существующей технике
    public Models addModelToEquipment(Long equipmentId, Models model) {
        Equipment equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Equipment not found"));

        model.setEquipment(equipment);
        return modelsRepository.save(model);
    }

    // Обновление существующих моделей по их ID
    public Equipment updateModels(Long equipmentId, List<Models> updatedModels) {
        Equipment equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Equipment not found"));

        List<Models> existingModels = equipment.getModels();

        for (Models updatedModel : updatedModels) {
            Models existingModel = existingModels.stream()
                    .filter(m -> Objects.equals(m.getId(), updatedModel.getId()))
                    .findFirst()
                    .orElse(null);

            if (existingModel != null) {
                updateModels(existingModel, updatedModel);
            } else {
                updatedModel.setEquipment(equipment);
                existingModels.add(updatedModel);
            }
        }

        return equipmentRepository.save(equipment);
    }



    private void updateModels(Models existingModel, Models updatedModel) {
        existingModel.setName(updatedModel.getName());
        existingModel.setSerialNumber(updatedModel.getSerialNumber());
        existingModel.setColor(updatedModel.getColor());
        existingModel.setSize(updatedModel.getSize());
        existingModel.setPrice(updatedModel.getPrice());
        existingModel.setInStock(updatedModel.isInStock());

        if (existingModel instanceof TvModels && updatedModel instanceof TvModels) {
            TvModels existingTvModel = (TvModels) existingModel;
            TvModels updatedTvModel = (TvModels) updatedModel;
            existingTvModel.setCategory(updatedTvModel.getCategory());
            existingTvModel.setTechnology(updatedTvModel.getTechnology());
        } else if (existingModel instanceof FridgeModels && updatedModel instanceof FridgeModels) {
            FridgeModels existingFridgeModel = (FridgeModels) existingModel;
            FridgeModels updatedFridgeModel = (FridgeModels) updatedModel;
            existingFridgeModel.setDoorCount(updatedFridgeModel.getDoorCount());
            existingFridgeModel.setCompressorType(updatedFridgeModel.getCompressorType());
        } else if (existingModel instanceof ComputerModels && updatedModel instanceof ComputerModels) {
            ComputerModels existingComputerModel = (ComputerModels) existingModel;
            ComputerModels updatedComputerModel = (ComputerModels) updatedModel;
            existingComputerModel.setCategory(updatedComputerModel.getCategory());
            existingComputerModel.setProcessorType(updatedComputerModel.getProcessorType());
        }   else if (existingModel instanceof VacuumModels && updatedModel instanceof VacuumModels) {
            VacuumModels existingFridgeModel = (VacuumModels) existingModel;
            VacuumModels updatedFridgeModel = (VacuumModels) updatedModel;
            existingFridgeModel.setDustCollectorVolume(updatedFridgeModel.getDustCollectorVolume());
            existingFridgeModel.setModeCount(updatedFridgeModel.getModeCount());
        } else if (existingModel instanceof SmartphoneModels && updatedModel instanceof SmartphoneModels) {
            SmartphoneModels existingComputerModel = (SmartphoneModels) existingModel;
            SmartphoneModels updatedComputerModel = (SmartphoneModels) updatedModel;
            existingComputerModel.setMemory(updatedComputerModel.getMemory());
            existingComputerModel.setCameraCount(updatedComputerModel.getCameraCount());
        }
    }


}
