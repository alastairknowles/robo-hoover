package com.yoti.rh.dto.validation;

import com.yoti.rh.dto.HooverInputDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidStartPositionValidator implements ConstraintValidator<ValidDirtyPatches, HooverInputDto> {

    @Override
    public void initialize(ValidDirtyPatches constraintAnnotation) {

    }

    @Override
    public boolean isValid(HooverInputDto value, ConstraintValidatorContext context) {
        Long[] roomSize = value.getRoomSize();
        Long roomSizeX = roomSize[0];
        Long roomSizeY = roomSize[1];

        Long[] startPosition = value.getStartPosition();
        Long startPositionX = startPosition[0];
        Long startPositionY = startPosition[1];

        return startPositionX <= roomSizeX && startPositionY <= roomSizeY;
    }

}
