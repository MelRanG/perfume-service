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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;


@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PerfumeService perfumeService;
    private final FavoritePerfumeRepository favoritePerfumeRepository;

    @PostConstruct
    public void initializing(){
        for(int i = 0; i < 15; i++){
            FavoritePerfume favoritePerfume = FavoritePerfume.builder()
                    .userId("user" + i)
                    .perfumeName("aa" + i)
                    .image("https://dummyimage.com/450x300/dee2e6/6c757d.jpg")
                    .build();

            favoritePerfumeRepository.save(favoritePerfume);
        }
        int j = 0;
        for(int i = 15; i > 0; i--){
            FavoritePerfume favoritePerfume = FavoritePerfume.builder()
                    .userId("user" + i)
                    .perfumeName("aa" + j)
                    .image("https://dummyimage.com/450x300/dee2e6/6c757d.jpg")
                    .build();
            j++;
            favoritePerfumeRepository.save(favoritePerfume);
        }
    }

    @GetMapping("/")
    public RedirectView index(@LoginUser SessionUser user, HttpSession session){
        if (user != null){
            session.setAttribute("userName", user.getName());
        }
        return new RedirectView("/perfume/list");
    }

    @GetMapping("/perfume/list")
    public String index(Model model,
                        @PageableDefault(size = 16, sort = "CountPerfumeName") Pageable pageable, HttpSession session){
        int next = pageable.next().getPageNumber();
        if (next == 1){next = 2;}
        model.addAttribute("perfume", perfumeService.findAllCount(pageable));
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", next);
        return "index";
    }

    @GetMapping("/perfume/search")
    public String search(Model model, @RequestParam(name = "perfumeName")String perfumeName){
        model.addAttribute("perfume", perfumeService.findByPerfumeNameContaining(perfumeName));
        return "perfume-search";
    }

    @GetMapping("/perfume/save")
    public String perfumeSave(Model model){
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
        model.addAttribute("favoriteUserLikePerfume", perfumeService.findByFavoriteUserLikePerfume(perfumeName));
        model.addAttribute("unFavorableUserLikePerfume", perfumeService.findByUnFavorableUserLikePerfume(perfumeName));

        return "perfume-detail";
    }
}
