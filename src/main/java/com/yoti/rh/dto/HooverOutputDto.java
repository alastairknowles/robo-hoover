package com.yoti.rh.dto;

public class HooverOutputDto {

    private Long id;

    private Long[] endPosition;

    private Long dirtyPatchesHoovered;

    public Long getId() {
        return id;
    }

    public HooverOutputDto setId(Long id) {
        this.id = id;
        return this;
    }

    public Long[] getEndPosition() {
        return endPosition;
    }

    public HooverOutputDto setEndPosition(Long[] endPosition) {
        this.endPosition = endPosition;
        return this;
    }

    public Long getDirtyPatchesHoovered() {
        return dirtyPatchesHoovered;
    }

    public HooverOutputDto setDirtyPatchesHoovered(Long dirtyPatchesHoovered) {
        this.dirtyPatchesHoovered = dirtyPatchesHoovered;
        return this;
    }

}
