package com.fan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RouterController {

    @RequestMapping({"/", "/index"})
    public String index() {
        return "index";
    }
    @RequestMapping("/tologin")
    public String toLogin() {
        return "vies/login";
    }
    @RequestMapping("/level1/{id}")
    public String level1(@PathVariable("id") int id) {
        return "vies/level1/" + id;
    }

    @RequestMapping("/level2/{id}")
    public String level2(@PathVariable("id") int id) {
        return "vies/level2/" + id;
    }

    @RequestMapping("/level3/{id}")
    public String level3(@PathVariable("id") int id) {
        return "vies/level3/" + id;
    }
}
