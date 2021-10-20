package com.mishadehterenok.project.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "accounts")
public class Account extends BaseEntity{

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "last_name",nullable = false, length = 30)
    private String lastName;

    @Transient
    private String fullName = firstName + " " + lastName;

    @Embedded
    private Address address;

    @Column(name = "account_amount", nullable = false)
    private double accountAmount;

    @Column(name = "phone_number", nullable = false, length = 30)
    private String phoneNumber;

    @Column(nullable = false, length = 40, unique = true)
    private String email;

    @Column(nullable = false, length = 50, unique = true)
    private String login;

    @Column(nullable = false, length = 50)
    private String password;

    @OneToMany(mappedBy = "account", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order){
        orders.add(order);
        order.setAccount(this);
    }
    public void removeOrder(Order order){
        orders.remove(order);
    }

    public void removeAllOrders(List<Order> orders){
        orders.removeAll(orders);
    }

    public void subtractAmount(Double amount){
        this.accountAmount -= amount;
    }

}
