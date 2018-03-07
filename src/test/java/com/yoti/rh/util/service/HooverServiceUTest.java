package com.yoti.rh.util.service;

import com.yoti.rh.domain.HooveringEvent;
import com.yoti.rh.repository.HooverRepository;
import com.yoti.rh.service.HooverServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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

    @Test
    public void shouldProcessInstruction() {

    }

}
