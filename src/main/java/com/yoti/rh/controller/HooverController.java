package com.yoti.rh.controller;

import com.yoti.rh.dto.HooverInputDto;
import com.yoti.rh.dto.HooverOutputDto;
import com.yoti.rh.service.HooverService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/hoover/instructions")
public class HooverController {

    private HooverService hooverService;

    @PostMapping("/input")
    private HooverOutputDto processInstruction(@Valid @RequestBody HooverInputDto input) {
        return hooverService.processInstruction(input);
    }

}
