package com.example.materias.serviceImpl;

import com.example.materias.model.Subject;
import com.example.materias.repository.SubjectRepository;
import com.example.materias.service.SubjectService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository repo;

    public SubjectServiceImpl(SubjectRepository repo) {
        this.repo = repo;
    }

    @Override
    public Subject create(Subject subject) {
        return repo.save(subject);
    }

    @Override
    public Subject update(Subject subject) {
        return repo.save(subject);
    }

    @Override
    public Optional<Subject> read(Long id) {
        return repo.findById(id);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Iterable<Subject> readAll() {
        return repo.findAll();
    }
}