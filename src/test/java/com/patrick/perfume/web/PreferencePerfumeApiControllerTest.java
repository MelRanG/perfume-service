//package com.patrick.perfume.web;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.patrick.perfume.domain.preferenceperfume.PreferencePerfume;
//import com.patrick.perfume.domain.preferenceperfume.PreferencePerfumeRepository;
//import com.patrick.perfume.web.dto.PreferencePerfumeSaveRequestDto;
//import com.patrick.perfume.web.dto.PreferencePerfumeUpdateRequestDto;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class PreferencePerfumeApiControllerTest {
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private PreferencePerfumeRepository prePerfumeRepository;
//
//    @Autowired
//    private WebApplicationContext context;
//
//    private MockMvc mvc;
//
//    @Before
//    public void setup(){
//        mvc = MockMvcBuilders
//                .webAppContextSetup(context)
//                .apply(springSecurity())
//                .build();
//    }
//
//    @After
//    public void tearDown() throws Exception{
//        prePerfumeRepository.deleteAll();
//    }
//
//    @Test
//    @WithMockUser(roles="USER")
//    public void PrePerfume_등록된다() throws Exception{
//        //given
//        String favorite = "조말론";
//        String unfavorable = "불가리";
//        PreferencePerfumeSaveRequestDto preRequestDto = PreferencePerfumeSaveRequestDto.builder()
//                .favoritePerfume(favorite)
//                .unfavorablePerfume(unfavorable)
//                .build();
//        String url = "http://localhost:" + port + "/api/v1/pre";
//
//        //when
//        mvc.perform(post(url)
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content(new ObjectMapper().writeValueAsString(preRequestDto)))
//                .andExpect(status().isOk());
//
//        //then
//        List<PreferencePerfume> all = prePerfumeRepository.findAll();
//        assertThat(all.get(0).getFavoritePerfume()).isEqualTo(favorite);
//        assertThat(all.get(0).getUnfavorablePerfume()).isEqualTo(unfavorable);
//    }
//    @Test
//    @WithMockUser(roles="USER")
//    public void PrePerfume_수정된다() throws Exception{
//        //given
//        PreferencePerfume savedPerfume = prePerfumeRepository.save(PreferencePerfume.builder()
//        .favoritePerfume("조말론")
//        .unfavorablePerfume("불가리")
//        .build());
//
//        Long updateId = savedPerfume.getId();
//        String expectedFavorite = "크리드";
//        String expectedUnfavorable = "딥디크";
//
//        PreferencePerfumeUpdateRequestDto requestDto = PreferencePerfumeUpdateRequestDto.builder()
//                .favoritePerfume(expectedFavorite)
//                .unfavorablePerfume(expectedUnfavorable)
//                .build();
//
//        String url = "http://localhost:" + port + "/api/v1/pre/" + updateId;
//
//        //when
//        mvc.perform(put(url)
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content(new ObjectMapper().writeValueAsString(requestDto)))
//                .andExpect(status().isOk());
//
//        //then
//        List<PreferencePerfume> all = prePerfumeRepository.findAll();
//        assertThat(all.get(0).getFavoritePerfume()).isEqualTo(expectedFavorite);
//        assertThat(all.get(0).getUnfavorablePerfume()).isEqualTo(expectedUnfavorable);
//    }
//}
