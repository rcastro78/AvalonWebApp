package com.avalongroup.Inventory.service;

import com.avalongroup.Inventory.model.Distributor;
import com.avalongroup.Inventory.repository.DistributorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.domain.Specification;
import java.util.List;
import java.util.Optional;

@Service
public class DistributorService {

    private final DistributorRepository distributorRepository;

    public DistributorService(DistributorRepository distributorRepository) {
        this.distributorRepository = distributorRepository;
    }

    public Page<Distributor> getDistributors(Pageable pageable) {
        Specification<Distributor> spec = (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("status"), true);

        return distributorRepository.findAll(spec,pageable);
    }

    public List<Distributor> getAllDistributors() {
        return distributorRepository.findAll();
    }

    public Optional<Distributor> getDistributorById(Integer id) {
        return distributorRepository.findById(id);
    }

    public Distributor createDistributor(Distributor distributor) {
        return distributorRepository.save(distributor);
    }

    public Distributor updateDistributor(Integer id, Distributor distributorDetails) {
        Distributor distributor = distributorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Distributor not found"));

        distributor.setDistributorName(distributorDetails.getDistributorName());
        distributor.setDistributorEmail(distributorDetails.getDistributorEmail());
        distributor.setDistributorPhone(distributorDetails.getDistributorPhone());
        distributor.setContact(distributorDetails.getContact());
        distributor.setStatus(distributorDetails.getStatus());
        distributor.setDistributorLogoUrl(distributor.getDistributorLogoUrl());

        return distributorRepository.save(distributor);
    }

    public void deleteDistributor(Integer id) {
        Distributor distributor = distributorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Distributor not found"));
        distributorRepository.delete(distributor);
    }
}

