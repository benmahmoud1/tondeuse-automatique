package com.tendeuse_automatique.batch.processors;

import com.tendeuse_automatique.dto.SurfaceDto;
import com.tendeuse_automatique.dto.TondeuseDto;
import com.tendeuse_automatique.dto.TondeuseInDto;
import com.tendeuse_automatique.entity.Tondeuse;
import com.tendeuse_automatique.enums.Direction;
import com.tendeuse_automatique.mapper.TondeuseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TondeuseProcessor implements ItemProcessor<TondeuseInDto, Tondeuse> {

    private final TondeuseMapper tondeuseMapper;

    @Override
    public Tondeuse process(TondeuseInDto tondeuseInDto)  {

        String[] surfaceDimensions = tondeuseInDto.getSurface().split(" ");
        int longeurX = Integer.parseInt(surfaceDimensions[0]);
        int longeurY = Integer.parseInt(surfaceDimensions[1]);
        SurfaceDto surfaceDto = SurfaceDto.builder().longeurX(longeurX).longeurY(longeurY).build();
        String[] position = tondeuseInDto.getPositionInitiale().split(" ");
        int coordoonnesX = Integer.parseInt(position[0]);
        int coordoonnesY = Integer.parseInt(position[1]);
        Direction direction = Direction.getDirectionFromName(position[2].charAt(0)) ;

        return tondeuseMapper.toTondeuse(TondeuseDto.builder()
                .coordonnesX(coordoonnesX)
                .coordonnesY(coordoonnesY)
                .directionInitiale(direction)
                .instructions(tondeuseInDto.getInstructions())
                .surfaceDto(surfaceDto)
                .build()) ;

    }

}
