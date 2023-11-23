package com.jesus.courses.springboot.interceptor.app.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @Value("${config.opening.hour}")
    private Integer opening;

    @Value("${config.closing.hour}")
    private Integer closing;

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("title", "Bienvenido al horario de atención a clientes");
        return "index";
    }

    @GetMapping("/closed")
    public String closed(Model model) {
        StringBuilder msg = new StringBuilder("Cerrado, por favor visítenos desde las ");
        msg.append(opening);
        msg.append(" hrs. ");
        msg.append("hasta las ");
        msg.append(closing);
        msg.append(" hrs. Gracias");

        model.addAttribute("title", "Fuera del horario de atención");
        model.addAttribute("msg", msg.toString());
        return "closed";
    }
}
