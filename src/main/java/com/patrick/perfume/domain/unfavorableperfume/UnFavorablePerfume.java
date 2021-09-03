package com.patrick.perfume.domain.unfavorableperfume;

import com.patrick.perfume.domain.BasePerfumeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;


@Getter
@Entity
@SuperBuilder
@NoArgsConstructor
public class UnFavorablePerfume extends BasePerfumeEntity {

}
