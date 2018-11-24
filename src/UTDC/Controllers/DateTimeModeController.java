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
        List<ChronoUnit> units = new ArrayList<>();
        List<String> units_s = new ArrayList<>();
        String prefix = "The following units are available to measure the difference between two";
        int i = 1;
        for (ChronoUnit unit : ChronoUnit.values()) {
            if (start.isSupported(unit)){
                units.add(unit);
                units_s.add(i + "-" + unit.toString());
                i ++;
            }
        }

        if (mode == 1) prefix = prefix + " dates:";
        else prefix = prefix + " times:";
        UTDCView.printColletion(prefix, units_s);

        String options = Input.lerString();
        String[] user_units = options.split("\\s+");

        ChronoUnit cu;
        for (String option : user_units){
            cu = units.get(Integer.parseInt(option) - 1);
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

        List<ChronoUnit> units = new ArrayList<>();
        List<String> units_s = new ArrayList<>();
        String prefix = "The following units are available to ";
        int i = 1;

        for(ChronoUnit unit : ChronoUnit.values()) {
            if (t.isSupported(unit)){
                units.add(unit);
                units_s.add(i + "-" + unit.toString());
                i ++;
            }
        }

        if (option.equals("3")) prefix = prefix + "add offset to a time:";
        else if (mode == 1) prefix = prefix + "add offset to a date:";
        else if (mode == 2) prefix = prefix + "subtract offset to a date:";
        UTDCView.printColletion(prefix, units_s);

        String options = Input.lerString();
        String[] user_units = options.split("\\s+");

        ChronoUnit cu;
        long l;
        for (String user_option : user_units){
            cu = units.get(Integer.parseInt(user_option) - 1);
            if (mode == 1) System.out.print(cu + " to add: ");
            else System.out.print(cu + " to subtract: ");
            l = Input.lerLong();
            if (mode ==1) t = t.plus(l,cu);
            else t = t.minus(l,cu);
        }
        return t;
    }
}
