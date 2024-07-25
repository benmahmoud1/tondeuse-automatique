package com.tendeuse_automatique.mapper;

import com.tendeuse_automatique.dto.SurfaceDto;
import com.tendeuse_automatique.dto.TondeuseDto;
import com.tendeuse_automatique.entity.Surface;
import com.tendeuse_automatique.entity.Tondeuse;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TondeuseMapper {

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "coordonnesX",source = "coordonnesX")
    @Mapping(target = "coordonnesY",source = "coordonnesY")
    @Mapping(target = "positionInitiale",source = "tondeuseDto",qualifiedByName = "toPositionInitiale")
    @Mapping(target = "directionInitiale",source = "directionInitiale")
    @Mapping(target = "instructions",source = "instructions")
    @Mapping(target = "surface",source = "surfaceDto")
    @Mapping(target = "positionFinale",ignore = true)
    @Mapping(target = "directionFinale",ignore = true)
    Tondeuse toTondeuse(TondeuseDto tondeuseDto);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "longeurX",source = "longeurX")
    @Mapping(target = "longeurY",source = "longeurY")
    Surface toTondeuse(SurfaceDto surfaceDto);


    @Mapping(target = "longeurX",source = "longeurX")
    @Mapping(target = "longeurY",source = "longeurY")
    SurfaceDto toSurfaceDto(Surface surface);


    @Named("toPositionInitiale")
    default String toPositionInitiale(TondeuseDto tondeuseDto){
        return String.valueOf(tondeuseDto.getCoordonnesX() + " " + tondeuseDto.getCoordonnesY() + " "+ tondeuseDto.getDirectionInitiale());
    }
}
