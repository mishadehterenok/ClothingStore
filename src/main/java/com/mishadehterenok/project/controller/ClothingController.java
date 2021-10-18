package com.mishadehterenok.project.controller;

import com.mishadehterenok.project.entity.Clothing;
import com.mishadehterenok.project.entity.Order;
import com.mishadehterenok.project.service.AccountService;
import com.mishadehterenok.project.service.ClothingService;
import com.mishadehterenok.project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ClothingController {

    private final ClothingService clothingService;
    private final OrderService orderService;
    private final AccountService accountService;

    @Autowired
    public ClothingController(ClothingService clothingService, OrderService orderService, AccountService accountService) {
        this.clothingService = clothingService;
        this.orderService = orderService;
        this.accountService = accountService;
    }

    //название одежды - это будет ссылка на его личную страницу,
    //на странице одежды будет форма для заполнения заказа(будут скрытые поля некоторые)
    @GetMapping("/clothing/{id}")
    public String showClothingPage(@PathVariable("id") Long id,
                                   @SessionAttribute("accountId") Long accountId,
                                   Model model){
        Clothing clothing = clothingService.findById(id);
        model.addAttribute("clothing", clothing);
        Order order = new Order();
        order.setAccount(accountService.findById(accountId));
        order.setClothing(clothing);
        model.addAttribute("order", order);
        return "clothing";
    }

    //после заполнения формы идет сохранение нового заказа(или обновление существующего)
    // и редирект на страницу корзины
    @PostMapping("/order/save")
    public String saveOrder(@ModelAttribute("order") Order order,
                            @SessionAttribute("accountId") Long accountId){
        orderService.saveOrUpdate(order, accountId);
        return "redirect:/basket";
    }


}
