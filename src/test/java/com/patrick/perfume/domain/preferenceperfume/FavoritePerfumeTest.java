package com.patrick.perfume.domain.preferenceperfume;

import com.patrick.perfume.domain.favoriteperfume.FavoritePerfume;
import com.patrick.perfume.domain.favoriteperfume.FavoritePerfumeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FavoritePerfumeTest {
    @Autowired
    FavoritePerfumeRepository favoriteRepo;

    @Test
    public void Favorite_저장테스트(){
        favoriteRepo.save(FavoritePerfume.builder()
                .userId("aa")
                .perfumeName("bb")
                .image("cc")
                .build());

        List<FavoritePerfume> perfumes = favoriteRepo.findAll();

        FavoritePerfume favoritePerfume = perfumes.get(0);
        assertThat(favoritePerfume.getPerfumeName()).isEqualTo("bb");
        assertThat(favoritePerfume.getUserId()).isEqualTo("aa");
        assertThat(favoritePerfume.getImage()).isEqualTo("cc");
    }



}
