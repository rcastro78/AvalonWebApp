package com.avalongroup.Inventory.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Distributors")
public class Distributor {

    public Distributor() {
    }

    public Distributor(Integer idDistributor, String distributorName, String distributorEmail,
                       String distributorPhone, String contact, Boolean status) {
        this.idDistributor = idDistributor;
        this.distributorName = distributorName;
        this.distributorEmail = distributorEmail;
        this.distributorPhone = distributorPhone;
        this.contact = contact;
        this.status = status;
    }

    public Distributor(Integer idDistributor, String distributorName, String distributorEmail,
                       String distributorPhone, String contact) {
        this.idDistributor = idDistributor;
        this.distributorName = distributorName;
        this.distributorEmail = distributorEmail;
        this.distributorPhone = distributorPhone;
        this.contact = contact;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDistributor")
    private Integer idDistributor;

    @Column(name = "distributorName", nullable = false, length = 100)
    private String distributorName;

    @Column(name = "distributorEmail", nullable = false, length = 100)
    private String distributorEmail;

    @Column(name = "distributorPhone", nullable = false, length = 20)
    private String distributorPhone;

    @Column(name = "contact", length = 100)
    private String contact;

    @Column(name = "createdAt", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updatedAt",  insertable = false, updatable = false,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    @Column(name = "status",  insertable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean status;

    // Getters and Setters

    public Integer getIdDistributor() {
        return idDistributor;
    }

    public void setIdDistributor(Integer idDistributor) {
        this.idDistributor = idDistributor;
    }

    public String getDistributorName() {
        return distributorName;
    }

    public void setDistributorName(String distributorName) {
        this.distributorName = distributorName;
    }

    public String getDistributorEmail() {
        return distributorEmail;
    }

    public void setDistributorEmail(String distributorEmail) {
        this.distributorEmail = distributorEmail;
    }

    public String getDistributorPhone() {
        return distributorPhone;
    }

    public void setDistributorPhone(String distributorPhone) {
        this.distributorPhone = distributorPhone;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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
}
