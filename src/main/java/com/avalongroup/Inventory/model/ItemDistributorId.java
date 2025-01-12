package com.avalongroup.Inventory.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ItemDistributorId implements Serializable {

    @Column(name = "idItem", nullable = false, length = 100)
    private String idItem;

    @Column(name = "idDistributor", nullable = false)
    private Integer idDistributor;

    public ItemDistributorId() {
    }

    public ItemDistributorId(String idItem, Integer idDistributor) {
        this.idItem = idItem;
        this.idDistributor = idDistributor;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemDistributorId that = (ItemDistributorId) o;
        return Objects.equals(idItem, that.idItem) &&
                Objects.equals(idDistributor, that.idDistributor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idItem, idDistributor);
    }
}
