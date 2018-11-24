package UTDC.Views;

import UTDC.Models.Opcao;

import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;
import java.util.Arrays;

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

    public static Menu optionsDates(){
        Opcao op1, op2, op3;
        op1 = new Opcao("<Year>-<Month>-<Day>", "1");
        op2 = new Opcao("<Year>-<Month>-<Day>T<Hours>:<Minutes>:<Seconds>.<Nanoseconds>", "2");
        op3 = new Opcao("<Hours>:<Minutes>:<Seconds>.<Nanoseconds>", "3");
        List<Opcao> ops = Arrays.asList(op1, op2, op3);
        Menu menu = new Menu(ops, "Do you want to work with:");
        return menu;
    }

    public static Menu optionsTimeZone(){
        Opcao op1, op2, op3;
        op1 = new Opcao("Your time zone --> Custom time zone ...", "1");
        op2 = new Opcao("Custom time zone --> Your time zone ...", "2");
        op3 = new Opcao("Custom time zone --> Custom time zone .", "3");
        List<Opcao> ops = Arrays.asList(op1, op2, op3);
        Menu timeZoneConvMenu = new Menu(ops, "What type of operation you want to do");
        return timeZoneConvMenu;
    }


    public static void printColletion(String header, Collection<String> values){
        System.out.println(header);
        for(String val: values){
            System.out.println(val);
        }
    }
}
