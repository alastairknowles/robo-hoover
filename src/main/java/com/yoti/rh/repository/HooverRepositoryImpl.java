package com.yoti.rh.repository;

import com.yoti.rh.domain.HooveringEvent;
import org.dizitart.no2.WriteResult;
import org.dizitart.no2.objects.ObjectRepository;
import org.springframework.stereotype.Repository;

@Repository
public class HooverRepositoryImpl implements HooverRepository {

    private final ObjectRepository<HooveringEvent> repository;

    public HooverRepositoryImpl(ObjectRepository<HooveringEvent> repository) {
        this.repository = repository;
    }

    @Override
    public HooveringEvent createHooveringEvent(HooveringEvent hooveringEvent) {
        WriteResult result = repository.insert(hooveringEvent);
        return hooveringEvent.setId(result.iterator().next().getIdValue());
    }

}
