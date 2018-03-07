package com.yoti.rh.util.service;

import com.yoti.rh.domain.HooveringEvent;
import com.yoti.rh.dto.HooverInputDto;
import com.yoti.rh.dto.HooverOutputDto;
import com.yoti.rh.repository.HooverRepository;
import com.yoti.rh.service.HooverServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HooverServiceUTest {

    @Mock
    private HooverRepository hooverRepository;

    private HooverServiceImpl hooverService;

    @Before
    public void setUp() {
        when(hooverRepository.createHooveringEvent(any(HooveringEvent.class)))
                .thenReturn(new HooveringEvent().setId(1L));
        hooverService = new HooverServiceImpl(hooverRepository);
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(hooverRepository);
        reset(hooverRepository);
    }

    /**
     * Minimal test verifies that we can just hoover the starting square and not go anywhere else
     */
    @Test
    public void shouldHooverStartingSquareWhenHooverDoesNotMove() {
        HooverInputDto input = new HooverInputDto()
                .setRoomSize(new Long[]{1L, 1L})
                .setStartPosition(new Long[]{0L, 0L})
                .setDirtyPatches(new Long[][]{
                        new Long[]{0L, 0L}});

        HooverOutputDto output = hooverService.processInstruction(input);
        verify(hooverRepository, times(1)).createHooveringEvent(any(HooveringEvent.class));
        assertEquals(1L, output.getId().longValue());
        assertEquals(0L, output.getEndPosition()[0].longValue());
        assertEquals(0L, output.getEndPosition()[0].longValue());
        assertEquals(1L, output.getDirtyPatchesHoovered().longValue());
    }

    /**
     * Minimal test verifies that we always skid when we hit the boundary, in all directions
     */
    @Test
    public void shouldSkidInAllTravelDirectionsWhenHittingBoundary() {
        HooverInputDto input = new HooverInputDto()
                .setRoomSize(new Long[]{1L, 1L})
                .setStartPosition(new Long[]{0L, 0L})
                .setDirtyPatches(new Long[][]{
                        new Long[]{0L, 0L}})
                .setDirections("NSEW");

        HooverOutputDto output = hooverService.processInstruction(input);
        verify(hooverRepository, times(1)).createHooveringEvent(any(HooveringEvent.class));
        assertEquals(1L, output.getId().longValue());
        assertEquals(0L, output.getEndPosition()[0].longValue());
        assertEquals(0L, output.getEndPosition()[1].longValue());
        assertEquals(1L, output.getDirtyPatchesHoovered().longValue());
    }

    /**
     * Minimal test verifies that we always hoover the first square when it is dirty
     */
    @Test
    public void shouldHooverStartingSquare() {
        HooverInputDto input = new HooverInputDto()
                .setRoomSize(new Long[]{1L, 2L})
                .setStartPosition(new Long[]{0L, 0L})
                .setDirtyPatches(new Long[][]{
                        new Long[]{0L, 0L}})
                .setDirections("N");

        HooverOutputDto output = hooverService.processInstruction(input);
        verify(hooverRepository, times(1)).createHooveringEvent(any(HooveringEvent.class));
        assertEquals(1L, output.getId().longValue());
        assertEquals(0L, output.getEndPosition()[0].longValue());
        assertEquals(1L, output.getEndPosition()[1].longValue());
        assertEquals(1L, output.getDirtyPatchesHoovered().longValue());
    }

    /**
     * Minimal test verifies that we always hoover the final square when it is dirty
     */
    @Test
    public void shouldHooverFinishingSquare() {
        HooverInputDto input = new HooverInputDto()
                .setRoomSize(new Long[]{1L, 2L})
                .setStartPosition(new Long[]{0L, 0L})
                .setDirtyPatches(new Long[][]{
                        new Long[]{0L, 1L}})
                .setDirections("N");

        HooverOutputDto output = hooverService.processInstruction(input);
        verify(hooverRepository, times(1)).createHooveringEvent(any(HooveringEvent.class));
        assertEquals(1L, output.getId().longValue());
        assertEquals(0L, output.getEndPosition()[0].longValue());
        assertEquals(1L, output.getEndPosition()[1].longValue());
        assertEquals(1L, output.getDirtyPatchesHoovered().longValue());
    }

    /**
     * Minimal test verifies that we never double count hoovered squares
     */
    @Test
    public void shouldNotDoubleCountHooveredSquares() {
        HooverInputDto input = new HooverInputDto()
                .setRoomSize(new Long[]{1L, 2L})
                .setStartPosition(new Long[]{0L, 0L})
                .setDirtyPatches(new Long[][]{
                        new Long[]{0L, 0L}})
                .setDirections("NS");

        HooverOutputDto output = hooverService.processInstruction(input);
        verify(hooverRepository, times(1)).createHooveringEvent(any(HooveringEvent.class));
        assertEquals(1L, output.getId().longValue());
        assertEquals(0L, output.getEndPosition()[0].longValue());
        assertEquals(0L, output.getEndPosition()[1].longValue());
        assertEquals(1L, output.getDirtyPatchesHoovered().longValue());
    }

    /**
     * Final test verifies that we can process a complex instruction - we cover all the cases
     */
    @Test
    public void shouldProcessComplexInstruction() {
        HooverInputDto input = new HooverInputDto()
                .setRoomSize(new Long[]{3L, 3L})
                .setStartPosition(new Long[]{1L, 0L})
                .setDirtyPatches(new Long[][]{
                        new Long[]{0L, 0L},
                        new Long[]{1L, 1L},
                        new Long[]{2L, 2L}})
                .setDirections("NNNNESWWW");

        HooverOutputDto output = hooverService.processInstruction(input);
        verify(hooverRepository, times(1)).createHooveringEvent(any(HooveringEvent.class));
        assertEquals(1L, output.getId().longValue());
        assertEquals(0L, output.getEndPosition()[0].longValue());
        assertEquals(1L, output.getEndPosition()[1].longValue());
        assertEquals(2L, output.getDirtyPatchesHoovered().longValue());
    }

}
