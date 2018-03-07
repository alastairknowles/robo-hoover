package com.yoti.rh.repository;

import com.yoti.rh.domain.HooveringEvent;
import org.dizitart.no2.objects.ObjectRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@SuppressWarnings("unchecked")
@RunWith(MockitoJUnitRunner.class)
public class HooverRepositoryUTest {

    @Mock
    private ObjectRepository<HooveringEvent> repository;

    private HooverRepositoryImpl hooverRepository;

    @Before
    public void setUp() {
        hooverRepository = new HooverRepositoryImpl(repository);
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(repository);
        reset(repository);
    }

    @Test
    public void shouldStoreHooveringEventReturningCreatedId() {
        HooveringEvent hooveringEvent = new HooveringEvent();
        hooverRepository.insertHooveringEvent(hooveringEvent);
        verify(repository, times(1)).insert(hooveringEvent);
    }

}
