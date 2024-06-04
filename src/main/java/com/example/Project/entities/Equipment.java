package com.example.Project.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String manufacturerCountry;
    private String manufacturerFirm;
    private boolean orderOnline;
    private boolean installmentAvailable;

    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Models> models;

    public Equipment() {}

    public Equipment(String name, String manufacturerCountry, String manufacturerFirm, boolean orderOnline, boolean installmentAvailable) {
        this.name = name;
        this.manufacturerCountry = manufacturerCountry;
        this.manufacturerFirm = manufacturerFirm;
        this.orderOnline = orderOnline;
        this.installmentAvailable = installmentAvailable;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturerCountry() {
        return manufacturerCountry;
    }

    public void setManufacturerCountry(String manufacturerCountry) {
        this.manufacturerCountry = manufacturerCountry;
    }

    public String getManufacturerFirm() {
        return manufacturerFirm;
    }

    public void setManufacturerFirm(String manufacturerFirm) {
        this.manufacturerFirm = manufacturerFirm;
    }

    public boolean isOrderOnline() {
        return orderOnline;
    }

    public void setOrderOnline(boolean orderOnline) {
        this.orderOnline = orderOnline;
    }

    public boolean isInstallmentAvailable() {
        return installmentAvailable;
    }

    public void setInstallmentAvailable(boolean installmentAvailable) {
        this.installmentAvailable = installmentAvailable;
    }

    public List<Models> getModels() {
        return models;
    }

    public void setModels(List<Models> models) {
        this.models = models;
    }
}