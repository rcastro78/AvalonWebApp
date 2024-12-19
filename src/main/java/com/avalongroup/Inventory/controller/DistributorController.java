package com.avalongroup.Inventory.controller;

import com.avalongroup.Inventory.model.Distributor;
import com.avalongroup.Inventory.service.DistributorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/distributors")
public class DistributorController {

    private final DistributorService distributorService;

    public DistributorController(DistributorService distributorService) {
        this.distributorService = distributorService;
    }

    @GetMapping
    public String getDistributors(Model model, @RequestParam(defaultValue = "0") int page) {
        // Obtén los distribuidores con paginación
        Page<Distributor> distributorPage = distributorService.getDistributors(PageRequest.of(page, 10));

        model.addAttribute("distributorPage", distributorPage); // El objeto con paginación
        model.addAttribute("currentPage", page);                // Página actual
        model.addAttribute("totalPages", distributorPage.getTotalPages()); // Total de páginas

        return "distributors"; // Nombre de la vista Thymeleaf
    }

    @GetMapping("/")
    public List<Distributor> getAllDistributors() {
        return distributorService.getAllDistributors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Distributor> getDistributorById(@PathVariable Integer id) {
        return distributorService.getDistributorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public String createDistributor(@ModelAttribute Distributor distributor,
                                    @RequestParam(defaultValue = "0") int page) {
        distributorService.createDistributor(distributor);
        return "redirect:/api/distributors?page=" + page;
    }

    @PostMapping("/update")
    public String updateDistributor(@RequestParam("idDistributor") Integer id,
                                    @RequestParam("distributorName") String distributorName,
                                    @RequestParam("distributorEmail") String distributorEmail,
                                    @RequestParam("distributorPhone") String distributorPhone,
                                    @RequestParam("contact") String contact,
                                    @RequestParam("status") Boolean status,
                                    @RequestParam(defaultValue = "0") int page) {

        // Llamada al servicio para actualizar el distribuidor
        Distributor updatedDistributor = new Distributor(id, distributorName, distributorEmail, distributorPhone, contact, status);
        distributorService.updateDistributor(id, updatedDistributor);

        // Redirigir a la misma página para cargar los distribuidores actualizados
        return "redirect:/api/distributors?page=" + page;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDistributor(@PathVariable Integer id) {
        distributorService.deleteDistributor(id);
        return ResponseEntity.noContent().build();
    }
}
