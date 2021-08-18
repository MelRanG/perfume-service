package com.patrick.perfume.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PreferencePerfumeUpdateRequestDto {
    private String favoritePerfume;
    private String unfavorablePerfume;

    @Builder
    public PreferencePerfumeUpdateRequestDto(String favoritePerfume, String unfavorablePerfume){
        this.favoritePerfume = favoritePerfume;
        this.unfavorablePerfume = unfavorablePerfume;
    }
}
