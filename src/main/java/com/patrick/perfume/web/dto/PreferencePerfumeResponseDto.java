package com.patrick.perfume.web.dto;

import com.patrick.perfume.domain.preferenceperfume.PreferencePerfume;
import lombok.Getter;

@Getter
public class PreferencePerfumeResponseDto {
    //update시에 사용
    private Long id;
    private String favoritePerfume;
    private String unfavorablePerfume;

    public PreferencePerfumeResponseDto(PreferencePerfume entity){
        this.id = entity.getId();
        this.favoritePerfume = entity.getFavoritePerfume();
        this.unfavorablePerfume = entity.getUnfavorablePerfume();
    }
}
