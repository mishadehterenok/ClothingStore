package com.mishadehterenok.project.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "clothes")
public class Clothing extends BaseEntity{

    @Column(nullable = false, length = 50, unique = true)
    private String name;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String description;

    @Column(name = "image", nullable = false, unique = true)
    private String imagePath;

    @ManyToMany(mappedBy = "clothes")
    private List<Category> categories = new ArrayList<>();

    @Column(name = "quantity_stock", nullable = false)
    private int quantityInStock;


    @ElementCollection(targetClass = Size.class)
    @JoinTable(name = "clothing_size", joinColumns = @JoinColumn(name = "clothing_id"))
    @Column(name = "size", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<Size> sizes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Transient
    public void addCategory(Category category){
        categories.add(category);
        category.getClothes().add(this);
    }

    @Transient
    public void removeCategory(Category category){
        categories.remove(category);
        category.getClothes().remove(this);
    }

    @Transient
    public void subtractQuantity(int n){
        this.quantityInStock -= n;
    }

}
