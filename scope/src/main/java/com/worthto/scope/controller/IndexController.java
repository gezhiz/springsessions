package com.worthto.scope.controller;

import com.worthto.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author gezz
 * @description
 * @date 2020/4/30.
 */
@Controller
public class IndexController {

    @Autowired
    private Person person;

    @GetMapping("index")
    public String index(Model model) {
        model.addAttribute("person", person);
        return "/index";
    }
}
