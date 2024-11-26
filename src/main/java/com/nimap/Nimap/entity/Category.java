package com.nimap.Nimap.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;
    private String categoryName;
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "category" , orphanRemoval = true)
    @JsonManagedReference
    private List<Product> productList = new ArrayList<>();

}
