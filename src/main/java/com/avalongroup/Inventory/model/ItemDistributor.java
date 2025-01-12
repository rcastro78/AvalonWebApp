package com.avalongroup.Inventory.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "ItemDistributor")
public class ItemDistributor {


    @Id
    @Column(name = "idItemDistributor")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idItemDistributor;


    @Column(name = "idItem", nullable = false, length = 45)
    private String idItem;

    @Column(name = "idDistributor", nullable = false)
    private Integer idDistributor;

    @Column(name = "distributorItemCode", nullable = false, length = 45)
    private String distributorItemCode;

    @Column(name = "createdAt", updatable = false, insertable = false)
    private LocalDateTime createdAt;

    @Column(name = "updatedAt", insertable = false)
    private LocalDateTime updatedAt;

    @Column(name = "status", insertable = false)
    private boolean status = true;

    // Getters and setters

    public Integer getIdItemDistributor() {
        return idItemDistributor;
    }

    public void setIdItemDistributor(Integer idItemDistributor) {
        this.idItemDistributor = idItemDistributor;
    }


    public String getIdItem() {
        return idItem;
    }

    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }

    public Integer getIdDistributor() {
        return idDistributor;
    }

    public void setIdDistributor(Integer idDistributor) {
        this.idDistributor = idDistributor;
    }


    public String getDistributorItemCode() {
        return distributorItemCode;
    }

    public void setDistributorItemCode(String distributorItemCode) {
        this.distributorItemCode = distributorItemCode;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

