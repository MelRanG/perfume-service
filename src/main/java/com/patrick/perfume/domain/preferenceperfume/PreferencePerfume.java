package com.patrick.perfume.domain.preferenceperfume;


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
public class PreferencePerfume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String user_id;

    private String favorite_perfume;

    private String unfavorable_perfume;

    @Builder
    public PreferencePerfume(String user_id, String favorite_perfume, String unfavorable_perfume){
        this.user_id = user_id;
        this.favorite_perfume = favorite_perfume;
        this.unfavorable_perfume = unfavorable_perfume;
    }


}
