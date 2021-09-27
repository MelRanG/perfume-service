package com.patrick.perfume.domain.unfavorableperfume;

import com.patrick.perfume.domain.favoriteperfume.FavoritePerfume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UnFavorablePerfumeRepository extends JpaRepository<UnFavorablePerfume, Long> {

    @Query("select count(userId) from UnFavorablePerfume where userId = :userId and perfumeName = :perfumeName")
    int countByUnFavorableUserIdAndPerfumeName(@Param(value = "userId") String userId, @Param(value = "perfumeName") String perfumeName);

    @Query("select p from UnFavorablePerfume p where p.perfumeName = :perfumeName")
    List<UnFavorablePerfume> findByPerfumeName(@Param(value= "perfumeName") String perfumeName);
}
