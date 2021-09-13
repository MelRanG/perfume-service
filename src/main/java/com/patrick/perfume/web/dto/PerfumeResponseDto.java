package com.patrick.perfume.web.dto;

import com.patrick.perfume.domain.favoriteperfume.FavoritePerfume;
import lombok.Getter;

import java.util.List;

@Getter
public class PerfumeResponseDto {
    private Long id;
    private String userId;
    private String perfumeName;
    private String image;


    public PerfumeResponseDto(FavoritePerfume entity){
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.perfumeName = entity.getPerfumeName();
        this.image = entity.getImage();
    }
}
