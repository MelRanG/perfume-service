package com.patrick.perfume.service;

import com.patrick.perfume.domain.preferenceperfume.PreferencePerfume;
import com.patrick.perfume.domain.preferenceperfume.PreferencePerfumeRepository;
import com.patrick.perfume.web.dto.PreferencePerfumeListResponseDto;
import com.patrick.perfume.web.dto.PreferencePerfumeResponseDto;
import com.patrick.perfume.web.dto.PreferencePerfumeSaveRequestDto;
import com.patrick.perfume.web.dto.PreferencePerfumeUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PreferencePerfumeService {
    private final PreferencePerfumeRepository preRepository;

    @Transactional
    public Long save(PreferencePerfumeSaveRequestDto preRequestDto){
        return preRepository.save(preRequestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PreferencePerfumeUpdateRequestDto preRequestDto){
        PreferencePerfume prePerfume = preRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        prePerfume.update(preRequestDto.getFavoritePerfume(), preRequestDto.getUnfavorablePerfume());
        return id;
    }

    public PreferencePerfumeResponseDto findById(Long id){
        PreferencePerfume entity = preRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PreferencePerfumeResponseDto(entity);
    }

    @Transactional
    public List<PreferencePerfumeListResponseDto> findAllDesc(){
        return preRepository.findAllDesc().stream()
                .map(PreferencePerfumeListResponseDto::new)
                .collect(Collectors.toList());
    }




}

