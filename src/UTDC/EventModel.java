package UTDC;

import java.time.LocalDateTime;
import java.util.List;

public class EventModel {

    LocalDateTime date;
    List<String> people_envolved;
    String description;
    String local;

    public EventModel(LocalDateTime date, List<String> people_envolved, String description, String local) {
        this.date = date;
        this.people_envolved = people_envolved;
        this.description = description;
        this.local = local;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<String> getPeople_envolved() {
        return people_envolved;
    }

    public void setPeople_envolved(List<String> people_envolved) {
        this.people_envolved = people_envolved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
