package com.mishadehterenok.project.service;

import com.mishadehterenok.project.entity.Order;
import com.mishadehterenok.project.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order findById(Long id){
        return orderRepository.findById(id).get();
    }

    public List<Order> findAllByAccountIdOrderByIdDesc(Long id){
        return orderRepository.findAllByAccount_IdOrderByIdDesc(id);
    }

    //считает общую сумму всех заказов в корзине
    public double countByFinalPriceWhereAccount_Id(Long id){
        return orderRepository.countByFinalPriceWhereAccount_Id(id);
    }

    //если заказ существует - обновляет воличесто и итоговую цену
    public void saveOrUpdate(Order order, Long accountId){
        if (orderRepository.existsByClothing_NameAndAccount_Id(order.getClothing().getName(), accountId)) {
            orderRepository.updateExistOrder(order.getQuantity(), accountId, order.getClothing().getName());
        } else {
            orderRepository.save(order);
        }
    }



}
