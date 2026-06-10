package com.example.materias.serviceImpl;

import com.example.materias.model.Professor;
import com.example.materias.repository.ProfessorRepository;
import com.example.materias.service.ProfessorService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfessorServiceImpl implements ProfessorService {
    private final ProfessorRepository repo;

    public ProfessorServiceImpl(ProfessorRepository repo) {
        this.repo = repo;
    }

    @Override
    public Professor create(Professor professor) {
        return repo.save(professor);
    }

    @Override
    public Professor update(Professor professor) {
        return repo.save(professor);
    }

    @Override
    public Optional<Professor> read(Long id) {
        return repo.findById(id);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Iterable<Professor> readAll() {
        return repo.findAll();
    }
}