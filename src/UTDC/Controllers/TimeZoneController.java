package UTDC.Controllers;

import UTDC.Input;
import UTDC.Views.Menu;
import UTDC.Views.UTDCView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

public class TimeZoneController implements ControllerInterface {

    public void startFlow(Menu menu){
        String opcao;
        do {
            menu.show();
            opcao = Input.lerString().toUpperCase();
            switch (opcao) {
                case "S":
                    UTDCView.printTimeZones();
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

    private void calculateTimezones() {
        Set<String> zones ;
        zones= ZoneId.getAvailableZoneIds();
        LocalDateTime date;
        String zone;
        System.out.print("Initial date: <Year>-<Month>-<Day>T<Hours>:<Minutes>:<Seconds>.Nanoseconds ");
        date = Input.lerDateTime();

        System.out.print("Final Time Zone: ");
        do {
            zone = Input.lerString();
            if (zones.contains(zone)) break;
            else System.out.print("TimeZone inesxistente!\nFinal Time Zone: ");
        }while (true);
        ZonedDateTime zdtZone2 = ZonedDateTime.of(date, ZoneId.of(zone));
        System.out.println("New Date: "+zdtZone2);
    }

}
