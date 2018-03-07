package com.yoti.rh.dto.validation;

import com.yoti.rh.dto.HooverInputDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static com.yoti.rh.util.CoordinateUtils.isInBounds;

public class ValidStartPositionValidator implements ConstraintValidator<ValidDirtyPatches, HooverInputDto> {

    @Override
    public void initialize(ValidDirtyPatches constraintAnnotation) {

    }

    @Override
    public boolean isValid(HooverInputDto value, ConstraintValidatorContext context) {
        Long[] lower = new Long[]{0L, 0L};
        Long[] upper = value.getRoomSize();
        Long[] startPosition = value.getStartPosition();
        return isInBounds(lower, upper, startPosition);

    }

}
