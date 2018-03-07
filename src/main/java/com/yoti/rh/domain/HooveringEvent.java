package com.yoti.rh.domain;

import com.yoti.rh.dto.HooverInputDto;
import com.yoti.rh.dto.HooverOutputDto;

public class HooveringEvent {

    private transient Long id;

    private HooverInputDto input;

    private HooverOutputDto output;

    public Long getId() {
        return id;
    }

    public HooveringEvent setId(Long id) {
        this.id = id;
        return this;
    }

    public HooverInputDto getInput() {
        return input;
    }

    public HooveringEvent setInput(HooverInputDto input) {
        this.input = input;
        return this;
    }

    public HooverOutputDto getOutput() {
        return output;
    }

    public HooveringEvent setOutput(HooverOutputDto output) {
        this.output = output;
        return this;
    }

}
