package com.avalongroup.Inventory.repository;

import com.avalongroup.Inventory.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
    // Métodos personalizados opcionales
}