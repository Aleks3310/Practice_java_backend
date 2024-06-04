package com.example.Project.entities;

import javax.persistence.Entity;

@Entity
public class ComputerModels extends Models {
    private String category;
    private String processorType;

    public ComputerModels() {}

    public ComputerModels(String name, String serialNumber, String color, String size, double price,
                         boolean inStock, Equipment equipment, String category, String processorType) {
        super(name, serialNumber, color, size, price, inStock, equipment);
        this.category = category;
        this.processorType = processorType;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getProcessorType() {
        return processorType;
    }
    public void setProcessorType(String processorType) {
        this.processorType = processorType;
    }
}
