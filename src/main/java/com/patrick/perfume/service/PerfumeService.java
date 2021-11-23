package com.patrick.perfume.service;

import com.patrick.perfume.domain.favoriteperfume.FavoritePerfume;
import com.patrick.perfume.domain.favoriteperfume.FavoritePerfumeRepository;

import com.patrick.perfume.domain.unfavorableperfume.UnFavorablePerfume;
import com.patrick.perfume.domain.unfavorableperfume.UnFavorablePerfumeRepository;
import com.patrick.perfume.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PerfumeService {

    private final FavoritePerfumeRepository favoritePerfumeRepo;
    private final UnFavorablePerfumeRepository unFavorablePerfumeRepo;
    private static final int PAGE_POST_COUNT = 16;


    @Transactional
    public boolean save(PerfumeRequestDto requestDto) {
        List<String> favoritePerfumeName = requestDto.getFavoritePerfume();
        List<String> unfavoritePerfumeName = requestDto.getUnfavorablePerfume();
        boolean check = true;

        for (String perfume : favoritePerfumeName) {
            // 향수 중복 체크
            if (favoriteCountByUserIdAndPerfumeName(requestDto.getUserId(), perfume)) {
                favoritePerfumeRepo.save(FavoritePerfume.builder()
                        .userId(requestDto.getUserId())
                        .perfumeName(perfume)
                        .image("https://dummyimage.com/450x300/dee2e6/6c757d.jpg")
                        .build());
            } else {
                check = false;
            }
        }
        for (String perfume : unfavoritePerfumeName) {
            if (unFavorableCountByUserIdAndPerfumeName(requestDto.getUserId(), perfume)) {
                unFavorablePerfumeRepo.save(UnFavorablePerfume.builder()
                        .userId(requestDto.getUserId())
                        .perfumeName(perfume)
                        .image("https://dummyimage.com/450x300/dee2e6/6c757d.jpg")
                        .build());
            } else{
            check =  false;
        }
    } return check;
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

    public boolean favoriteCountByUserIdAndPerfumeName(String userId, String perfumeName){
        if (favoritePerfumeRepo.countByFavoriteUserIdAndPerfumeName(userId, perfumeName) == 0){return true;}
        return false;
    }

    public boolean unFavorableCountByUserIdAndPerfumeName(String userId, String perfumeName) {
        if(unFavorablePerfumeRepo.countByUnFavorableUserIdAndPerfumeName(userId, perfumeName) == 0){return true;}
        return false;
    }

//    @Transactional
//    public List<PerfumeListResponseInterface> findAllCount(){
//        return favoritePerfumeRepo.findAllCount();
//    }

    @Transactional
    public Page<PerfumeListResponseInterface> findAllCount(Pageable pageable){
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize());
        return favoritePerfumeRepo.findAllCount(pageable);
    }

    public List<FavoritePerfume> findByFavoritePerfumeName(String perfumeName){
        return favoritePerfumeRepo.findByPerfumeName(perfumeName);
    }

    public List<FavoritePerfume> findByFavoriteUserId(String userId){
        return favoritePerfumeRepo.findByUserId(userId);
    }

    public List<UnFavorablePerfume> findByUnfavorablePerfumeName(String perfumeName){
        return unFavorablePerfumeRepo.findByPerfumeName(perfumeName);
    }

    public List<String> findByFavoriteUserLikePerfume(String perfumeName){
        return favoritePerfumeRepo.findByFavoriteUserLikePerfume(perfumeName);
    }

    public List<String> findByUnFavorableUserLikePerfume(String perfume){
        return unFavorablePerfumeRepo.findByUnFavorableUserLikePerfume(perfume);
    }

    public List<PerfumeListResponseInterface> findByPerfumeNameContaining(String perfumeName){
        return favoritePerfumeRepo.findByPerfumeNameContaining(perfumeName);
    }

}
