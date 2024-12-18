package com.avalongroup.Inventory.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Product")
public class Product {

    @Id
    @Column(name = "idItem", nullable = false, length = 100)
    private String idItem;

    @Column(name = "idBrand", nullable = false, columnDefinition = "INT")
    private Integer idBrand; // Representa la relación con la tabla Brand

    @Column(name = "idUnit", nullable = false, columnDefinition = "INT")
    private Integer idUnit; // Representa la relación con la tabla Unit

    @Column(name = "itemName", nullable = false, length = 100)
    private String itemName;

    @Column(name = "qty", nullable = false, length = 100)
    private String qty;

    @Column(name = "createdAt", insertable = false, updatable = false,  columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updatedAt", insertable = false, updatable = false,  columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    @Column(name = "status", insertable = false, updatable = false,  columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean status;

    @Column(name = "vegan", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer vegan;

    @Column(name = "plant_based", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer plantBased;

    @Column(name = "upc", length = 45)
    private String upc;

    // Getters y Setters

    public String getIdItem() {
        return idItem;
    }

    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }

    public Integer getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(Integer idBrand) {
        this.idBrand = idBrand;
    }

    public Integer getUnit() {
        return idUnit;
    }

    public void setIdUnit(Integer idUnit) {
        this.idUnit = idUnit;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getVegan() {
        return vegan;
    }

    public void setVegan(Integer vegan) {
        this.vegan = vegan;
    }

    public Integer getPlantBased() {
        return plantBased;
    }

    public void setPlantBased(Integer plantBased) {
        this.plantBased = plantBased;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }
}

