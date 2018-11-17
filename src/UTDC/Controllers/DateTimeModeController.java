package UTDC.Controllers;

import UTDC.Input;
import UTDC.Views.Menu;
import UTDC.Views.UTDCView;

import java.time.LocalDate;
import java.time.Month;

import static java.time.temporal.ChronoUnit.*;

public class DateTimeModeController implements ControllerInterface {
    public void startFlow(Menu menu){
        String opcao;
        do {
            menu.show();
            opcao = Input.lerString().toUpperCase();
            switch (opcao) {
                case "D":
                    durationBetweenDates();
                    break;
                case "A":
                    System.out.println("Result: " + dateOffset(1));
                    break;
                case "S":
                    System.out.println("Result: " + dateOffset(2));
                    break;
                case "M":
                    break;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        } while (!opcao.equals("M"));
    }

    public void durationBetweenDates(){
        System.out.println("Duration between dates");
        System.out.println("Date format: Year-Month-Day");
        LocalDate start, end;
        System.out.print("Initial date: ");
        start = Input.lerDate();
        System.out.print("End date: ");
        end = Input.lerDate();
        UTDCView.unitiesOptions(1);

        String options = Input.lerString();
        String[] unities = options.split("\\s+");

        for (String option : unities){
            switch (option){
                case "1":
                    System.out.println(YEARS.between(start,end) + " Years");
                    break;
                case "2":
                    System.out.println(MONTHS.between(start,end) + " Months");
                    break;
                case "3":
                    System.out.println(DAYS.between(start,end) + " Days");
                    break;
                case "4":
                    System.out.println(WEEKS.between(start,end) + " Weeks");
                    break;
                default: break;
            }
        }
    }

    //mode = 1 -> Date plus offset; mode = 2 -> Date minus offset
    private LocalDate dateOffset(int mode){
        System.out.println("Date with offset");
        System.out.println("Date format: Year-Month-Day");
        System.out.print("Date: ");
        LocalDate start = Input.lerDate();

        if (mode == 1) UTDCView.unitiesOptions(2);
        else UTDCView.unitiesOptions(3);

        String options = Input.lerString();
        String[] unities = options.split("\\s+");

        long years = 0; long months = 0; long days = 0; long weeks = 0;
        for (String option : unities){
            switch (option){
                case "1":
                    if (mode == 1) System.out.print("Years to add: ");
                    else System.out.print("Years to subtract: ");
                    years = Input.lerLong();
                    break;
                case "2":
                    if (mode == 1) System.out.print("Months to add: ");
                    else System.out.print("Months to subtract: ");
                    months = Input.lerLong();
                    break;
                case "3":
                    if (mode == 1) System.out.print("Days to add: ");
                    else System.out.print("Days to subtract: ");
                    days = Input.lerLong();
                    break;
                case "4":
                    if (mode == 1) System.out.print("Weeks to add: ");
                    else System.out.print("Weeks to subtract: ");
                    weeks = Input.lerLong();
                    break;
                default: break;
            }
        }

        if (mode == 1) return start.plusYears(years).plusMonths(months).plusDays(days).plusWeeks(weeks);
        else return start.minusYears(years).minusMonths(months).minusDays(days).minusWeeks(weeks);
    }
}
