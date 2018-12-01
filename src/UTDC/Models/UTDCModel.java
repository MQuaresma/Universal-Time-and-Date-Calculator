package UTDC.Models;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UTDCModel implements Serializable {

    //Map<username,eventos>
    Map<String,List<EventModel>> events;
    //Map<username,user>
    Map<String,User> users;

    public UTDCModel(){
        this.events = new HashMap<>();
        this.users = new HashMap<>();
    }

    public UTDCModel(Map<String,List<EventModel>> events, Map<String,User> users){
        this.events = events;this.users = users;
    }

    // 1 -> sucesso; 2 -> não existe username; 3 -> password errada
    public int login(String username, String password){
        if (!users.containsKey(username)) return 2;
        else{
            User u = users.get(username);
            String pass = u.getPassword();
            if (!pass.equals(password)) return 3;
        }
        return 1;
    }

    public String getUserName(String username){
        return this.users.get(username).getName();
    }

    // 1 -> sucesso; 2 -> já existe username
    public int registUser(String username, String name, String password){
        if (users.containsKey(username)) return 2;
        else{
            this.users.put(username, new User(username, name, password));
            this.events.put(username, new ArrayList<>());
            return 1;
        }
    }

    public void addEvent(String username, EventModel em){
        this.events.get(username).add(em);
    }

    public void removeEvent(String username, EventModel em){
        this.events.get(username).remove(em);
    }

    public void removeEventAt(String username, LocalDateTime timestamp){
        List<EventModel> events = this.events.get(username).stream()
                .filter(ev -> !ev.getDate().equals(timestamp))
                .collect(Collectors.toList());
        this.events.remove(username);
        this.events.put(username, events);
    }

    public List<EventModel> getEventsRaw(String username){
        return this.events.get(username);
    }

    public List<String> getShortEvents(String username){
        return this.events.get(username).stream().map(e -> e.getInfoShort()).collect(Collectors.toList());
    }

    public List<String> getDetailsEvents(String username){
        return this.events.get(username).stream().map(e -> e.getInfoDetails()).collect(Collectors.toList());
    }

    public List<String> filter_map_Events(String username, Predicate<EventModel> p){
        return this.events.get(username).stream()
                                        .filter(e -> p.test(e))
                                        .map(e -> e.getInfoShort())
                                        .collect(Collectors.toList());
    }

    public List<String> getFutureEvents(String username, LocalDateTime now){
        return this.filter_map_Events(username, (e) -> e.getDate().isAfter(now));
    }

    public List<String> getPastEvents(String username, LocalDateTime now){
        return this.filter_map_Events(username, (e) -> e.getDate().isBefore(now));
    }

    public List<String> getEventsTimeInterval(String username, LocalDateTime date1, LocalDateTime date2){
        return this.filter_map_Events(username, (e) -> e.getDate().isBefore(date2) && e.getDate().isAfter(date1));
    }

    public List<String> getEventsByTitle(String username, String title){
        return  this.filter_map_Events(username, (e) -> e.getTitle().toLowerCase().contains(title.toLowerCase()));
    }

    public List<String> getEventsByDescription(String username, String description){
        return this.filter_map_Events(username, (e) -> e.getDescription().toLowerCase().contains(description.toLowerCase()));
    }

    public List<String> getEventsByDayOfWeek(String username, DayOfWeek dayOfWeek){
        return this.filter_map_Events(username, (e) -> e.getDayOfWeek().equals(dayOfWeek));
    }

    public List<String> getEventsByDate(String username, LocalDateTime date){
        return this.filter_map_Events(username, e -> e.getDate().equals(date));
    }

    public List<String> getEventsByLocation(String username, String location){
        return this.filter_map_Events(username, e -> e.getLocal().toLowerCase().contains(location.toLowerCase()));
    }

    public List<String> getEventsByAttendees(String username, String attendee){
        return this.filter_map_Events(username, e -> e.getPeople_envolved().contains(attendee));
    }

    public boolean containsEvent(String username, LocalDateTime date){
        return this.events.get(username).stream().anyMatch(e -> e.getDate().equals(date));
    }

    public EventModel getEvent(String username, LocalDateTime date){
        EventModel event = null;
        for (EventModel em : this.events.get(username)){
            if (em.getDate().equals(date)) event = em;
        }
        return event;
    }
}

