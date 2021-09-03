package com.patrick.perfume.web.dto;

import com.patrick.perfume.domain.favoriteperfume.FavoritePerfume;
import com.patrick.perfume.domain.unfavorableperfume.UnFavorablePerfume;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UnFavorablePerfumeSaveRequestDto {
    private String userId;
    private String perfumeName;
    private String image;

    @Builder
    public UnFavorablePerfumeSaveRequestDto(String userId, String perfumeName, String image){
        this.userId = userId;
        this.perfumeName = perfumeName;
        this.image = image;
    }

    public UnFavorablePerfume toEntity(){
        return UnFavorablePerfume.builder()
                .userId(userId)
                .perfumeName(perfumeName)
                .image(image)
                .build();
    }
}
