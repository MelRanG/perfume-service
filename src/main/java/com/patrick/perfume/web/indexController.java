package com.patrick.perfume.web;

import com.patrick.perfume.config.auth.LoginUser;
import com.patrick.perfume.config.auth.dto.SessionUser;
import com.patrick.perfume.service.PerfumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.Optional;


@RequiredArgsConstructor
@Controller
public class indexController {

    private final PerfumeService perfumeService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("perfume", perfumeService.findAllDesc());

        if (user != null){
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/perfume/save")
    public String perfumeSave(Model model, @LoginUser SessionUser user){
        if (user != null){
            model.addAttribute("userName", user.getName());
        }
        return "perfume-save";
    }
}
