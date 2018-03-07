package com.yoti.rh.dto;

public class HooverOutputDto {

    private String id;

    private Long[] endPosition;

    private Long dirtyPatchesHoovered;

    public String getId() {
        return id;
    }

    public HooverOutputDto setId(String id) {
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
