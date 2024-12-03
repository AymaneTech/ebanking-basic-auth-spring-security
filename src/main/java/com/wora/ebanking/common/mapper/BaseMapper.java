package com.wora.ebanking.common.mapper;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface BaseMapper<E, R, S> {
    E toEntity(R dto);

    S toResponseDTO(E entity);
}
