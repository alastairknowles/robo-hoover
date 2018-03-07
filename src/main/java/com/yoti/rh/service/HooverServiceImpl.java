package com.yoti.rh.service;

import com.yoti.rh.domain.HooveringEvent;
import com.yoti.rh.dto.HooverInputDto;
import com.yoti.rh.dto.HooverOutputDto;
import com.yoti.rh.repository.HooverRepository;
import org.springframework.stereotype.Service;

@Service
public class HooverServiceImpl implements HooverService {

    private final HooverRepository hooverRepository;

    public HooverServiceImpl(HooverRepository hooverRepository) {
        this.hooverRepository = hooverRepository;
    }

    @Override
    public HooverOutputDto processInstruction(HooverInputDto input) {
        HooverOutputDto output = hoover(input);
        HooveringEvent hooveringEvent = new HooveringEvent().setInput(input).setOutput(output);
        hooveringEvent = hooverRepository.createHooveringEvent(hooveringEvent);
        output.setId(hooveringEvent.getId());
        return output;
    }

    private HooverOutputDto hoover(HooverInputDto input) {
        HooverOutputDto output = new HooverOutputDto();
        return output;
    }

}
