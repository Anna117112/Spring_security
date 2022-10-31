package com.geecbrains.entities;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name = "autorites")
public class Autorites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
}
