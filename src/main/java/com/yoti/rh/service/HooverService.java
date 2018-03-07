package com.yoti.rh.service;

import com.yoti.rh.dto.HooverInputDto;
import com.yoti.rh.dto.HooverOutputDto;
import org.springframework.stereotype.Service;

@Service
public interface HooverService {

    /**
     * Receives a hoover input instruction, processes it and returns an output
     *
     * @param input the input instruction
     * @return the output - the result of the instruction being processed
     */
    HooverOutputDto processInstruction(HooverInputDto input);

}
