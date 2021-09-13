package com.patrick.perfume.web.dto;

import com.patrick.perfume.domain.favoriteperfume.FavoritePerfume;
import lombok.Getter;

import java.util.List;

@Getter
public class PerfumeRequestDto {
    private String userId;
    private List<String> favoritePerfume;
    private List<String> unfavorablePerfume;

    public PerfumeRequestDto(String userId, List<String> favoritePerfume, List<String> unfavorablePerfume){
        this.userId = userId;
        this.favoritePerfume = favoritePerfume;
        this.unfavorablePerfume = unfavorablePerfume;
    }
}
