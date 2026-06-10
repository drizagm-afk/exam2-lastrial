package com.example.materias.controller;

import com.example.materias.dto.ApiResponse;
import com.example.materias.model.Bachiller;
import com.example.materias.service.BachillerService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bachillers")
public class BachillerController {
    private final BachillerService serv;

    public BachillerController(BachillerService serv) {
        this.serv = serv;
    }

    @GetMapping
    public String index() {
        return "bachillers/index";
    }

    @GetMapping("/readAll")
    public ResponseEntity<ApiResponse> readAll() {
        return ResponseEntity.ok(
                ApiResponse.ok("Bachilleratos: ", serv.readAll())
        );
    }

    @GetMapping("/form/create")
    public String formCreate(Model model) {
        return buildForm(model, new Bachiller());
    }
    @GetMapping("/form/update/{id}")
    public String formUpdate(@PathVariable Long id, Model model) {
        return buildForm(model, serv.read(id).get());
    }
    public String buildForm(Model model, Bachiller bachiller) {
        model.addAttribute("bachiller", bachiller);
        return "bachillers/form :: form";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Bachiller bachiller) {
        serv.create(bachiller);
        return "redirect:/bachillers";
    }
    @PostMapping("/update")
    public String update(@ModelAttribute Bachiller bachiller) {
        serv.update(bachiller);
        return "redirect:/bachillers";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        serv.delete(id);
        return ResponseEntity.noContent().build();
    }
}
