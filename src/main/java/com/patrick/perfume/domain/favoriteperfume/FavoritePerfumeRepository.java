package com.patrick.perfume.domain.favoriteperfume;

import com.patrick.perfume.domain.preferenceperfume.PreferencePerfume;
import com.patrick.perfume.web.dto.PerfumeListResponseInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoritePerfumeRepository extends JpaRepository<FavoritePerfume, Long> {

    @Query("select p from FavoritePerfume p order by p.id DESC")
    List<FavoritePerfume> findAllDesc();

    @Query("select p.perfumeName AS perfumeName, count(p.userId) AS countPerfumeName, p.image AS image from FavoritePerfume p group by p.perfumeName order by count(p.userId) desc")
    List<PerfumeListResponseInterface> findAllCount();
}
