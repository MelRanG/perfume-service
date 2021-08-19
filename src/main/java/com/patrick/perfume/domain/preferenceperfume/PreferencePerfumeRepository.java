package com.patrick.perfume.domain.preferenceperfume;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PreferencePerfumeRepository extends JpaRepository<PreferencePerfume, Long> {

    @Query("select p from PreferencePerfume p order by p.id DESC")
    List<PreferencePerfume> findAllDesc();
}
