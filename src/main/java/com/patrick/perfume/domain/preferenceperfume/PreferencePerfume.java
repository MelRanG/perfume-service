package com.patrick.perfume.domain.preferenceperfume;


import com.patrick.perfume.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class PreferencePerfume extends BaseTimeEntity {
    //로그인 구현시 userId 구현해야함 -> prePerfumeDto에도 구현해야함
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private String favoritePerfume;

    private String unfavorablePerfume;

    @Builder
    public PreferencePerfume(String userId, String favoritePerfume, String unfavorablePerfume){
        this.userId = userId;
        this.favoritePerfume = favoritePerfume;
        this.unfavorablePerfume = unfavorablePerfume;
    }

    public void update(String favoritePerfume, String unfavorablePerfume){
        this.favoritePerfume = favoritePerfume;
        this.unfavorablePerfume = unfavorablePerfume;
    }

}
