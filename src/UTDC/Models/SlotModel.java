package UTDC.Models;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class SlotModel {

    private LocalDateTime date;
    private Duration duration;
    private List<EventModel> events;

    public SlotModel(LocalDateTime date, Duration duration, List<EventModel> events) {
        this.date = date;
        this.duration = duration;
        this.events = events;
    }

    public SlotModel(LocalDateTime date, Duration duration){
        this.date = date;
        this.duration = duration;
        this.events = new ArrayList<EventModel>();
    }

    public void addEvent(EventModel em){
        this.events.add(em);
    }

    public void removeEvent(EventModel em){
        this.events.remove(em);
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public List<EventModel> getEvents() {
        return events;
    }

    public void setEvents(List<EventModel> events) {
        this.events = events;
    }
}
