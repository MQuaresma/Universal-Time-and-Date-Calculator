package UTDC.Controllers;

import UTDC.Input;
import UTDC.Models.UTDCModel;
import UTDC.Views.Menu;
import UTDC.Views.UTDCView;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.Set;

public class TimeZoneController implements ControllerInterface {
    public void setView(UTDCView v){
    }

    public void setModel(UTDCModel m){
    }

    public void startFlow(Menu menu){
        String opcao;
        do {
            menu.show();
            opcao = Input.lerString().toUpperCase();
            switch (opcao) {
                case "S":
                    this.showTimeZones();
                    break;
                case "C":
                    this.calculateTimezones();
                    break;
                case "T":
                    this.checkTimeZoneTime();
                    break;
                case "M":
                    break;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        } while (!opcao.equals("M"));
    }

    private void showTimeZones() {
        Set<String> timezones = ZoneId.getAvailableZoneIds();
        UTDCView.printColletion("All available Time Zones:", timezones);
        String opcao;
        System.out.println("Find TimeZone: (M -> Main Menu) ");
        opcao = Input.lerString().toUpperCase();
        while (!opcao.equals("M")) {
            UTDCView.printTimeZoneTime(opcao);
            System.out.println("Find TimeZone: (M -> Main Menu) ");
            opcao = Input.lerString().toUpperCase();
        }
    }

    private void calculateTimezones(){
        Menu tz = UTDCView.optionsTimeZone();
        tz.show();
        String option = Input.lerString().toUpperCase();
        ZoneId zone1 = null;
        ZoneId zone2 = null;
        switch (option) {
            case "1":
                zone1 = ZonedDateTime.now().getZone();
                System.out.print("Final time zone: ");
                zone2 = this.readTimezone();
                break;
            case "2":
                zone2 = ZonedDateTime.now().getZone();
                System.out.print("Initial time zone: ");
                zone1 = this.readTimezone();
                break;
            case "3":
                System.out.print("Initial time zone: ");
                zone1 = this.readTimezone();
                System.out.print("Final time zone: ");
                zone2 = this.readTimezone();
                break;
            default:
                System.out.println("Invalid option!");
                break;
        }
        System.out.print("Insert date in initial zone: ");
        LocalDateTime ldt = Input.lerDateTime();
        ZonedDateTime zdt = ZonedDateTime.of(ldt,zone1).withZoneSameInstant(zone2);

        String time1 = ldt.format(DateTimeFormatter.ofPattern(UTDCController.local_time_date_format));
        String time2 = (zdt.toLocalDateTime()).format(DateTimeFormatter.ofPattern(UTDCController.local_time_date_format));

        System.out.println(time1 + " in " + zone1.toString() + " = " + time2 + " in " + zone2.toString());
    }

    private ZoneId readTimezone(){
        String zone;
        do {
            zone = Input.lerString();
            if (ZoneId.getAvailableZoneIds().contains(zone)) break;
            else System.out.print("Invalid time zone!\nTry again: ");
        }while (true);
        return ZoneId.of(zone);
    }

    private void checkTimeZoneTime(){
        UTDCView.timeZoneTime();
        ZoneId z_id = readTimezone();
        String zone_time = LocalDateTime.now(z_id).format(DateTimeFormatter.ofPattern(UTDCController.local_time_date_format));
        System.out.println("Current date-time at " + z_id.toString() + " : " + zone_time);
    }

}
