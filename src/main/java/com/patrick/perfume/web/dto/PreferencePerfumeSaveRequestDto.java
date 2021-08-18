package com.patrick.perfume.web.dto;

import com.patrick.perfume.domain.preferenceperfume.PreferencePerfume;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PreferencePerfumeSaveRequestDto {
    // 로그인 구현하면 userId 추가해야함

    private String userId;
    private String favoritePerfume;
    private String unfavorablePerfume;

    @Builder
    public PreferencePerfumeSaveRequestDto(String favoritePerfume, String unfavorablePerfume){
        this.favoritePerfume = favoritePerfume;
        this.unfavorablePerfume = unfavorablePerfume;
    }

    public PreferencePerfume toEntity(){
        return PreferencePerfume.builder()
                .favoritePerfume(favoritePerfume)
                .unfavorablePerfume(unfavorablePerfume)
                .build();
    }

}


