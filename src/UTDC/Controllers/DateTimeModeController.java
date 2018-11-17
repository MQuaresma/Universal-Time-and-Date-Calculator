package UTDC.Controllers;

import UTDC.Input;
import UTDC.Views.Menu;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.*;

public class DateTimeModeController implements ControllerInterface {
    public void startFlow(Menu menu){
        String opcao;
        do {
            menu.show();
            opcao = Input.lerString().toUpperCase();
            switch (opcao) {
                case "D":
                    System.out.println(durationBetweenDates());
                    break;
                case "A":
                    System.out.println(datePlusAmount());
                    break;
                case "S":
                    // TODO
                    break;
                case "M":
                    break;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        } while (!opcao.equals("M"));
    }

    private long durationBetweenDates(){
        System.out.println("Duration between dates");
        System.out.println("Date format: Year-Month-Day");
        LocalDate start, end;
        start = Input.lerDate();
        end = Input.lerDate();
        // TODO: allow for different time units of measure
        long dt = DAYS.between(start, end);
        return dt;
    }

    private LocalDate datePlusAmount(){
        System.out.println("Date with offset");
        System.out.println("Date format: Year-Month-Day");
        LocalDate start = Input.lerDate();
        long offset = Input.lerInt();
        return start.plusDays(offset);
    }
}
