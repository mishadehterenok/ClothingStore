package com.mishadehterenok.project.entity;

import com.mishadehterenok.project.dto.BrandDto;
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
@Table(name = "brands")
public class Brand extends BaseEntity{

    @Column(name = "brand_name", nullable = false, length = 40, unique = true)
    private String name;

    @OneToMany(mappedBy = "brand", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Clothing> clothes = new ArrayList<>();

    public void addClothing(Clothing clothing){
        clothes.add(clothing);
        clothing.setBrand(this);
    }
    public void removeClothing(Clothing clothing){
        clothes.remove(clothing);
    }

    public BrandDto createDto(){
        return new BrandDto(getName(), getClothes().size());
    }
}
