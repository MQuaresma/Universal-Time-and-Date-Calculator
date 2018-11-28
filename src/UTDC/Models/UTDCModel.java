package UTDC.Models;

import java.awt.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

public class UTDCModel implements Serializable {

    //TODO<Feature>: implement map between user and agenda
    List<EventModel> events;

    public UTDCModel(List<EventModel> events){
        this.events = events;
    }

    public void addEvent(EventModel em){
        this.events.add(em);
    }

    public void removeEvent(EventModel em){
        this.events.remove(em);
    }

    public void removeEventAt(LocalDateTime timestamp){
        this.events = this.events.stream()
                .filter(ev -> !ev.getDate().equals(timestamp))
                .collect(Collectors.toList());
    }

    public List<String> getShortEvents(){
        return this.events.stream().map(e -> e.getInfoShort()).collect(Collectors.toList());
    }

    public List<String> getDetailsEvents(){
        return this.events.stream().map(e -> e.getInfoDetails()).collect(Collectors.toList());
    }

    public List<String> getFutureEvents(LocalDateTime now){
        return this.events.stream()
                          .filter(e -> e.getDate().isAfter(now))
                          .map(e -> e.getInfoShort())
                          .collect(Collectors.toList());
    }

    public List<String> getPastEvents(LocalDateTime now){
        return this.events.stream()
                .filter(e -> e.getDate().isBefore(now))
                .map(e -> e.getInfoShort())
                .collect(Collectors.toList());
    }

    public List<String> getEventsTimeInterval (LocalDateTime date1,LocalDateTime date2){
        return this.events.stream()
                .filter(e -> e.getDate().isBefore(date2) && e.getDate().isAfter(date1))
                .map(e -> e.getInfoShort())
                .collect(Collectors.toList());
    }

    public List<String> getEventsByTitle(String title){
        return this.events.stream()
                .filter(e -> e.getTitle().equals(title))
                .map(e -> e.getInfoShort())
                .collect(Collectors.toList());
    }

    public List<String> getEventsByDescription(String description){
        return this.events.stream()
                .filter(e -> e.getDescription().contains(description))
                .map(e -> e.getInfoShort())
                .collect(Collectors.toList());
    }

    //TODO: more flexible search to include LocalDateTime during the event (init + duration)
    public List<String> getEventsByDate(LocalDateTime date){
        return this.events.stream()
                .filter(e -> e.getDate().equals(date))
                .map(e -> e.getInfoShort())
                .collect(Collectors.toList());
    }

    public List<String> getEventsByLocation(String location){
        return this.events.stream()
                .filter(e -> e.getLocal().contains(location))
                .map(e -> e.getInfoShort())
                .collect(Collectors.toList());
    }

    public boolean containsEvent(LocalDateTime date){
        return this.events.stream().anyMatch(e -> e.getDate().equals(date));
    }

    public EventModel getEvent(LocalDateTime date){
        EventModel event = null;
        for (EventModel em : this.events){
            if (em.getDate().equals(date)) event = em;
        }
        return event;
    }
}

