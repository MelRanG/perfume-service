package com.patrick.perfume.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PerfumeUpdateRequestDto {
    private String perfumeName;
    private String image;

    @Builder
    public PerfumeUpdateRequestDto(String perfumeName, String image){
        this.perfumeName = perfumeName;
        this.image = image;
    }
}
