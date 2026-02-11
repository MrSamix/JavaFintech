package org.example.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="tblCategories")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length=150, nullable = false)
    private String name;

    @Column(name="date_created", nullable = false)
    private LocalDateTime dateCreated;

    @OneToMany(mappedBy = "category")
    private List<ProductEntity> products;

    public CategoryEntity() {
        this.dateCreated = LocalDateTime.now();
    }

    public CategoryEntity(String name) {
        this(); // викликає конструктор по замовчуванню
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
}
