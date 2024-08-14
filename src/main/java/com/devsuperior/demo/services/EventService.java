package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.dto.EventRequestDto;
import com.devsuperior.demo.entities.Event;
import com.devsuperior.demo.repository.CityRepository;
import com.devsuperior.demo.repository.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CityRepository cityRepository;

    @Transactional(readOnly = true)
    public Page<EventDTO> findAllEvents(Pageable pageable) {
        return eventRepository.findAll(pageable).map(EventDTO::new);
    }

    @Transactional
    public EventDTO insert(EventRequestDto dto) {
        Event event = new Event();
        dtoToEntity(dto, event);
        event = eventRepository.save(event);
        return new EventDTO(event);
    }


    private void dtoToEntity(EventRequestDto dto, Event event) {
        try {
            event.setCity(cityRepository.getReferenceById(dto.cityId()));
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
        event.setName(dto.name());
        event.setDate(dto.date());
        event.setUrl(dto.url());
    }


}
