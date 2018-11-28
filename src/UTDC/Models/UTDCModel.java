package UTDC.Models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;
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

    public List<EventModel> getEventsRaw(){
        return this.events;
    }

    public List<String> getShortEvents(){
        return this.events.stream().map(e -> e.getInfoShort()).collect(Collectors.toList());
    }

    public List<String> getDetailsEvents(){
        return this.events.stream().map(e -> e.getInfoDetails()).collect(Collectors.toList());
    }

    public List<String> filter_map_Events(Predicate<EventModel> p){
        return this.events.stream()
                .filter(e -> p.test(e))
                .map(e -> e.getInfoShort())
                .collect(Collectors.toList());
    }

    public List<String> getFutureEvents(LocalDateTime now){
        return this.filter_map_Events((e) -> e.getDate().isAfter(now));
    }

    public List<String> getPastEvents(LocalDateTime now){
        return this.filter_map_Events((e) -> e.getDate().isBefore(now));
    }

    public List<String> getEventsTimeInterval(LocalDateTime date1, LocalDateTime date2){
        return this.filter_map_Events((e) -> e.getDate().isBefore(date2) && e.getDate().isAfter(date1));
    }

    public List<String> getEventsByTitle(String title){
        return  this.filter_map_Events((e) -> e.getTitle().equals(title));
    }

    public List<String> getEventsByDescription(String description){
        return this.filter_map_Events((e) -> e.getDescription().contains(description));
    }

    //TODO: more flexible search to include LocalDateTime during the event (init + duration)
    public List<String> getEventsByDate(LocalDateTime date){
        return this.filter_map_Events(e -> e.getDate().equals(date));
    }

    public List<String> getEventsByLocation(String location){
        return this.filter_map_Events(e -> e.getLocal().contains(location));
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

