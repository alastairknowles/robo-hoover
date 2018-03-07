package com.yoti.rh.dto;

import com.yoti.rh.dto.validation.ValidDirtyPatches;
import com.yoti.rh.dto.validation.ValidStartPosition;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ValidDirtyPatches(message = "Invalid dirty patch definitions")
@ValidStartPosition(message = "Invalid start position definition")
public class HooverInputDto {

    @NotNull
    @Size(min = 2, max = 2, message = "Invalid room size dimensions")
    private Long[] roomSize;

    @NotNull
    @Size(min = 2, max = 2, message = "Invalid start position")
    private Long[] startPosition;

    @NotEmpty
    private Long[][] dirtyPatches;

    @NotEmpty
    @Pattern(regexp = "^[NSEW]+", message = "Invalid directions")
    private String directions;

    public Long[] getRoomSize() {
        return roomSize;
    }

    public HooverInputDto setRoomSize(Long[] roomSize) {
        this.roomSize = roomSize;
        return this;
    }

    public Long[] getStartPosition() {
        return startPosition;
    }

    public HooverInputDto setStartPosition(Long[] startPosition) {
        this.startPosition = startPosition;
        return this;
    }

    public Long[][] getDirtyPatches() {
        return dirtyPatches;
    }

    public HooverInputDto setDirtyPatches(Long[][] dirtyPatches) {
        this.dirtyPatches = dirtyPatches;
        return this;
    }

    public String getDirections() {
        return directions;
    }

    public HooverInputDto setDirections(String directions) {
        this.directions = directions;
        return this;
    }

}
