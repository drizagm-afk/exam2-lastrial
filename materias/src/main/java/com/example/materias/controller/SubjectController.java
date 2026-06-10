package com.example.materias.controller;

import com.example.materias.dto.ApiResponse;
import com.example.materias.model.Subject;
import com.example.materias.service.BachillerService;
import com.example.materias.service.ProfessorService;
import com.example.materias.service.SubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/subjects")
public class SubjectController {
    private final SubjectService serv;
    private final BachillerService servBachiller;
    private final ProfessorService servProfessor;

    public SubjectController(SubjectService serv, BachillerService servBachiller, ProfessorService servProfessor) {
        this.serv = serv;
        this.servBachiller = servBachiller;
        this.servProfessor = servProfessor;
    }

    @GetMapping
    public String index() {
        return "subjects/index";
    }

    @GetMapping("/readAll")
    public ResponseEntity<ApiResponse> readAll() {
        return ResponseEntity.ok(
                ApiResponse.ok("Subjectatos: ", serv.readAll())
        );
    }

    @GetMapping("/form/create")
    public String formCreate(Model model) {
        return buildForm(model, new Subject());
    }
    @GetMapping("/form/update/{id}")
    public String formUpdate(@PathVariable Long id, Model model) {
        return buildForm(model, serv.read(id).get());
    }
    public String buildForm(Model model, Subject subject) {
        model.addAttribute("subject", subject);
        model.addAttribute("professors", servProfessor.readAll());
        model.addAttribute("bachillers", servBachiller.readAll());
        return "subjects/form :: form";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Subject subject) {
        serv.create(subject);
        return "redirect:/subjects";
    }
    @PostMapping("/update")
    public String update(@ModelAttribute Subject subject) {
        serv.update(subject);
        return "redirect:/subjects";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        serv.delete(id);
        return ResponseEntity.noContent().build();
    }
}
