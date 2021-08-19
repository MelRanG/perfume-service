package com.patrick.perfume.web.dto;

import com.patrick.perfume.domain.preferenceperfume.PreferencePerfume;
import lombok.Getter;

@Getter
public class PreferencePerfumeListResponseDto {
    private Long id;
    private String userId;
    private String favoritePerfume;
    private String unfavorablePerfume;

    public PreferencePerfumeListResponseDto(PreferencePerfume entity){
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.favoritePerfume = entity.getFavoritePerfume();
        this.unfavorablePerfume = entity.getUnfavorablePerfume();
    }
}
