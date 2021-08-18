package com.patrick.perfume.web;

import com.patrick.perfume.domain.preferenceperfume.PreferencePerfume;
import com.patrick.perfume.domain.preferenceperfume.PreferencePerfumeRepository;
import com.patrick.perfume.web.dto.PreferencePerfumeSaveRequestDto;
import com.patrick.perfume.web.dto.PreferencePerfumeUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PreferencePerfumeApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PreferencePerfumeRepository prePerfumeRepository;

    @After
    public void tearDown() throws Exception{
        prePerfumeRepository.deleteAll();
    }

    @Test
    public void PrePerfume_등록된다() throws Exception{
        //given
        String favorite = "조말론";
        String unfavorable = "불가리";
        PreferencePerfumeSaveRequestDto preRequestDto = PreferencePerfumeSaveRequestDto.builder()
                .favoritePerfume(favorite)
                .unfavorablePerfume(unfavorable)
                .build();
        String url = "http://localhost:" + port + "/api/v1/pre";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, preRequestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<PreferencePerfume> all = prePerfumeRepository.findAll();
        assertThat(all.get(0).getFavoritePerfume()).isEqualTo(favorite);
        assertThat(all.get(0).getUnfavorablePerfume()).isEqualTo(unfavorable);
    }
    @Test
    public void PrePerfume_수정된다() throws Exception{
        //given
        PreferencePerfume savedPerfume = prePerfumeRepository.save(PreferencePerfume.builder()
        .favoritePerfume("조말론")
        .unfavorablePerfume("불가리")
        .build());

        Long updateId = savedPerfume.getId();
        String expectedFavorite = "크리드";
        String expectedUnfavorable = "딥디크";

        PreferencePerfumeUpdateRequestDto requestDto = PreferencePerfumeUpdateRequestDto.builder()
                .favoritePerfume(expectedFavorite)
                .unfavorablePerfume(expectedUnfavorable)
                .build();

        String url = "http://localhost:" + port + "/api/v1/pre/" + updateId;

        HttpEntity<PreferencePerfumeUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<PreferencePerfume> all = prePerfumeRepository.findAll();
        assertThat(all.get(0).getFavoritePerfume()).isEqualTo(expectedFavorite);
        assertThat(all.get(0).getUnfavorablePerfume()).isEqualTo(expectedUnfavorable);
    }
}
