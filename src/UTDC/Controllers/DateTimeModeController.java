package UTDC.Controllers;

import UTDC.Input;
import UTDC.Models.UTDCModel;
import UTDC.Views.Menu;
import UTDC.Views.ViewInterface;
import UTDC.Views.DateTimeView;

import java.text.DecimalFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.*;
import java.util.*;
import java.util.function.Function;

public class DateTimeModeController implements ControllerInterface {
    private DateTimeView view;

    public void setView(ViewInterface v){
        this.view = (DateTimeView) v;
    }

    public void setModel(UTDCModel m){
    }

    public void startFlow(){
        Menu menu = this.view.getMenu(1);

        String opcao;
        do {
            menu.show();
            opcao = Input.lerString().toUpperCase();
            switch (opcao) {
                case "D":
                    this.durationBetweenDates(1);
                    break;
                case "U":
                    this.durationBetweenDates(2);
                    break;
                case "A":
                    System.out.println("Result: " + timeToString(dateOffset(1)));
                    break;
                case "S":
                    System.out.println("Result: " + timeToString(dateOffset(2)));
                    break;
                case "L":
                    this.lifeStats();
                    break;
                case "M":
                    break;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        } while (!opcao.equals("M"));
    }

    //mode = 1 -> simple, mode = 2 -> units
    private void durationBetweenDates(int mode){
        if (mode == 1) System.out.println("Duration between dates");
        else System.out.println("Duration between dates in specific units");
        Menu date_menu = this.view.dateFormats(1);
        date_menu.show();
        String option = Input.lerString();
        Temporal start, end;
        switch (option){
            case "1":
                System.out.print("Initial date: ");
                start = Input.lerDate();
                System.out.print("End date: ");
                end = Input.lerDate();
                if (mode == 1) this.durationBetweenDatesSimple(start, end);
                else this.dbd_function(start,end);
                break;
            case "2":
                System.out.print("Initial date: ");
                start = Input.lerDateTime();
                System.out.print("End date: ");
                end = Input.lerDateTime();
                if (mode == 1) this.durationBetweenDatesSimple(start, end);
                else this.dbd_function(start,end);
                break;
            case "3":
                System.out.print("Initial time: ");
                start = Input.lerTime();
                System.out.print("End time: ");
                end = Input.lerTime();
                if (mode == 1) this.durationBetweenDatesSimple(start, end);
                else this.dbd_function(start,end);
            default:
                break;
        }
    }

    private void durationBetweenDatesSimple(Temporal start, Temporal end){
        ChronoUnit[] c_units = {ChronoUnit.YEARS, ChronoUnit.MONTHS, ChronoUnit.DAYS, ChronoUnit.HOURS, ChronoUnit.MINUTES, ChronoUnit.SECONDS};
        long[] values = new long[6];
        int i = 0;
        Temporal temp = start;
        for (ChronoUnit cu : c_units){
            if (temp.isSupported(cu)){
                values[i] = temp.until(end, cu);
                temp = temp.plus(values[i], cu);
            }
            else values[i] = 0;
            i++;
        }
        String r = "";
        for (int j=0; j<6; j++){
            if (values[j] != 0) r += values[j] + " " + c_units[j] + " ";
        }
        System.out.println(r);
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

    private void lifeStats(){
        System.out.println("Life Analytics");
        Menu date_menu = this.view.dateFormats(0);
        Menu stats_menu = this.view.getMenu(2);
        String option=null;
        LocalDate t = null, bday = null, now=null;
        boolean ok=false;

        System.out.print("Insert birthday: ");
        bday = Input.lerDate();

        do{
            stats_menu.show();
            option = Input.lerString().toUpperCase();

            switch(option){
                case "A":
                    while (!ok){
                        System.out.print("Insert date: ");
                        t = Input.lerDate();
                        now = LocalDate.now();
                        ok = t.isAfter(bday); //Ensure dates are in the the right order
                        if (!ok) System.out.println("Please ensure your birthday precedes the other date");
                    }
                    this.dbd_function(bday, t);
                    break;
                case "W":
                    this.nextBirthday(bday, now);
                    break;
                case "D":
                    this.birthdays_on_daysofweek(bday, now);
                    break;
                case "L":
                    this.leapYearCount(bday, now);
                    break;
                case "M":
                    break;
                default:
                    System.out.println("Invalid option, try again!");
                    break;
            }
        }while(!option.equals("M"));
    }

    private void birthdays_on_daysofweek(LocalDate bday, LocalDate now){
        LocalDate day_of_week_adj;
        MonthDay temp_m_d;
        MonthDay bday_m_d = MonthDay.of(bday.getMonth(), bday.getDayOfMonth());
        TemporalAdjuster adj;
        Map<DayOfWeek, Integer> freq = new HashMap<>();
        List<String> freq_s = new ArrayList<>();
        Function<DayOfWeek, String> freq_to_string = (dow) -> dow.getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " - ";
        int total_bday=0, dow_counter;

        for(DayOfWeek dow : DayOfWeek.values()){
            dow_counter = 0;
            adj = TemporalAdjusters.nextOrSame(dow);
            day_of_week_adj = bday.with(adj);
            while(day_of_week_adj.isBefore(now)){
                temp_m_d = MonthDay.of(day_of_week_adj.getMonth(), day_of_week_adj.getDayOfMonth());
                if(temp_m_d.equals(bday_m_d)){
                    total_bday ++;
                    dow_counter ++;
                }
                day_of_week_adj = day_of_week_adj.plusWeeks(1);
            }
            freq.put(dow, dow_counter);
        }

        for(DayOfWeek dow : freq.keySet()){
            double relative_freq = freq.get(dow) * 100.f / total_bday;
            freq_s.add(freq_to_string.apply(dow) + new DecimalFormat("#0.00").format(relative_freq) + "%");
        }

        this.view.printColletion("*** Day of week frequency table ***", freq_s);
    }

    private void leapYearCount(LocalDate bday, LocalDate now){
        LocalDate temp=bday;
        List<String> leap_years = new ArrayList<>();
        int counter = 0;

        while(temp.isBefore(now)){
            if(temp.isLeapYear()){
                counter ++;
                leap_years.add(String.valueOf(temp.getYear()));
            }
            temp = temp.plusYears(1);
        }
        System.out.println("Since you we're born there have been " + counter + " leap years.");
        this.view.printColletion("*** Leap years ***", leap_years);
    }

    private void nextBirthday(LocalDate bday, LocalDate now){
        MonthDay bday_m_d = MonthDay.of(bday.getMonth(), bday.getDayOfMonth());
        LocalDate next_bday = now.with(bday_m_d);

        if(next_bday.isBefore(now))
            next_bday = next_bday.with(ChronoField.YEAR, now.getYear()+1);

        System.out.print("Your next birthday (" );
        System.out.print(next_bday.format(DateTimeFormatter.ofPattern(UTDCController.local_date_format)));
        System.out.println(") is in a " + next_bday.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH));
    }

    //mode = 1 -> Date plus offset; mode = 2 -> Date minus offset
    private Temporal dateOffset(int mode){
        System.out.println("Date/Time with offset");
        Menu date_menu = this.view.dateFormats(1);
        String option;
        Temporal t = null;
        boolean ok=false;

        while(!ok){
            date_menu.show();
            option = Input.lerString();
            switch (option){
                case "1":
                    System.out.print("Insert date: ");
                    t = Input.lerDate();
                    ok=true;
                    break;
                case "2":
                    System.out.print("Insert date: ");
                    t = Input.lerDateTime();
                    ok=true;
                    break;
                case "3":
                    System.out.print("Insert time: ");
                    t = Input.lerTime();
                    ok=true;
                    break;
                default:
                    System.out.println("Invalid option, try again!");
                    break;
            }
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

    //List available ChronoUnits for a give Temporal object
    private List<ChronoUnit> available_chronounits(Temporal temp){
        List<ChronoUnit> units = new ArrayList<>();
        List<String> units_s = new ArrayList<>();
        String header = "The following units can be used to measure a time interval.\n" +
                "Enter the desired ones separated by spaces";
        int i = 1;
        for (ChronoUnit unit : ChronoUnit.values()) {
            if (temp.isSupported(unit)){
                units.add(unit);
                units_s.add(i + "-" + unit.toString());
                i ++;
            }
        }

        this.view.printColletion(header, units_s);

        return units;
    }

    static String timeToString(Temporal t){
        if (t instanceof LocalDateTime) return ((LocalDateTime) t).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        else if (t instanceof LocalDate) return ((LocalDate) t).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        else return ((LocalTime) t).format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

}
