package com.patrick.perfume.web;

import com.patrick.perfume.service.PreferencePerfumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class indexController {

    private final PreferencePerfumeService preService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("prePerfume", preService.findAllDesc());
        return "index";
    }

    @GetMapping("/perfume/save")
    public String perfumeSave(){
        return "perfume-save";
    }
}
