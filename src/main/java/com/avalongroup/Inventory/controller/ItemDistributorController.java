package com.avalongroup.Inventory.controller;

import com.avalongroup.Inventory.model.Brand;
import com.avalongroup.Inventory.model.ItemDistributor;
import com.avalongroup.Inventory.model.ItemDistributorId;
import com.avalongroup.Inventory.service.ItemDistributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/item-distributors")
public class ItemDistributorController {

    @Autowired
    private ItemDistributorService service;

    @PostMapping("/create")
    public String create(@ModelAttribute ItemDistributor itemDistributor) {
        if (!service.existsByIdItemAndIdDistributor(itemDistributor.getIdItem(), itemDistributor.getIdDistributor())) {
            service.createItemDistributor(itemDistributor);
        }
        return "redirect:/api/product-distributor-brands/product/" + itemDistributor.getIdItem() + "/distributors";
    }

    @GetMapping("/{idItem}/{idDistributor}")
    public ResponseEntity<ItemDistributor> get(@PathVariable String idItem, @PathVariable Integer idDistributor) {
        ItemDistributor itemDistributor = service.getItemDistributor(idItem, idDistributor);
        return itemDistributor != null ? ResponseEntity.ok(itemDistributor) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idItem}/{idDistributor}")
    public ResponseEntity<Void> delete(@PathVariable String idItem, @PathVariable Integer idDistributor) {
        service.deleteItemDistributor(idItem, idDistributor);
        return ResponseEntity.noContent().build();
    }

    // Otros métodos según sea necesario
}