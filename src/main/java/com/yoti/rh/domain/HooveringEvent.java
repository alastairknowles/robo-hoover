package com.yoti.rh.domain;

import com.yoti.rh.dto.HooverInputDto;
import com.yoti.rh.dto.HooverOutputDto;
import org.dizitart.no2.objects.Id;

public class HooveringEvent {

    @Id
    private String id;

    private HooverInputDto input;

    private HooverOutputDto output;

    public String getId() {
        return id;
    }

    public HooveringEvent setId(String id) {
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
