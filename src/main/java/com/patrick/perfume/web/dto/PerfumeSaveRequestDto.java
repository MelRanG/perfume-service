package com.patrick.perfume.web.dto;

import com.patrick.perfume.domain.BasePerfumeEntity;
import com.patrick.perfume.domain.favoriteperfume.FavoritePerfume;
import com.patrick.perfume.domain.preferenceperfume.PreferencePerfume;
import com.patrick.perfume.domain.unfavorableperfume.UnFavorablePerfume;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PerfumeSaveRequestDto {
    // 로그인 구현하면 userId 추가해야함

    private String userId;
    private String perfumeName;
    private String image;

    @Builder
    public PerfumeSaveRequestDto(String userId, String perfumeName, String image){
        this.userId = userId;
        this.perfumeName = perfumeName;
        this.image = image;
    }

    public FavoritePerfume toEntity(){
        return FavoritePerfume.builder()
                .userId(userId)
                .perfumeName(perfumeName)
                .image(image)
                .build();
    }

}
