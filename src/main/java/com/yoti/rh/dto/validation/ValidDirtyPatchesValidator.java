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
        Long[] roomSize = value.getRoomSize();
        Long roomSizeX = roomSize[0];
        Long roomSizeY = roomSize[1];
        for (Long[] dirtyPatch : value.getDirtyPatches()) {
            if (dirtyPatch == null || dirtyPatch.length != 2) {
                return false;
            }

            Long dirtyPatchX = dirtyPatch[0];
            Long dirtyPatchY = dirtyPatch[1];
            if (dirtyPatchX > roomSizeX || dirtyPatchY > roomSizeY) {
                return false;
            }
        }

        return true;
    }

}
