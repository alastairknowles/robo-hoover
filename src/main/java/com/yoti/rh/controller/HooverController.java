package com.yoti.rh.controller;

import com.google.common.collect.ImmutableMap;
import com.yoti.rh.dto.HooverInputDto;
import com.yoti.rh.dto.HooverOutputDto;
import com.yoti.rh.service.HooverService;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hoover/instructions")
public class HooverController {

    private HooverService hooverService;

    public HooverController(HooverService hooverService) {
        this.hooverService = hooverService;
    }

    @PostMapping("/input")
    private HooverOutputDto processInstruction(@Valid @RequestBody HooverInputDto input) {
        return hooverService.processInstruction(input);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, List<String>> handleException(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        return ImmutableMap.of("errors", errors);
    }

}
