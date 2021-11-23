package com.patrick.perfume.domain.preferenceperfume;

import com.patrick.perfume.domain.favoriteperfume.FavoritePerfume;
import com.patrick.perfume.domain.favoriteperfume.FavoritePerfumeRepository;
import com.patrick.perfume.web.dto.PerfumeListResponseInterface;
import com.patrick.perfume.web.dto.PerfumeResponseDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FavoritePerfumeTest {
    @Autowired
    FavoritePerfumeRepository favoriteRepo;

    @Test
    public void 향수저장(){
        //given
        List<String> favorite = Arrays.asList("1번", "2번", "3번");
        List<String> unfavorable = Arrays.asList("4번", "5번", "6번");

        for(String perfume: favorite){
            favoriteRepo.save(FavoritePerfume.builder()
                    .userId("id")
                    .perfumeName(perfume)
                    .image("image")
                    .build());
        }

        //when
        List<FavoritePerfume> favoList = favoriteRepo.findAll();

        //then
        FavoritePerfume favoritePerfume = favoList.get(0);
        assertThat(favoritePerfume.getPerfumeName()).isEqualTo("aa0");
    }

    @Test
    public void 향수검색(){
        List<String> favorite = Arrays.asList("1번후들", "2번", "3번");

        for(String perfume: favorite){
            favoriteRepo.save(FavoritePerfume.builder()
                    .userId("id")
                    .perfumeName(perfume)
                    .image("image")
                    .build());
        }


        String perfumeName = "후";
        List<PerfumeListResponseInterface> result = favoriteRepo.findByPerfumeNameContaining(perfumeName);
        System.out.println(result.get(0).getPerfumeName());
        assertThat(result.get(0).getPerfumeName()).isEqualTo("1번후들");
    }

    @Test
    public void 유저조회(){
        List<FavoritePerfume> userList = favoriteRepo.findByUserId("user1");
        for(FavoritePerfume user : userList){
            System.out.println(user.getPerfumeName());
        }
    }

}
