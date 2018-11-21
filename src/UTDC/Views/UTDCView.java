package UTDC.Views;

import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.HashSet;
import java.util.List;
import java.util.Arrays;

import java.time.ZoneId;

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
        op11 = new Opcao("Date/Time calculator mode .............","D");
        op12 = new Opcao("Slot Management mode ..................","M");
        op13 = new Opcao("Timezone calculator mode ..............","T");
        op14 = new Opcao("End application session >>>>>>>>>>>>>>>","E");
        List<Opcao> linhas1 = Arrays.asList(op11, op12, op13, op14);
        Menu menuPrincipal = new Menu(linhas1,"Initial Menu");
        menusUTDC.addMenu(1,menuPrincipal);

        Opcao op21, op22, op23, op24;
        op21 = new Opcao("Difference between dates/times ........", "D");
        op22 = new Opcao("Add amount to date/time ...............", "A");
        op23 = new Opcao("Subtract amount from date/time ........", "S");
        op24 = new Opcao("Main menu .............................", "M");
        List<Opcao> linhas2 = Arrays.asList(op21, op22, op23, op24);
        Menu dateTimeMenu = new Menu(linhas2, "Date/Time Calculator Mode");
        menusUTDC.addMenu(2, dateTimeMenu);

        Opcao op31, op32, op33;
        op31 = new Opcao("Show Time Zones .......................", "S");
        op32 = new Opcao("Calculate new Time Zone................", "C");
        op33 = new Opcao("Main menu .............................", "M");
        List<Opcao> linhas3 = Arrays.asList(op31, op32, op33);
        Menu timeZoneMenu = new Menu(linhas3, "Timezone Calculator Mode");
        menusUTDC.addMenu(4, timeZoneMenu);

        //return menu
        return menusUTDC;
    }

    public static void imprimeUnidades(List<ChronoUnit> unities){
        int i = 1;
        for (ChronoUnit unit : unities){
            System.out.println(i + " - " + unit.toString());
            i++;
        }
        System.out.print("Please insert separated by spaces the unities you want to calculate: ");
    }

    public static void optionsDates(){
        System.out.println("Do you want to work with:");
        System.out.println("1 - <Year>-<Month>-<Day>");
        System.out.println("2 - <Year>-<Month>-<Day>T<Hours>:<Minutes>:<Seconds>.<Nanoseconds>");
        System.out.println("3 - <Hours>:<Minutes>:<Seconds>.<Nanoseconds>");
        System.out.print("Option: ");
    }

    public static void printTimeZones() {
        System.out.println("All Time Zones");
        for (String z : ZoneId.getAvailableZoneIds()){
                System.out.println(z);
        }
    }

    /*
    //option = 1 -> between two dates; option = 2 -> add offset; option = 3 -> subtract offset
    public static void unitiesOptionsLocalDate(int option){
        if (option == 1) System.out.println("The following unities are available to measure the time between two dates:");
        else if (option == 2) System.out.println("The following unities are available to add offset to a date:");
        else if (option == 3) System.out.println("The following unities are available to subtract offset to a date:");
        System.out.println("1 - Years");
        System.out.println("2 - Months");
        System.out.println("3 - Days");
        System.out.println("4 - Weeks");
        System.out.print("Please insert separated by spaces the unities you want to calculate: ");
    }

    //option = 1 -> between two dates; option = 2 -> add offset; option = 3 -> subtract offset
    public static void unitiesOptionsLocalDateTime(int option){
        if (option == 1) System.out.println("The following unities are available to measure the time between two dates:");
        else if (option == 2) System.out.println("The following unities are available to add offset to a date:");
        else if (option == 3) System.out.println("The following unities are available to subtract offset to a date:");
        System.out.println("1 - Years");
        System.out.println("2 - Months");
        System.out.println("3 - Days");
        System.out.println("4 - Weeks");
        System.out.println("5 - Hours");
        System.out.println("6 - Minutes");
        System.out.println("7 - Seconds");
        System.out.println("8 - Nanoseconds");
        System.out.print("Please insert separated by spaces the unities you want to calculate: ");
    }
    */
}
