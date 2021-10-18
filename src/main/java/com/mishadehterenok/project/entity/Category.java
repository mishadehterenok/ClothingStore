package com.mishadehterenok.project.entity;

import com.mishadehterenok.project.dto.CategoryDto;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "categories")
public class Category extends BaseEntity{

    @Column(name = "category_name", nullable = false, length = 30, unique = true)
    private String name;

    @ManyToMany
    @JoinTable(name = "category_clothing",
    joinColumns = @JoinColumn(name = "category_id"),
    inverseJoinColumns = @JoinColumn(name = "clothing_id"))
    private List<Clothing> clothes;

    public void addClothing(Clothing clothing){
        clothes.add(clothing);
        clothing.getCategories().add(this);
    }
    public void removeClothing(Clothing clothing){
        clothes.remove(clothing);
        clothing.getCategories().remove(this);
    }

    public CategoryDto createDto(){
        return new CategoryDto(getName(), clothes.size());
    }
}
