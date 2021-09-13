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

@RequiredArgsConstructor
@Service
public class PerfumeService {

    private final FavoritePerfumeRepository favoritePerfumeRepo;
    private final UnFavorablePerfumeRepository unPerfumeRepo;



    @Transactional
    public void save(PerfumeRequestDto requestDto){
        PerfumeSaveRequestDto perfumeSaveRequestDto = new PerfumeSaveRequestDto();

        List<String> favoritePerfumeName = requestDto.getFavoritePerfume();
        List<String> unfavoritePerfumeName = requestDto.getUnfavorablePerfume();

        for(String perfume: favoritePerfumeName){
            favoritePerfumeRepo.save(FavoritePerfume.builder()
                    .userId("id")
                    .perfumeName(perfume)
                    .image("image")
                    .build());
        }

        for(String perfume: unfavoritePerfumeName){
            unPerfumeRepo.save(UnFavorablePerfume.builder()
                    .userId("id")
                    .perfumeName(perfume)
                    .image("image")
                    .build());
        }
    }

//    @Transactional
//    public Long update(Long id, PerfumeUpdateRequestDto perfumeUpdateRequestDto){
//        FavoritePerfume perfume = perfumeRepo.findById(id).orElseThrow(() ->
//                new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
//        perfume.update(perfumeUpdateRequestDto.getPerfumeName(), perfumeUpdateRequestDto.getImage());
//        return id;
//    }

    public PerfumeResponseDto findById(Long id){
        FavoritePerfume entity = favoritePerfumeRepo.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PerfumeResponseDto(entity);
    }

    @Transactional
    public List<PerfumeListResponseInterface> findAllCount(){
        return favoritePerfumeRepo.findAllCount();
    }
}
