package UTDC.Controllers;

import UTDC.Input;
import UTDC.Models.EventModel;
import UTDC.Models.UTDCModel;
import UTDC.Views.Menu;
import UTDC.Views.UTDCView;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.time.DayOfWeek;
import java.time.temporal.TemporalAdjuster;
import java.util.*;
import java.util.stream.Collectors;

public class ManagementController implements ControllerInterface {

    private UTDCModel model;
    private String username;

    public void setView(UTDCView v){
    }

    public void setModel(UTDCModel m){
        this.model = m;
    }

    public void startFlow(Menu menu){
        Menu initMenu = UTDCView.managementLoginRegist();
        String opcao, user, password, name;
        do {
            initMenu.show();
            opcao = Input.lerString().toUpperCase();
            switch (opcao){
                case "L":
                    System.out.print("Username: ");
                    user = Input.lerString();
                    System.out.print("Password: ");
                    password = Input.lerString();
                    int login = model.login(user, password);
                    if (login == 1){
                        this.username = user;
                        System.out.println("Login completed successfully!\nWelcome " + model.getUserName(user) + ".");
                        this.managementUserFlow(menu);
                    }
                    else if (login == 2) System.out.println("The username introduced does not exist!");
                    else System.out.println("The password introduced is wrong!");
                    break;
                case "R":
                    System.out.print("Username: ");
                    user = Input.lerString();
                    System.out.print("Name: ");
                    name = Input.lerString();
                    System.out.print("Password: ");
                    password = Input.lerString();
                    int r = model.registUser(user, name, password);
                    if (r == 1) System.out.println("Registration completed successfully!");
                    else System.out.println("The introduced username already exists!");
                    break;
                case "M":
                    break;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        } while (!opcao.equals("M"));

    }

    public void managementUserFlow(Menu menu){
        String opcao;
        do {
            menu.show();
            opcao = Input.lerString().toUpperCase();
            switch (opcao) {
                case "L":
                    this.checkEvents();
                    break;
                case "N":
                    this.addEvent();
                    break;
                case "R":
                    this.removeEvent();
                    break;
                case "C":
                    this.changeEvent();
                    break;
                case "D":
                    this.appointmentDetails();
                    break;
                case "B":
                    break;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        } while (!opcao.equals("B"));
    }

    private void checkEvents(){
        Menu menu = UTDCView.checkEventsMenu();
        menu.show();
        String op;
        op = Input.lerString().toUpperCase();
        switch(op){
            case "A":
                List<String> event_info = this.model.getShortEvents(this.username);
                UTDCView.printColletion("*** Event Summary ***", event_info);
                break;
            case "F":
                LocalDateTime after = LocalDateTime.now();
                List<String> future_info = this.model.getFutureEvents(this.username,after);
                UTDCView.printColletion("*** Upcoming events ***", future_info);
                break;
            case "D":
                LocalDate input;
                LocalDateTime start, end;
                System.out.print("Initial date: ");
                input = Input.lerDate();
                start= input.atTime(0,0,0);
                end = input.atTime(23,59,59);
                List<String> events_date = this.model.getEventsTimeInterval(this.username,start,end);
                UTDCView.printColletion("*** Events in given date ***",events_date );
                break;
            case "U":
                this.listEventsInUpcomingTimeframe();
                break;
            case "P":
                LocalDateTime before = LocalDateTime.now();
                List<String> past_info = this.model.getPastEvents(this.username,before);
                UTDCView.printColletion("*** Past events ***", past_info);
                break;
            case "W":
                List<String> week_days = Arrays.asList(DayOfWeek.values())
                                            .stream()
                                            .map(d -> d.getDisplayName(TextStyle.FULL, Locale.ENGLISH))
                                            .collect(Collectors.toList());
                UTDCView.printColletion("Weekdays", week_days);
                System.out.print("Weekday: ");
                DayOfWeek w_day = Input.lerWeekDay();
                List<String> events_weekday = this.model.getEventsByDayOfWeek(this.username,w_day);
                UTDCView.printColletion("*** Events at " + w_day.toString() + " ***", events_weekday);
                break;
            default:
                break;
        }
    }

    private void listEventsInUpcomingTimeframe(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime frame_end = now;
        List<ChronoUnit> available = new ArrayList<>();
        List<String> available_s = new ArrayList<>();
        ChronoUnit cu;
        long amount;
        int i = 1;

        for(ChronoUnit c : ChronoUnit.values()){
            if(now.isSupported(c)) {
                available_s.add(i + "-" + c.toString());
                available.add(c);
                i ++;
            }
        }

        String prompt = "Which units would you like to use? (enter each unit separated by a space)";
        UTDCView.printColletion(prompt, available_s);
        String[] units = Input.lerString().split("\\s+");
        for(String code:units){
            try{
                cu = available.get(Integer.parseInt(code) - 1);
                System.out.print(cu + ":");
                amount = Input.lerLong();
                frame_end = frame_end.plus(amount, cu);
            }catch(IndexOutOfBoundsException e){
                System.out.println(code + " is not a valid option!");
            }
        }

        List<String> upcoming_events = this.model.getEventsTimeInterval(this.username, now, frame_end);
        UTDCView.printColletion("*** Upcoming Events ***", upcoming_events);
    }

    private void addEvent(){
        System.out.print("Insert a title for the event: ");
        String title = Input.lerString();
        System.out.print("Insert a description for the event: ");
        String description = Input.lerString();
        System.out.print("Insert the local where the event will take place: ");
        String local = Input.lerString();
        System.out.print("Insert separeted by commas the list of people related to the event: ");
        String[] people = Input.lerString().split("\\s*,\\s*");
        List<String> people_envolved = new ArrayList<String>(Arrays.asList(people));
        System.out.print("Which is the Date-Time when the event begin: ");
        LocalDateTime date = Input.lerDateTime();

        EventModel em = new EventModel(date,people_envolved,title,description,local);

        LocalDateTime end = date;
        List<ChronoUnit> available = new ArrayList<>();
        List<String> available_s = new ArrayList<>();
        int i = 1;

        for(ChronoUnit c : ChronoUnit.values()){
            if(date.isSupported(c)) {
                available_s.add(i + " - " + c.toString());
                available.add(c);
                i ++;
            }
        }
        ChronoUnit cu;
        long amount;
        String prompt = "Which units would you like to use to set the duration of the event? (enter each unit separated by a space)";
        UTDCView.printColletion(prompt, available_s);
        String[] units = Input.lerString().split("\\s+");
        for(String code : units){
            try{
                cu = available.get(Integer.parseInt(code) - 1);
                System.out.print(cu + ": ");
                amount = Input.lerLong();
                end = end.plus(amount, cu);
            }catch(IndexOutOfBoundsException e){
                System.out.println(code + " is not a valid option!");
            }
        }
        em.setDuration(Duration.between(date,end));

        List<EventModel> events = this.model.getEventsRaw(this.username);
        boolean valid=true;

        for(EventModel ev: events){
            LocalDateTime temp_start = ev.getDate();
            LocalDateTime temp_end = temp_start.plus(ev.getDuration());
            valid = date.isAfter(temp_end) || end.isBefore(temp_start);
            if(!valid) break;
        }

        if(valid) {
            this.model.addEvent(this.username, em);
            System.out.println("Event added successfully with the following properties:");
            System.out.println(em.getInfoDetails());
        }else{
            System.out.println("Event overlaps other events");
        }
    }

    private void removeEvent(){
        List<String> ev = this.listEventsByCriteria();
        if(ev.size()<=0){
            System.out.println("No events match the criteria");
        }else{
            UTDCView.printColletion("*** Events matching the criteira ***", ev);
            System.out.print("Insert the exact date of the event you'd like to remove: ");
            LocalDateTime timestamp = Input.lerDateTime();
            if(!this.model.containsEvent(username, timestamp))
                System.out.println("No event found with given Date-Time");
            else {
                this.model.removeEventAt(this.username, timestamp);
                System.out.println("Event removed!");
            }
        }
    }

    private List<String> listEventsByCriteria(){
        List<String> matches = new ArrayList<>();
        String op;
        String search_param;

        Menu menu = UTDCView.eventPropertyMenu();

        menu.show();
        op = Input.lerString().toUpperCase();
        switch(op){
            case "T":
                System.out.print("Title: ");
                search_param = Input.lerString();
                matches = this.model.getEventsByTitle(this.username,search_param);
                break;
            case "D":
                System.out.print("Description: ");
                search_param = Input.lerString();
                matches = this.model.getEventsByDescription(this.username,search_param);
                break;
            case "L":
                System.out.print("Location: ");
                search_param = Input.lerString();
                matches = this.model.getEventsByLocation(this.username,search_param);
                break;
            case "W":
                System.out.print("Date-Time: ");
                LocalDateTime ev_date = Input.lerDateTime();
                matches = this.model.getEventsByDate(this.username,ev_date);
                break;
            default:
                break;
        }
        return matches;
    }

    private void changeEvent(){
        System.out.print("Insert the Date-Time of the event you want to change: ");
        LocalDateTime date = Input.lerDateTime();
        if (!this.model.containsEvent(this.username,date)){
            System.out.println("There is not an event with such Date-Time!");
        }else{
            EventModel em = this.model.getEvent(this.username,date);
            Menu change = UTDCView.changeEventPropertyMenu();
            change.show();
            String op = Input.lerString();
            switch (op){
                case "1":
                    System.out.println("The event title is set to: " + em.getTitle());
                    System.out.print("Insert the new title: ");
                    String title = Input.lerString();
                    em.setTitle(title);
                    System.out.println("Event title changed with success.");
                    break;
                case "2":
                    System.out.println("The event Date-Time is set to: " + em.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
                    System.out.print("Insert the new date: ");
                    LocalDateTime new_date = Input.lerDateTime();
                    em.setDate(new_date);
                    System.out.println("Event date changed with success.");
                    break;
                case "3":
                    System.out.println("The event description is set to: " + em.getDescription());
                    System.out.print("Insert the new description: ");
                    String description = Input.lerString();
                    em.setDescription(description);
                    System.out.println("Event description changed with success.");
                    break;
                case "4":
                    System.out.println("The event location is set to: " + em.getLocal());
                    System.out.print("Insert the new location: ");
                    String local = Input.lerString();
                    em.setLocal(local);
                    System.out.println("Event location changed with success.");
                    break;
                case "5":
                    UTDCView.printColletion("The list of people envolved in the event is set to: ", em.getPeople_envolved());
                    System.out.print("Insert the new list of people envolved separeted by commas: ");
                    String[] people = Input.lerString().split("\\s*,\\s*");
                    List<String> people_envolved = new ArrayList<String>(Arrays.asList(people));
                    em.setPeople_envolved(people_envolved);
                    System.out.println("Event list of people envolved changed with success.");
                    break;
                case "6":
                    this.changeEventDuration(em);
                    break;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }
    }

    private void changeEventDuration(EventModel em){
        System.out.println("The duration of the event is set to: " + em.getDuration());
        LocalDateTime date = em.getDate();
        LocalDateTime end = date;
        List<ChronoUnit> available = new ArrayList<>();
        List<String> available_s = new ArrayList<>();
        int i = 1;

        for(ChronoUnit c : ChronoUnit.values()){
            if(end.isSupported(c)) {
                available_s.add(i + " - " + c.toString());
                available.add(c);
                i ++;
            }
        }

        ChronoUnit cu;
        long amount;
        String prompt = "Which units would you like to use to set the duration of the event? (enter each unit separated by a space)";
        UTDCView.printColletion(prompt, available_s);
        String[] units = Input.lerString().split("\\s+");
        for(String code : units){
            try{
                cu = available.get(Integer.parseInt(code) - 1);
                System.out.print(cu + ": ");
                amount = Input.lerLong();
                end = end.plus(amount, cu);
            }catch(IndexOutOfBoundsException e){
                System.out.println(code + " is not a valid option!");
            }
        }
        em.setDuration(Duration.between(date,end));
        System.out.println("Event duration changed with success.");
    }

    private void appointmentDetails(){
        System.out.print("Insert the Date-Time of the event you want to check the details: ");
        LocalDateTime date = Input.lerDateTime();
        if (!this.model.containsEvent(this.username,date)){
            System.out.println("There is not an event with such Date-Time!");
        }else{
            EventModel event = this.model.getEvent(this.username,date);
            String details = event.getInfoDetails();
            System.out.println(details);
        }
    }
}
