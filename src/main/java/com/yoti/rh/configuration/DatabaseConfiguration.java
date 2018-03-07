package com.yoti.rh.configuration;

import com.yoti.rh.domain.HooveringEvent;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

@Configuration
public class DatabaseConfiguration {

    private Nitrite datastore;

    @Bean
    public ObjectRepository<HooveringEvent> repository() {
        datastore = Nitrite.builder().openOrCreate();
        return datastore.getRepository(HooveringEvent.class);
    }

    @PreDestroy
    public void tearDown() {
        this.datastore.close();
    }

}
