package com.avalongroup.Inventory.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vw_product_distributor_brand") // Nombre de la vista en la base de datos
public class ProductDistributorBrand {

    @Id
    @Column(name = "distributorItemCode", nullable = false, length = 100)
    private String distributorItemCode; // Usado como identificador único (puedes cambiarlo si hay otra clave primaria)

    @Column(name = "MFG", length = 100)
    private String mfg;

    @Column(name = "itemName", length = 100)
    private String itemName;

    @Column(name = "unitName", length = 100)
    private String unitName;

    @Column(name = "qty", length = 100)
    private String qty;

    @Column(name = "idBrand", nullable = false)
    private Integer idBrand;

    @Column(name = "brandName", length = 100)
    private String brandName;

    @Column(name = "idDistributor", nullable = false)
    private Integer idDistributor;

    @Column(name = "distributorName", length = 100)
    private String distributorName;

    @Column(name = "distributorEmail", length = 100)
    private String distributorEmail;

    // Getters y Setters
    public String getDistributorItemCode() {
        return distributorItemCode;
    }

    public void setDistributorItemCode(String distributorItemCode) {
        this.distributorItemCode = distributorItemCode;
    }

    public String getMfg() {
        return mfg;
    }

    public void setMfg(String mfg) {
        this.mfg = mfg;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public Integer getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(Integer idBrand) {
        this.idBrand = idBrand;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

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
}

