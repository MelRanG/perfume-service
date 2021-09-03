package com.patrick.perfume.service;

import com.patrick.perfume.domain.favoriteperfume.FavoritePerfume;
import com.patrick.perfume.domain.favoriteperfume.FavoritePerfumeRepository;

import com.patrick.perfume.domain.unfavorableperfume.UnFavorablePerfume;
import com.patrick.perfume.domain.unfavorableperfume.UnFavorablePerfumeRepository;
import com.patrick.perfume.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PerfumeService {

    private final FavoritePerfumeRepository perfumeRepo;
    private final UnFavorablePerfumeRepository unPerfumeRepo;


    @Transactional
    public Long save(PerfumeRequestDto requestDto){
        PerfumeSaveRequestDto perfumeSaveRequestDto = new PerfumeSaveRequestDto(requestDto.getUserId(), requestDto.getFavoritePerfume(), "aa");
        UnFavorablePerfumeSaveRequestDto unPerfumeSaveRequestDto = new UnFavorablePerfumeSaveRequestDto(requestDto.getUserId(), requestDto.getUnfavorablePerfume(), "aa");

        unPerfumeRepo.save(unPerfumeSaveRequestDto.toEntity());
        return perfumeRepo.save(perfumeSaveRequestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PerfumeUpdateRequestDto perfumeUpdateRequestDto){
        FavoritePerfume perfume = perfumeRepo.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        perfume.update(perfumeUpdateRequestDto.getPerfumeName(), perfumeUpdateRequestDto.getImage());
        return id;
    }

    public PerfumeResponseDto findById(Long id){
        FavoritePerfume entity = perfumeRepo.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PerfumeResponseDto(entity);
    }

    @Transactional
    public List<PerfumeListResponseInterface> findAllDesc(){
        return perfumeRepo.findAllDesc();
    }

}
