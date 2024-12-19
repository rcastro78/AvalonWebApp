package com.avalongroup.Inventory.controller;
import com.avalongroup.Inventory.model.Unit;
import com.avalongroup.Inventory.service.UnitService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/units")
public class UnitController {

    private final UnitService unitService;

    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    // Obtener unidades con paginación para mostrar en una vista Thymeleaf
    @GetMapping
    public String getUnits(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Unit> unitPage = unitService.getUnits(PageRequest.of(page, 10));

        model.addAttribute("unitPage", unitPage); // Datos de la página actual
        model.addAttribute("currentPage", page);  // Página actual
        model.addAttribute("totalPages", unitPage.getTotalPages()); // Total de páginas

        return "units"; // Nombre de la vista Thymeleaf
    }

    // Devolver todas las unidades en formato JSON para un DropdownList
    @GetMapping("/dropdown")
    @ResponseBody
    public List<Unit> getAllUnitsForDropdown() {
        return unitService.getAllUnits();
    }

    // Obtener una unidad por ID
    @GetMapping("/{id}")
    public ResponseEntity<Unit> getUnitById(@PathVariable Integer id) {
        Optional<Unit> unit = unitService.getUnitById(id);
        return unit.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Crear una nueva unidad
    @PostMapping("/create")
    public String createUnit(@ModelAttribute Unit unit, @RequestParam(defaultValue = "0") int page) {
        unitService.createUnit(unit);
        return "redirect:/api/units?page=" + page;
    }

    // Actualizar una unidad existente
    @PostMapping("/update")
    public String updateUnit(@RequestParam("idUnit") Integer id,
                             @RequestParam("unitName") String unitName,
                             @RequestParam(defaultValue = "0") int page) {

        Unit updatedUnit = new Unit();
        updatedUnit.setIdUnit(id);
        updatedUnit.setUnitName(unitName);

        unitService.updateUnit(id, updatedUnit);
        return "redirect:/api/units?page=" + page;
    }

    // Eliminar una unidad por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUnit(@PathVariable Integer id) {
        unitService.deleteUnit(id);
        return ResponseEntity.noContent().build();
    }
}
