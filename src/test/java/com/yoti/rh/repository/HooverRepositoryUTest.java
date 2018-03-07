package com.yoti.rh.repository;

import com.yoti.rh.domain.HooveringEvent;
import org.dizitart.no2.NitriteId;
import org.dizitart.no2.WriteResult;
import org.dizitart.no2.objects.ObjectRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@SuppressWarnings("unchecked")
@RunWith(MockitoJUnitRunner.class)
public class HooverRepositoryUTest {

    @Mock
    private ObjectRepository<HooveringEvent> repository;

    private HooverRepositoryImpl hooverRepository;

    @Before
    public void setUp() {
        WriteResult insertResult = mock(WriteResult.class);
        when(insertResult.iterator()).thenReturn(Collections.singletonList(NitriteId.createId(1L)).iterator());
        when(repository.insert(any(HooveringEvent.class))).thenReturn(insertResult);

        hooverRepository = new HooverRepositoryImpl(repository);
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(repository);
        reset(repository);
    }

    @Test
    public void shouldStoreHooveringEventReturningCreatedId() {
        HooveringEvent hooveringEvent = hooverRepository.createHooveringEvent(new HooveringEvent());
        verify(repository, times(1)).insert(hooveringEvent);
        Assert.assertEquals(1L, hooveringEvent.getId().longValue());
    }

}
