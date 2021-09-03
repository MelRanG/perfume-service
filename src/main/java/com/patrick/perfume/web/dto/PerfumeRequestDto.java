package com.patrick.perfume.web.dto;

import com.patrick.perfume.domain.favoriteperfume.FavoritePerfume;
import lombok.Getter;

@Getter
public class PerfumeRequestDto {
    private String userId;
    private String favoritePerfume;
    private String unfavorablePerfume;

    public PerfumeRequestDto(String userId, String favoritePerfume, String unfavorablePerfume){
        this.userId = userId;
        this.favoritePerfume = favoritePerfume;
        this.unfavorablePerfume = unfavorablePerfume;
    }
}
