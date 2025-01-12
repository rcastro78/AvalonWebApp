package com.avalongroup.Inventory.repository;

import com.avalongroup.Inventory.model.ItemDistributor;
import com.avalongroup.Inventory.model.ItemDistributorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDistributorRepository
        extends JpaRepository<ItemDistributor, ItemDistributorId> {

    boolean existsByIdItemAndIdDistributor(String idItem, Integer idDistributor);
}
