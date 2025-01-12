package com.avalongroup.Inventory.service;

import com.avalongroup.Inventory.model.ItemDistributor;
import com.avalongroup.Inventory.model.ItemDistributorId;
import com.avalongroup.Inventory.repository.ItemDistributorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemDistributorService {

    @Autowired
    private ItemDistributorRepository repository;

    public ItemDistributor createItemDistributor(ItemDistributor itemDistributor) {
        return repository.save(itemDistributor);
    }

    public boolean existsByIdItemAndIdDistributor(String idItem, Integer idDistributor) {
        return repository.existsByIdItemAndIdDistributor(idItem, idDistributor);
    }

    public ItemDistributor getItemDistributor(String idItem, Integer idDistributor) {
        ItemDistributorId id = new ItemDistributorId();
        id.setIdItem(idItem);
        id.setIdDistributor(idDistributor);
        return repository.findById(id).orElse(null);
    }

    public void deleteItemDistributor(String idItem, Integer idDistributor) {
        ItemDistributorId id = new ItemDistributorId();
        id.setIdItem(idItem);
        id.setIdDistributor(idDistributor);
        repository.deleteById(id);
    }

    // Otros métodos de negocio según sea necesario
}