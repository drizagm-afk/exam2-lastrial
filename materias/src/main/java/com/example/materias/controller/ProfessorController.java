package com.example.materias.controller;

import com.example.materias.dto.ApiResponse;
import com.example.materias.model.Professor;
import com.example.materias.service.ProfessorService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profs")
public class ProfessorController {
    private final ProfessorService serv;

    public ProfessorController(ProfessorService serv) {
        this.serv = serv;
    }

    @GetMapping
    public String index() {
        return "profs/index";
    }

    @GetMapping("/readAll")
    public ResponseEntity<ApiResponse> readAll() {
        return ResponseEntity.ok(
                ApiResponse.ok("Profesores: ", serv.readAll())
        );
    }

    @GetMapping("/form/create")
    public String formCreate(Model model) {
        return buildForm(model, new Professor());
    }
    @GetMapping("/form/update/{id}")
    public String formUpdate(@PathVariable Long id, Model model) {
        return buildForm(model, serv.read(id).get());
    }
    public String buildForm(Model model, Professor professor) {
        model.addAttribute("professor", professor);
        return "profs/form :: form";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Professor professor) {
        serv.create(professor);
        return "redirect:/profs";
    }
    @PostMapping("/update")
    public String update(@ModelAttribute Professor professor) {
        serv.update(professor);
        return "redirect:/profs";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        serv.delete(id);
        return ResponseEntity.noContent().build();
    }
}
