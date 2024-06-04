package com.example.Project.entities;

import javax.persistence.Entity;

@Entity
public class VacuumModels extends Models {

    private double dustCollectorVolume;
    private int modeCount;

    public VacuumModels() {}

    public VacuumModels(String name, String serialNumber, String color, String size, double price,
                        boolean inStock, Equipment equipment, double dustCollectorVolume, int modeCount) {
        super(name, serialNumber, color, size, price, inStock, equipment);
        this.dustCollectorVolume = dustCollectorVolume;
        this.modeCount = modeCount;
    }

    public double getDustCollectorVolume() {
        return dustCollectorVolume;
    }

    public void setDustCollectorVolume(double dustCollectorVolume){
        this.dustCollectorVolume = dustCollectorVolume;
    }
    public int getModeCount() {
        return modeCount;
    }
    public void setModeCount(int modeCount){
        this.modeCount = modeCount;
    }
}