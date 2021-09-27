package com.patrick.perfume.web;

import com.patrick.perfume.domain.favoriteperfume.FavoritePerfume;
import com.patrick.perfume.domain.favoriteperfume.FavoritePerfumeRepository;
import com.patrick.perfume.web.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PerfumeApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private FavoritePerfumeRepository perfumeRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup(){
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @After
    public void tearDown() throws Exception{
        perfumeRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles="USER")
    public void perfume_등록된다() throws Exception{
        //given
        String userId = "id";
        List<String> favorite = Arrays.asList("1번", "2번", "3번");
        List<String> unfavorable = Arrays.asList("4번", "5번", "6번");
        String image = "image";

        PerfumeRequestDto perfumeRequestDto = new PerfumeRequestDto(userId, favorite, unfavorable);

        String url = "http://localhost:" + port + "/api/v1/perfume";

        //when
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(perfumeRequestDto)))
                .andExpect(status().isOk());

        //then
        List<FavoritePerfume> all = perfumeRepository.findAll();
        assertThat(all.get(0).getUserId()).isEqualTo(userId);
        assertThat(all.get(0).getPerfumeName()).isEqualTo("1번");

    }

    @Test
    @WithMockUser(roles="USER")
    public void Perfume_수정된다() throws Exception{
        //given
        String userId = "조말론";
        String perfumeName = "불가리";
        String image = "aaa";

        FavoritePerfume savedPerfume = perfumeRepository.save(FavoritePerfume.builder()
                .userId(userId)
                .perfumeName(perfumeName)
                .image(image)
                .build());

        Long updateId = savedPerfume.getId();
        String expectedPerfume = "크리드";
        String expectedimage = "딥디크";

        PerfumeUpdateRequestDto requestDto = PerfumeUpdateRequestDto.builder()
                .perfumeName(expectedPerfume)
                .image(expectedimage)
                .build();

        String url = "http://localhost:" + port + "/api/v1/perfume/" + updateId;

        //when
        mvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        List<FavoritePerfume> all = perfumeRepository.findAll();
        assertThat(all.get(0).getPerfumeName()).isEqualTo(expectedPerfume);
        assertThat(all.get(0).getImage()).isEqualTo(expectedimage);
    }

//    @Test
//    @WithMockUser(roles = "USER")
//    public void perfume_조회() throws Exception{
//        String userId = "mel";
//        String perfumeName = "불가리";
//        String image = "aaa";
//        PerfumeSaveRequestDto perfumeRequestDto = PerfumeSaveRequestDto.builder()
//                .userId(userId)
//                .perfumeName(perfumeName)
//                .image(image)
//                .build();
//        String url = "http://localhost:" + port + "/api/v1/pre";
//
//        //when
//        mvc.perform(post(url)
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content(new ObjectMapper().writeValueAsString(perfumeRequestDto)))
//                .andExpect(status().isOk());
//
//        List<PerfumeListResponseInterface> responseDto = perfumeRepository.findAllDesc();
//        System.out.println(responseDto.get(0).getPerfumeName());
//        //assertThat(responseDto.get(0).getPerfumeName()).isEqualTo("불가리");
//    }
}
