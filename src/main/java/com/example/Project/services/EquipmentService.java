package com.example.Project.services;

import com.example.Project.entities.Equipment;
import com.example.Project.exceptions.ResourceNotFoundException;
import com.example.Project.repositories.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }

    public Equipment getEquipmentById(Long id) {
        return equipmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Equipment not found"));
    }

    public Equipment createEquipment(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    public Equipment updateEquipment(Long id, Equipment equipmentDetails) {
        Equipment equipment = getEquipmentById(id);
        equipment.setName(equipmentDetails.getName());
        equipment.setManufacturerCountry(equipmentDetails.getManufacturerCountry());
        equipment.setManufacturerFirm(equipmentDetails.getManufacturerFirm());
        equipment.setOrderOnline(equipmentDetails.isOrderOnline());
        equipment.setInstallmentAvailable(equipmentDetails.isInstallmentAvailable());
        return equipmentRepository.save(equipment);
    }

    public void deleteEquipment(Long id) {
        equipmentRepository.deleteById(id);
    }
}
