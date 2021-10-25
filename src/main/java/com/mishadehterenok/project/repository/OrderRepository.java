package com.mishadehterenok.project.repository;

import com.mishadehterenok.project.entity.Order;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findAllByAccount_IdOrderByIdDesc(Long id);

    @Query("select count(o.finalPrice) from Order o where o.account.id=?1")
    Double countByFinalPriceWhereAccount_Id(Long id);

    boolean existsByClothing_NameAndAccount_Id(String clothingName, Long accountId);

    @Modifying
    @Query("update Order o set o.quantity = o.quantity + ?1 where o.account.id = ?2 and o.clothing.name = ?3")
    void updateExistOrder(int quantity, Long accountId, String clothingName);


}
