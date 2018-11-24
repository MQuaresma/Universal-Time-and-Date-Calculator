package UTDC.Controllers;

import UTDC.Input;
import UTDC.Views.Menu;
import UTDC.Views.UTDCView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;

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
        UTDCView.optionsDates();
        String option = Input.lerString();
        Temporal start, end;
        switch (option){
            case "1":
                System.out.print("Initial date: ");
                start = Input.lerDate();
                System.out.print("End date: ");
                end = Input.lerDate();
                dbd_function(start,end,1);
                break;
            case "2":
                System.out.print("Initial date: ");
                start = Input.lerDateTime();
                System.out.print("End date: ");
                end = Input.lerDateTime();
                dbd_function(start,end,1);
                break;
            case "3":
                System.out.print("Initial time: ");
                start = Input.lerTime();
                System.out.print("End time: ");
                end = Input.lerTime();
                dbd_function(start,end,2);
            default:
                break;
        }
    }

    // mode = 1 -> Difference between dates; mode = 2 -> Difference between times
    public void dbd_function(Temporal start, Temporal end, int mode){
        List<ChronoUnit> unities = new ArrayList<>();
        for (ChronoUnit unit : ChronoUnit.values()) {
            if (start.isSupported(unit)) unities.add(unit);
        }
        if (mode == 1) System.out.println("The following unities are available to measure the difference between two dates:");
        else System.out.println("The following unities are available to measure the difference between two times:");
        UTDCView.imprimeUnidades(unities);

        String options = Input.lerString();
        String[] user_unities = options.split("\\s+");

        ChronoUnit cu;
        for (String option : user_unities){
            cu = unities.get(Integer.parseInt(option) - 1);
            System.out.println(cu.between(start,end) + " " + cu.toString());
        }
    }

    //mode = 1 -> Date plus offset; mode = 2 -> Date minus offset
    private Temporal dateOffset(int mode){
        System.out.println("Date/Time with offset");
        UTDCView.optionsDates();
        String option = Input.lerString();
        Temporal t = null;
        switch (option){
            case "1":
                System.out.print("Insert date: ");
                t = Input.lerDate();
                break;
            case "2":
                System.out.print("Insert date: ");
                t = Input.lerDateTime();
                break;
            case "3":
                System.out.print("Insert time: ");
                t = Input.lerTime();
                break;
            default:
                break;
        }
        List<ChronoUnit> unities = new ArrayList<>();
        for (ChronoUnit unit : ChronoUnit.values()) {
            if (t.isSupported(unit)) unities.add(unit);
        }
        if (option.equals("3")) System.out.println("The following unities are available to add offset to a time:");
        else if (mode == 1) System.out.println("The following unities are available to add offset to a date:");
        else if (mode == 2) System.out.println("The following unities are available to subtract offset to a date:");
        UTDCView.imprimeUnidades(unities);

        String options = Input.lerString();
        String[] user_unities = options.split("\\s+");

        ChronoUnit cu;
        long l;
        for (String user_option : user_unities){
            cu = unities.get(Integer.parseInt(user_option) - 1);
            if (mode == 1) System.out.print(cu + " to add: ");
            else System.out.print(cu + " to subtract: ");
            l = Input.lerLong();
            if (mode ==1) t = t.plus(l,cu);
            else t = t.minus(l,cu);
        }
        return t;
    }

    /*
    public void durationBetweenDates(){
        System.out.println("Duration between dates");
        UTDCView.optionsDates();
        String option = Input.lerString();

        switch (option) {
            case "1":
                dbd_LocalDate();
                break;
            case "2":
                dbd_LocalDateTime();
                break;
            default:
                break;
        }
    }

    public void dbd_LocalDate(){
        LocalDate start, end;
        System.out.print("Initial date: ");
        start = Input.lerDate();
        System.out.print("End date: ");
        end = Input.lerDate();
        UTDCView.unitiesOptionsLocalDate(1);

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

    public void dbd_LocalDateTime(){
        LocalDateTime start, end;
        System.out.print("Initial date: ");
        start = Input.lerDateTime();
        System.out.print("End date: ");
        end = Input.lerDateTime();
        UTDCView.unitiesOptionsLocalDateTime(1);

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
                case "5":
                    System.out.println(HOURS.between(start,end) + " Hours");
                    break;
                case "6":
                    System.out.println(MINUTES.between(start,end) + " Minutes");
                    break;
                case "7":
                    System.out.println(SECONDS.between(start,end) + " Seconds");
                    break;
                case "8":
                    System.out.println(NANOS.between(start,end) + " Nanoseconds");
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

        if (mode == 1) UTDCView.unitiesOptionsLocalDate(2);
        else UTDCView.unitiesOptionsLocalDate(3);

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
    */
}
