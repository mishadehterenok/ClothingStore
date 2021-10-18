package com.mishadehterenok.project.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "orders")
public class Order extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "clothing_id")
    private Clothing clothing;

    @Column(nullable = false)
    private int quantity;

    @Column(name = "final_price", nullable = false)
    private double finalPrice = quantity*clothing.getPrice();

}
