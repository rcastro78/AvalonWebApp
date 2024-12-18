package com.avalongroup.Inventory.service;

import com.avalongroup.Inventory.model.Brand;
import com.avalongroup.Inventory.repository.BrandRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public Page<Brand> getBrands(Pageable pageable) {
        return brandRepository.findAll(pageable);
    }

    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public Optional<Brand> getBrandById(Integer id) {
        return brandRepository.findById(id);
    }

    public Brand createBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    public Brand updateBrand(Integer id, Brand brandDetails) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        brand.setBrandName(brandDetails.getBrandName());
        brand.setBrandLogoUrl(brandDetails.getBrandLogoUrl());
        //brand.setStatus(brandDetails.getStatus());

        return brandRepository.save(brand);
    }

    public void deleteBrand(Integer id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found"));
        brandRepository.delete(brand);
    }
}
