package com.patrick.perfume.web;

import com.patrick.perfume.config.auth.LoginUser;
import com.patrick.perfume.config.auth.dto.SessionUser;
import com.patrick.perfume.domain.favoriteperfume.FavoritePerfume;
import com.patrick.perfume.domain.favoriteperfume.FavoritePerfumeRepository;
import com.patrick.perfume.service.PerfumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.PostConstruct;


@RequiredArgsConstructor
@Controller
public class indexController {

    private final PerfumeService perfumeService;
    private final FavoritePerfumeRepository favoritePerfumeRepository;

    @GetMapping("/")
    public RedirectView index(){
        return new RedirectView("/perfume/list");
    }

    @GetMapping("/perfume/list")
    public String index(Model model, @LoginUser SessionUser user,
                        @PageableDefault(size = 16, sort = "CountPerfumeName") Pageable pageable){
        int next = pageable.next().getPageNumber();
        if (next == 1){next = 2;}
        model.addAttribute("perfume", perfumeService.findAllCount(pageable));
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", next);

        if (user != null){
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @PostConstruct
    public void initializing(){
        for(int i = 0; i < 30; i++){
            FavoritePerfume favoritePerfume = FavoritePerfume.builder()
                                                    .userId("user" + i)
                                                    .perfumeName("aa" + i)
                                                    .image("https://dummyimage.com/450x300/dee2e6/6c757d.jpg")
                                                    .build();

            favoritePerfumeRepository.save(favoritePerfume);
        }
    }

    @GetMapping("/perfume/save")
    public String perfumeSave(Model model, @LoginUser SessionUser user){
        if (user != null){
            model.addAttribute("userName", user.getName());
        }
        return "perfume-save";
    }

    @GetMapping("/perfume/detail/{perfumeName}")
    public String perfumeDetail(@PathVariable String perfumeName, Model model){
        model.addAttribute("favoriteUserList", perfumeService.findByFavoritePerfumeName(perfumeName));
        model.addAttribute("image", perfumeService.findByFavoritePerfumeName(perfumeName).get(0).getImage());
        model.addAttribute("perfumeName", perfumeName);
        model.addAttribute("favoriteUser", perfumeService.findByFavoritePerfumeName(perfumeName).size());
        model.addAttribute("unFavorableUser", perfumeService.findByUnfavorablePerfumeName(perfumeName).size());
        model.addAttribute("unFavorableUserList", perfumeService.findByUnfavorablePerfumeName(perfumeName));

        return "perfume-detail";
    }
}
