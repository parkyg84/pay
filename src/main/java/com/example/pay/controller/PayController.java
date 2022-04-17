package com.example.pay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PayController {

    @RequestMapping(value = "/")
    public String home()
    {
        System.out.println("index controller start");
        //model.addAttribute("name", "indexController");

        return "index";
    }
}
