package UTDC.Controllers;

import UTDC.Input;
import UTDC.Models.EventModel;
import UTDC.Models.UTDCModel;
import UTDC.Views.Menu;
import UTDC.Views.UTDCView;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class ManagementController implements ControllerInterface {
    private UTDCModel model;

    public void setView(UTDCView v){
    }

    public void setModel(UTDCModel m){
        this.model = m;
    }

    public void startFlow(Menu menu){
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
                    break;
                case "D":
                    break;
                case "M":
                    break;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        } while (!opcao.equals("M"));
    }

    private void checkEvents(){
        Menu menu = UTDCView.checkEventsMenu();
        menu.show();
        String op;
        op = Input.lerString();
        switch(op){
            case "A":
                List<String> event_info = this.model.getShortEvents();
                UTDCView.printColletion("*** Event Summary ***", event_info);
                break;
            case "F":
                LocalDateTime after = LocalDateTime.now();
                List<String> future_info = this.model.getFutureEvents(after);
                UTDCView.printColletion("*** Upcoming events ***", future_info);
                break;
            case "D":
                LocalDate input;
                LocalDateTime start, end;
                System.out.print("Initial date: ");
                input = Input.lerDate();
                start= input.atTime(0,0,0);
                end = input.atTime(23,59,59);
                List<String> events_date = this.model.getEventsTimeInterval(start,end);
                UTDCView.printColletion("*** Events in given date ***",events_date );
                break;
            case "U":
                this.listEventsInUpcomingTimeframe();
                break;
            case "P":
                LocalDateTime before = LocalDateTime.now();
                List<String> past_info = this.model.getPastEvents(before);
                UTDCView.printColletion("*** Past events ***", past_info);
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

        List<String> upcoming_events = this.model.getEventsTimeInterval(now, frame_end);
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
        this.model.addEvent(em);
        System.out.println("Event added successfully with the following properties:");
        System.out.println(em.getInfoDetails());
    }

    private void removeEvent(){
        List<String> ev = this.listEventsByCriteria();
        if(ev.size()<=0){
            System.out.println("No events match the criteria");
        }else{
            UTDCView.printColletion("*** Events matching the criteira ***", ev);
            System.out.print("Insert the exact date of the event you'd like to remove: ");
            LocalDateTime timestamp = Input.lerDateTime();

            this.model.removeEventAt(timestamp);
        }
    }


    private List<String> listEventsByCriteria(){
        List<String> matches = new ArrayList<>();
        String op;
        String search_param;

        Menu menu = UTDCView.eventPropertyMenu();

        menu.show();
        op = Input.lerString();
        switch(op){
            case "T":
                System.out.print("Title: ");
                search_param = Input.lerString();
                matches = this.model.getEventsByTitle(search_param);
                break;
            case "D":
                System.out.print("Description: ");
                search_param = Input.lerString();
                matches = this.model.getEventsByDescription(search_param);
                break;
            case "L":
                System.out.print("Location: ");
                search_param = Input.lerString();
                matches = this.model.getEventsByLocation(search_param);
                break;
            case "W":
                System.out.print("Date-Time: ");
                LocalDateTime ev_date = Input.lerDateTime();
                matches = this.model.getEventsByDate(ev_date);
                break;
            default:
                break;
        }
        return matches;
    }
}
