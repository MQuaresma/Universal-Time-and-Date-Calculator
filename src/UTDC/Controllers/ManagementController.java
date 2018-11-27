package UTDC.Controllers;

import UTDC.Input;
import UTDC.Models.UTDCModel;
import UTDC.Views.Menu;
import UTDC.Views.UTDCView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.List;

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
                    this.checkAppointments();
                    break;
                case "N":
                    break;
                case "R":
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

    private void checkAppointments(){
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
                LocalDate input= null;
                LocalDateTime start, end;
                System.out.print("Initial date: ");
                input = Input.lerDate();
                start= input.atTime(0,0,0);
                end = input.atTime(23,59,59);
                List<String> events_date = this.model.getEventsTimeInterval(start,end);
                UTDCView.printColletion("*** Events in given date ***",events_date );
                break;
            case "U":
                //TODO: events in less than x amount of time
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
}
