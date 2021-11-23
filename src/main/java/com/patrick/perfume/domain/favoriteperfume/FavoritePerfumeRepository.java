package com.patrick.perfume.domain.favoriteperfume;

import com.patrick.perfume.domain.preferenceperfume.PreferencePerfume;
import com.patrick.perfume.web.dto.PerfumeListResponseInterface;
import com.patrick.perfume.web.dto.PerfumeResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavoritePerfumeRepository extends JpaRepository<FavoritePerfume, Long> {

    List<FavoritePerfume> findByPerfumeName(String perfumeName);

    List<FavoritePerfume> findByUserId(String userId);


    @Query("select p.perfumeName AS perfumeName, count(p.userId) AS countPerfumeName, p.image AS image from FavoritePerfume p group by p.perfumeName, p.image having perfumeName like %:perfumeName% order by count(p.userId) desc, p.perfumeName")
    List<PerfumeListResponseInterface> findByPerfumeNameContaining(@Param(value = "perfumeName") String perfumeName);

    @Query("select p from FavoritePerfume p order by p.id DESC")
    List<FavoritePerfume> findAllDesc();

    @Query("select p.perfumeName AS perfumeName, count(p.userId) AS countPerfumeName, p.image AS image from FavoritePerfume p group by p.perfumeName, p.image order by count(p.userId) desc, p.perfumeName")
    Page<PerfumeListResponseInterface> findAllCount(Pageable pageable);

    @Query("select count(p.userId) from FavoritePerfume p where p.userId = :userId and p.perfumeName = :perfumeName")
    int countByFavoriteUserIdAndPerfumeName(@Param(value = "userId") String userId, @Param(value = "perfumeName") String perfumeName);

    @Query("SELECT perfumeName FROM FavoritePerfume where userId IN (select userId from FavoritePerfume where perfumeName = :perfumeName) and perfumeName <> :perfumeName group by perfumeName order by count(perfumeName)")
    List<String> findByFavoriteUserLikePerfume(@Param(value="perfumeName") String perfumeName);
}
