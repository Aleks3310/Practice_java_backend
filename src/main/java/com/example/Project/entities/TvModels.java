package com.example.Project.entities;

import javax.persistence.Entity;

@Entity
public class TvModels extends Models {

    private String category;
    private String technology;

    public TvModels() {}

    public TvModels(String name, String serialNumber, String color, String size, double price,
                    boolean inStock, Equipment equipment, String category, String technology) {
        super(name, serialNumber, color, size, price, inStock, equipment);
        this.category = category;
        this.technology = technology;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }
}