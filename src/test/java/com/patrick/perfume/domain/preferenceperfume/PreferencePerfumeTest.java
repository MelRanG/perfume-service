package com.patrick.perfume.domain.preferenceperfume;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PreferencePerfumeTest {

    @Autowired
    PreferencePerfumeRepository prePerfumeRepo;

    @After
    public void cleanup(){ prePerfumeRepo.deleteAll();}

    @Test
    public void 향수저장(){
        //given
        String userId = "aaa";
        String favoritePerfume = "불가리";
        String unfavorablePerfume = "조말론";

        prePerfumeRepo.save(PreferencePerfume.builder()
                .user_id(userId)
                .favorite_perfume(favoritePerfume)
                .unfavorable_perfume(unfavorablePerfume)
                .build());

        //when
        List<PreferencePerfume> preList = prePerfumeRepo.findAll();

        //then
        PreferencePerfume prePerfume = preList.get(0);
        assertThat(prePerfume.getUser_id()).isEqualTo(userId);
        assertThat(prePerfume.getFavorite_perfume()).isEqualTo(favoritePerfume);
        assertThat(prePerfume.getUnfavorable_perfume()).isEqualTo(unfavorablePerfume);
    }
}
