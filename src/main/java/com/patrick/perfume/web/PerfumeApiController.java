package com.patrick.perfume.web;

import com.patrick.perfume.domain.favoriteperfume.FavoritePerfume;
import com.patrick.perfume.service.PerfumeService;
import com.patrick.perfume.web.dto.PerfumeRequestDto;
import com.patrick.perfume.web.dto.PerfumeResponseDto;
import com.patrick.perfume.web.dto.PerfumeSaveRequestDto;
import com.patrick.perfume.web.dto.PerfumeUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PerfumeApiController {

    private final PerfumeService perfumeService;

    @PostMapping("/api/v1/pre")
    public Long save(@RequestBody PerfumeRequestDto perfume){
        //perfume을 받아서 favorite엔티티와 unfavorable엔티티로 분할해서 전달해야함
        return perfumeService.save(perfume);
    }

    @PutMapping("/api/v1/pre/{id}")
    public Long update(@PathVariable Long id, @RequestBody PerfumeUpdateRequestDto perfume){
        return perfumeService.update(id, perfume);
    }

    @GetMapping("/api/v1/pre/{id}")
    public PerfumeResponseDto findById(@PathVariable Long id){
        return perfumeService.findById(id);
    }
}
