package com.example.Project.entities;

import javax.persistence.Entity;

@Entity
public class FridgeModels extends Models {
    private int doorCount;
    private String compressorType;

    public FridgeModels() {}

    public FridgeModels(String name, String serialNumber, String color, String size, double price,
                        boolean inStock, Equipment equipment, int doorCount, String compressorType) {
        super(name, serialNumber, color, size, price, inStock, equipment);
        this.doorCount = doorCount;
        this.compressorType = compressorType;
    }

    public int getDoorCount() {
        return doorCount;
    }
    public void setDoorCount(int doorCount) {
        this.doorCount = doorCount;
    }
    public String getCompressorType() {
        return compressorType;
    }
    public void setCompressorType(String compressorType) {
        this.compressorType = compressorType;
    }
}
