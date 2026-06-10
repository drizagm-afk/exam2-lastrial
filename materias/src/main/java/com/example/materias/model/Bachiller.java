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
@Table(name = "bachillers")
public class Bachiller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bachiller")
    private Long id;

    @NotNull
    @Column(name = "bachiller", unique = true)
    private String name;
    @NotNull
    @Column(name = "section")
    private int section;
}
