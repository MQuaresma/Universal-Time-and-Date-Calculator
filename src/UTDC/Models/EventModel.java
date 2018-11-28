package UTDC.Models;

import UTDC.Controllers.UTDCController;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EventModel {

    private LocalDateTime date;
    private Duration duration;
    List<String> people_envolved;
    String title;
    String description;
    String local;

    public EventModel(LocalDateTime date, List<String> people_envolved, String title, String description, String local) {
        this.date = date;
        this.people_envolved = people_envolved;
        this.title = title;
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

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfoShort(){
        StringBuilder sb = new StringBuilder();
        sb.append("Title: ").append(this.title).append("\n");
        sb.append("Date: ").append(this.date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))).append("\n");
        sb.append("Location: ").append(this.local).append("\n");

        return sb.toString();
    }

    public String getInfoDetails(){
        StringBuilder sb = new StringBuilder();
        sb.append("Title: ").append(this.title).append("\n");
        sb.append("Date: ").append(this.date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))).append("\n");
        sb.append("Duration: ").append(this.duration).append("\n");
        sb.append("Location: ").append(this.local).append("\n");
        sb.append("Details: ").append(this.description).append("\n");
        sb.append("Attendees: ");
        for(String name: this.people_envolved)
            sb.append(name).append(" ");

        return sb.toString();
    }
}
