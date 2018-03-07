package com.yoti.rh.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yoti.rh.domain.HooveringEvent;
import com.yoti.rh.dto.HooverInputDto;
import com.yoti.rh.dto.HooverOutputDto;
import org.assertj.core.api.Assertions;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class HooverControllerITest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectRepository<HooveringEvent> repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldPostHooveringInstruction() throws Exception {
        HooverInputDto input = new HooverInputDto()
                .setRoomSize(new Long[]{3L, 3L})
                .setStartPosition(new Long[]{1L, 0L})
                .setDirtyPatches(new Long[][]{
                        new Long[]{0L, 0L},
                        new Long[]{1L, 1L},
                        new Long[]{2L, 2L}})
                .setDirections("NNNNESWWW");
        String inputString = objectMapper.writeValueAsString(input);

        String outputString = mockMvc.perform(post("/hoover/instructions/input")
                .content(inputString)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString();

        HooverOutputDto output = objectMapper.readValue(outputString, HooverOutputDto.class);
        HooveringEvent event = repository.find(ObjectFilters.eq("id", output.getId())).firstOrDefault();

        // We could add more assertions here to verify the database storage but the code is so simple it's not worth it
        Assert.assertNotNull(event.getInput());
        Assert.assertNotNull(event.getOutput());

        assertEquals(0L, output.getEndPosition()[0].longValue());
        assertEquals(1L, output.getEndPosition()[1].longValue());
        assertEquals(2L, output.getDirtyPatchesHoovered().longValue());
    }

    @Test
    public void shouldPropagateValidationErrors() throws Exception {
        HooverInputDto input = new HooverInputDto()
                .setRoomSize(new Long[]{3L, 3L})
                .setStartPosition(new Long[]{3L, 3L})
                .setDirtyPatches(new Long[][]{
                        new Long[]{0L},
                        new Long[]{1L},
                        new Long[]{2L}})
                .setDirections("XXX");
        String inputString = objectMapper.writeValueAsString(input);

        String outputString = mockMvc.perform(post("/hoover/instructions/input")
                .content(inputString)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse().getContentAsString();

        Map<String, List<String>> errors = objectMapper.readValue(
                outputString, new TypeReference<Map<String, List<String>>>() {
                });

        Assertions.assertThat(errors.get("errors")).containsExactlyInAnyOrder(
                "Invalid start position definition", "Invalid directions", "Invalid dirty patch definitions");
    }

}
