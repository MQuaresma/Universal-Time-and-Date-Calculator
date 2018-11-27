package UTDC.Models;

import java.awt.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
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

}

