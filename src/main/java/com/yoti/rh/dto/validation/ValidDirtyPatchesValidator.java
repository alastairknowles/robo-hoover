package com.yoti.rh.dto.validation;

import com.yoti.rh.dto.HooverInputDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidDirtyPatchesValidator implements ConstraintValidator<ValidDirtyPatches, HooverInputDto> {

    @Override
    public void initialize(ValidDirtyPatches constraintAnnotation) {

    }

    @Override
    public boolean isValid(HooverInputDto value, ConstraintValidatorContext context) {
        Long[][] dirtyPatches = value.getDirtyPatches();
        if (dirtyPatches == null || dirtyPatches.length == 0) {
            return false;
        }

        Long[] startPosition = value.getStartPosition();
        Long startPositionX = startPosition[0];
        Long startPositionY = startPosition[1];
        for (Long[] dirtyPatch : dirtyPatches) {
            if (dirtyPatch == null || dirtyPatch.length != 2L) {
                return false;
            }

            Long dirtyPatchX = dirtyPatch[0];
            Long dirtyPatchY = dirtyPatch[1];
            if (dirtyPatchX > startPositionX || dirtyPatchY > startPositionY) {
                return false;
            }
        }

        return true;
    }

}
