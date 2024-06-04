package com.example.Project.entities;

import javax.persistence.Entity;

@Entity
public class SmartphoneModels extends Models {
    private int memory;
    private int cameraCount;

    public SmartphoneModels() {}

    public SmartphoneModels(String name, String serialNumber, String color, String size, double price,
                            boolean inStock, Equipment equipment, int memory, int cameraCount) {
        super(name, serialNumber, color, size, price, inStock, equipment);
        this.memory = memory;
        this.cameraCount = cameraCount;
    }

    public int getMemory() {
        return memory;
    }
    public void setMemory(int memory) {
        this.memory = memory;
    }
    public int getCameraCount() {
        return cameraCount;
    }
    public void setCameraCount(int cameraCount) {
        this.cameraCount = cameraCount;
    }
}
