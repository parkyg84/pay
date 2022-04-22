package com.example.pay.controller;

import com.example.pay.service.IPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController
public class PayController {

    @Autowired
    private IPayService payService;

    @RequestMapping(value = "/start")
    @ResponseBody
    public String home()
    {
        System.out.println("PayController Start");
        //model.addAttribute("name", "indexController");  //모델 뷰 처리 시..

        String customer = payService.GetCustomer();
        System.out.println("통과");

        return customer;
    }
}
