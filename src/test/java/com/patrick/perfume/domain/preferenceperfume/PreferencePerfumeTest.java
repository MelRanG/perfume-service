//package com.patrick.perfume.domain.preferenceperfume;
//
//import org.junit.After;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class PreferencePerfumeTest {
//
//    @Autowired
//    PreferencePerfumeRepository prePerfumeRepo;
//
//    @After
//    public void cleanup(){ prePerfumeRepo.deleteAll();}
//
//    @Test
//    public void 향수저장(){
//        //given
//        String favoritePerfume = "불가리";
//        String unfavorablePerfume = "조말론";
//
//        prePerfumeRepo.save(PreferencePerfume.builder()
//                .favoritePerfume(favoritePerfume)
//                .unfavorablePerfume(unfavorablePerfume)
//                .build());
//
//        //when
//        List<PreferencePerfume> preList = prePerfumeRepo.findAll();
//
//        //then
//        PreferencePerfume prePerfume = preList.get(0);
//        assertThat(prePerfume.getFavoritePerfume()).isEqualTo(favoritePerfume);
//        assertThat(prePerfume.getUnfavorablePerfume()).isEqualTo(unfavorablePerfume);
//    }
//
//    @Test
//    public void BaseTimeEntity_등록(){
//        //given
//        LocalDateTime now = LocalDateTime.of(2021,8,17,0,0,0);
//
//        prePerfumeRepo.save(PreferencePerfume.builder()
//                .favoritePerfume("조말론")
//                .unfavorablePerfume("불가리")
//                .build());
//
//        //when
//        List<PreferencePerfume> preList = prePerfumeRepo.findAll();
//
//        //then
//        PreferencePerfume prePerfume = preList.get(0);
//
//        assertThat(prePerfume.getCreatedDate()).isAfter(now);
//        assertThat(prePerfume.getModifiedDate()).isAfter(now);
//    }
//
//}
