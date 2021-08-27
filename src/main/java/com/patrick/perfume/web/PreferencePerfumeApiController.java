package com.patrick.perfume.web;

import com.patrick.perfume.service.PreferencePerfumeService;
import com.patrick.perfume.web.dto.PerfumeSaveRequestDto;
import com.patrick.perfume.web.dto.PreferencePerfumeResponseDto;
import com.patrick.perfume.web.dto.PreferencePerfumeSaveRequestDto;
import com.patrick.perfume.web.dto.PreferencePerfumeUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PreferencePerfumeApiController {

    private final PreferencePerfumeService preService;


    @PostMapping("/api/v1/pre")
    public Long save(@RequestBody PreferencePerfumeSaveRequestDto preRequestDto){
        return preService.save(preRequestDto);
    }

    @PutMapping("/api/v1/pre/{id}")
    public Long update(@PathVariable Long id, @RequestBody PreferencePerfumeUpdateRequestDto preRequestDto){
        return preService.update(id, preRequestDto);
    }

    @GetMapping("/api/v1/pre/{id}")
    public PreferencePerfumeResponseDto findById(@PathVariable Long id){
        return preService.findById(id);
    }
}
