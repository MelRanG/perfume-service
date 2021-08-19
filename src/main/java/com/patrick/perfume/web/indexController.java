package com.patrick.perfume.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/perfume/save")
    public String perfumeSave(){
        return "perfume-save";
    }
}
