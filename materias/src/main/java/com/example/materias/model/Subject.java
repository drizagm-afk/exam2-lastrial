package com.example.materias.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_subject")
    private Long id;

    @NotNull
    @Column(name = "subject", unique = true)
    private String name;
    @NotNull
    @ManyToOne
    @JoinColumn(name="id_bachiller")
    private Bachiller bachiller;
    @NotNull
    @ManyToOne
    @JoinColumn(name="id_professor")
    private Professor professor;
}
