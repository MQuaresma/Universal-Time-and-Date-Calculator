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

    private void durationBetweenDates(){
        System.out.println("Duration between dates");
        Menu date_menu = UTDCView.dateFormats();
        date_menu.show();
        String option = Input.lerString();
        Temporal start, end;
        switch (option){
            case "1":
                System.out.print("Initial date ");
                start = Input.lerDate();
                System.out.print("End date ");
                end = Input.lerDate();
                this.dbd_function(start,end);
                break;
            case "2":
                System.out.print("Initial date: ");
                start = Input.lerDateTime();
                System.out.print("End date: ");
                end = Input.lerDateTime();
                this.dbd_function(start,end);
                break;
            case "3":
                System.out.print("Initial time: ");
                start = Input.lerTime();
                System.out.print("End time: ");
                end = Input.lerTime();
                this.dbd_function(start,end);
            default:
                break;
        }
    }

    private void dbd_function(Temporal start, Temporal end){
        List<ChronoUnit> units = this.available_chronounits(start);
        String options = Input.lerString();
        String[] user_units = options.split("\\s+");

        ChronoUnit cu;
        for (String option : user_units){
            cu = units.get(Integer.parseInt(option) - 1);
            System.out.println(cu.between(start,end) + " " + cu.toString());
        }
    }

    //List available ChronoUnits for a give Temporal object
    private List<ChronoUnit> available_chronounits(Temporal temp){
        List<ChronoUnit> units = new ArrayList<>();
        List<String> units_s = new ArrayList<>();
        String header = "The following units can be used to set/get the time difference.\n" +
                "Enter the desired ones separated by spaces";
        int i = 1;
        for (ChronoUnit unit : ChronoUnit.values()) {
            if (temp.isSupported(unit)){
                units.add(unit);
                units_s.add(i + "-" + unit.toString());
                i ++;
            }
        }

        UTDCView.printColletion(header, units_s);

        return units;
    }

    //mode = 1 -> Date plus offset; mode = 2 -> Date minus offset
    private Temporal dateOffset(int mode){
        System.out.println("Date/Time with offset");
        Menu date_menu = UTDCView.dateFormats();
        date_menu.show();
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

        List<ChronoUnit> units = this.available_chronounits(t);
        String options = Input.lerString();
        String[] user_units = options.split("\\s+");

        ChronoUnit cu;
        long l;
        for (String user_option : user_units){
            cu = units.get(Integer.parseInt(user_option) - 1);
            if (mode == 1) System.out.print(cu + " to add: ");
            else System.out.print(cu + " to subtract: ");
            l = Input.lerLong();
            t = t.plus((mode==1 ? 1 : -1)*l, cu);
        }
        return t;
    }
}
