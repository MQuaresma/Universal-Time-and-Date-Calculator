package UTDC.Views;

import UTDC.Models.Opcao;

import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class UTDCView {

    private Menus menusUTDC;

    public UTDCView() {
        menusUTDC = initView();
    }

    public Menus getMenusUTDC() {
        return menusUTDC;
    }

    public void setMenusUTDC(Menus m){
        this.menusUTDC = m;
    }

    public Menu getMenu(int indice){
        return this.menusUTDC.get(indice);
    }

    public static Menus initView() {
        Menus menusUTDC = new Menus();

        //Menu principal
        Opcao op11, op12, op13, op14;
        op11 = new Opcao("Date/Time calculator mode ...........................","D");
        op12 = new Opcao("Slot Management mode ................................","M");
        op13 = new Opcao("Timezone calculator mode ............................","T");
        op14 = new Opcao("End application session >>>>>>>>>>>>>>>>>>>>>>>>>>>>>","E");
        List<Opcao> linhas1 = Arrays.asList(op11, op12, op13, op14);
        Menu menuPrincipal = new Menu(linhas1,"Initial Menu");
        menusUTDC.addMenu(1,menuPrincipal);

        Opcao op21, op22, op23, op24, op25, op26;
        op21 = new Opcao("Difference between dates/times ......................", "D");
        op22 = new Opcao("Difference between dates/times in specific unities ..", "U");
        op23 = new Opcao("Add amount to date/time .............................", "A");
        op24 = new Opcao("Subtract amount from date/time ......................", "S");
        op25 = new Opcao("Lifetime analytics ..................................", "L");
        op26 = new Opcao("Main menu ...........................................", "M");
        List<Opcao> linhas2 = Arrays.asList(op21, op22, op23, op24, op25, op26);
        Menu dateTimeMenu = new Menu(linhas2, "Date/Time Calculator Mode");
        menusUTDC.addMenu(2, dateTimeMenu);

        Opcao op31, op32, op33, op34, op35, op36;
        op31 = new Opcao("List appointments ...................................", "L");
        op32 = new Opcao("Add appointment .....................................", "N");
        op33 = new Opcao("Remove appointment ..................................", "R");
        op34 = new Opcao("Change appointment ..................................", "C");
        op35 = new Opcao("Appointment details .................................", "D");
        op36 = new Opcao("Back ................................................", "B");
        List<Opcao> linhas3 = Arrays.asList(op31, op32, op33, op34, op35, op36);
        Menu slotMenu = new Menu(linhas3, "Slot Management Mode");
        menusUTDC.addMenu(3, slotMenu);


        Opcao op41, op42, op43, op44, op45;
        op41 = new Opcao("Show Time Zones .....................................", "S");
        op42 = new Opcao("Find Time Zones .....................................", "F");
        op43 = new Opcao("Calculate new Time Zone .............................", "C");
        op44 = new Opcao("Time-Date at specific Time Zone .....................", "T");
        op45 = new Opcao("Main menu ...........................................", "M");
        List<Opcao> linhas4 = Arrays.asList(op41, op42, op43, op44, op45);
        Menu timeZoneMenu = new Menu(linhas4, "Timezone Calculator Mode");
        menusUTDC.addMenu(4, timeZoneMenu);

        //return menu
        return menusUTDC;
    }

    public static Menu dateFormats(int full){
        Opcao op1, op2, op3;
        op1 = new Opcao("dd-MM-yyyy ...........................................", "1");
        op2 = new Opcao("dd-MM-yyyy HH:mm:ss ..................................", "2");
        List<Opcao> ops;

        if(full == 1){
            op3 = new Opcao("HH:mm:ss .............................................", "3");
            ops = Arrays.asList(op1, op2, op3);
        }
        else ops = Arrays.asList(op1, op2);

        return new Menu(ops, "Available date/time formats");
    }

    public static Menu optionsTimeZone(){
        Opcao op1, op2, op3;
        op1 = new Opcao("Your time zone    -->  Custom time zone .............", "1");
        op2 = new Opcao("Custom time zone  -->  Your time zone ...............", "2");
        op3 = new Opcao("Custom time zone  -->  Custom time zone .............", "3");
        List<Opcao> ops = Arrays.asList(op1, op2, op3);
        Menu timeZoneConvMenu = new Menu(ops, "What type of conversion do you want to perform?");
        return timeZoneConvMenu;
    }

    public static Menu managementLoginRegist(){
        Opcao op1, op2, op3;
        op1 = new Opcao("Login ...............................................", "L");
        op2 = new Opcao("New user registration ...............................", "R");
        op3 = new Opcao("Main menu ...........................................", "M");
        List<Opcao> ops = Arrays.asList(op1, op2, op3);
        Menu management = new Menu(ops, "Management initial menu");
        return management;
    }

    public static Menu checkEventsMenu(){
        Opcao op1, op2, op3, op4, op5, op6;
        op1 = new Opcao("Check all events ....................................", "A");
        op2 = new Opcao("Check future events .................................", "F");
        op3 = new Opcao("Check events in a given date ........................", "D");
        op4 = new Opcao("Check events in x amount of time ....................", "U");
        op5 = new Opcao("Check past events ...................................", "P");
        op6 = new Opcao("Check all events in x weekday .......................", "W");
        List<Opcao> ops = Arrays.asList(op1, op2, op3, op4, op5, op6);
        Menu checkMenu = new Menu(ops, "Which events would you like to list?");
        return checkMenu;
    }

    public static Menu eventPropertyMenu(){
        Opcao op1, op2, op3, op4;

        op1 = new Opcao("Title ...............................................", "T");
        op2 = new Opcao("Description .........................................", "D");
        op3 = new Opcao("Location ............................................", "L");
        op4 = new Opcao("Date ................................................", "W");
        List<Opcao> ops = Arrays.asList(op1, op2, op3, op4);
        Menu checkMenu = new Menu(ops, "Choose search parameter");
        return checkMenu;
    }

    public static Menu changeEventPropertyMenu(){
        Opcao op1, op2, op3, op4, op5, op6;

        op1 = new Opcao("Title ...............................................", "1");
        op2 = new Opcao("Date ................................................", "2");
        op3 = new Opcao("Description .........................................", "3");
        op4 = new Opcao("Location ............................................", "4");
        op5 = new Opcao("People envolved .....................................", "5");
        op6 = new Opcao("Duration ............................................", "6");
        List<Opcao> ops = Arrays.asList(op1, op2, op3, op4, op5, op6);

        return new Menu(ops, "Choose the property to change");
    }

    public static Menu lifeStatisticsMenu(){
        Opcao op1, op2, op3, op4, op5;
        op1 = new Opcao("Age at given date ...................................", "A");
        op2 = new Opcao("Day of week of day of next birthday .................", "W");
        op3 = new Opcao("Birthday-Weekday percentage distribuition ...........", "D");
        op4 = new Opcao("Amount of leap years in lifetime ....................", "L");
        op5 = new Opcao("Date/Time Menu ......................................", "M");

        List<Opcao> ops = Arrays.asList(op1, op2, op3, op4, op5);
        return new Menu(ops, "Available Statistics");
    }

    public static void timeZoneTime(){
        System.out.println("Which TimeZone would you like to check the time of?");
        System.out.println("(Format hint = Continent/City)");
    }

    public static void printColletion(String header, Collection<String> values){
        System.out.println(header);
        for(String val: values){
            System.out.println(val);
        }
    }
}
