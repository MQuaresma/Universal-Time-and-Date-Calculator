package UTDC.Controllers;

import UTDC.Input;
import UTDC.Views.Menu;
import UTDC.Views.UTDCView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TimeZoneController implements ControllerInterface {

    public void startFlow(Menu menu){
        String opcao;
        do {
            menu.show();
            opcao = Input.lerString().toUpperCase();
            switch (opcao) {
                case "S":
                    Set<String> timezones = ZoneId.getAvailableZoneIds();
                    UTDCView.printColletion("All available Time Zones:", timezones);
                    break;
                case "C":
                    calculateTimezones();
                    break;
                case "M":
                    break;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        } while (!opcao.equals("M"));
    }

    private void calculateTimezones(){
        UTDCView.printColletion("All available Time Zones", ZoneId.getAvailableZoneIds());
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
        System.out.println("Date format: <Year>-<Month>-<Day>T<Hours>:<Minutes>:<Seconds>.Nanoseconds");
        System.out.print("Insert date in initial zone: ");
        LocalDateTime ldt = Input.lerDateTime();
        ZonedDateTime zdt = ZonedDateTime.of(ldt,zone1).withZoneSameInstant(zone2);
        System.out.println(ldt + " in " + zone1.toString() + " = " + zdt.toLocalDateTime() + " in " + zone2.toString());
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
}
