package com.example.materias.serviceImpl;

import com.example.materias.model.Bachiller;
import com.example.materias.repository.BachillerRepository;
import com.example.materias.service.BachillerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BachillerServiceImpl implements BachillerService {
    private final BachillerRepository repo;

    public BachillerServiceImpl(BachillerRepository repo) {
        this.repo = repo;
    }

    @Override
    public Bachiller create(Bachiller bachiller) {
        return repo.save(bachiller);
    }

    @Override
    public Bachiller update(Bachiller bachiller) {
        return repo.save(bachiller);
    }

    @Override
    public Optional<Bachiller> read(Long id) {
        return repo.findById(id);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Iterable<Bachiller> readAll() {
        return repo.findAll();
    }
}