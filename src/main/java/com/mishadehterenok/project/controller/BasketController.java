package com.mishadehterenok.project.controller;

import com.mishadehterenok.project.entity.Order;
import com.mishadehterenok.project.service.AccountService;
import com.mishadehterenok.project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BasketController {

    private final OrderService orderService;
    private AccountService accountService;

    @Autowired
    public BasketController(OrderService orderService, AccountService accountService) {
        this.orderService = orderService;
        this.accountService = accountService;
    }

    //формируем страницу корзины, добавляем в модель все заказы и считаем общую сумму
    @GetMapping("/basket")
    public ModelAndView showBasketPage(@SessionAttribute ("accountId") Long accountId,
                                       ModelAndView model){
        List<Order> allAccountOrders = orderService.findAllByAccountIdOrderByIdDesc(accountId);
        model.addObject("orders", allAccountOrders);
        double totalAmount = orderService.countByFinalPriceWhereAccount_Id(accountId);
        model.addObject("total", totalAmount);
        model.setViewName("basket");
        return model;
    }

    //удаление заказа по нажатию соответствующей кнопки
    @PostMapping("/deleteOrder/{id}")
    public String deleteOrder(@PathVariable("id") Long orderId,
                              @SessionAttribute("accountId") Long accountId){
        accountService.findById(accountId).removeOrder(orderService.findById(orderId));
        return "redirect:/basket";
    }

    //   оплата всех заказов
    // - снятие денег со счета
    // - изменение количества товаров в базе
    @PostMapping("/buyAll/{totalAmount}")
    public String buyAllClothingInBasket(@PathVariable("totalAmount") Double totalAmount,
                                         @SessionAttribute("accountId") Long accountId){
        List<Order> orders = orderService.findAllByAccountIdOrderByIdDesc(accountId);
        accountService.updateAccountAfterPurchaseOrders(accountId, totalAmount, orders);
        return "redirect:/basket";
    }

    //оплата одного заказа
    @PostMapping("/buyOne/{orderId}")
    public String buyOneClothingInBasket(@PathVariable("orderId") Long orderId,
                                         @SessionAttribute("accountId") Long accountId) {
        Order order = orderService.findById(orderId);
        accountService.updateAccountAfterPurchaseOneOrder(accountId, order);
        return "redirect:/basket";
    }
}
