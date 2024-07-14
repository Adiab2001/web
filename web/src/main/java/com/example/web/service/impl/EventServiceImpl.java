package com.example.web.service.impl;

import com.example.web.Repo.ClubRepo;
import com.example.web.Repo.EventRepo;
import com.example.web.dto.EventDto;
import com.example.web.models.Club;
import com.example.web.models.Event;
import com.example.web.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.web.mappers.EventMapper.mapToEvent;
import static com.example.web.mappers.EventMapper.mapToEventDto;

@Service
public class EventServiceImpl implements EventService {
    private EventRepo eventRepo;
    private ClubRepo clubRepo;

    @Autowired

    public EventServiceImpl(EventRepo eventRepo, ClubRepo clubRepo) {
        this.eventRepo = eventRepo;
        this.clubRepo = clubRepo;
    }

    @Override
    public void createEvent(Long clubId, EventDto eventDto) {
        Club club = clubRepo.findById(clubId).get();
        Event event = mapToEvent(eventDto);
        event.setClub(club);
        eventRepo.save(event);
    }

    public List<EventDto> findAllEvents(){
        List<Event> events = eventRepo.findAll();
        return events.stream().map((event) -> mapToEventDto(event)).collect(Collectors.toList());

    }

    @Override
    public EventDto findById(Long eventId) {
        Event event = eventRepo.findById(eventId).get();
        return mapToEventDto(event);

    }


}
