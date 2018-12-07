package UTDC.Views;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class TimeZoneView implements  ViewInterface{

    private Menus menusUTDC;

    public TimeZoneView() {
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

    public Menus initView() {
        Menus menusUTDC = new Menus();

        Opcao op1, op2, op3, op4, op5;
        op1 = new Opcao("Show Time Zones .....................................", "S");
        op2 = new Opcao("Find Time Zones .....................................", "F");
        op3 = new Opcao("Calculate new Time Zone .............................", "C");
        op4 = new Opcao("Time-Date at specific Time Zone .....................", "T");
        op5 = new Opcao("Main menu ...........................................", "M");
        List<Opcao> linhas = Arrays.asList(op1, op2, op3, op4, op5);
        Menu timeZoneMenu = new Menu(linhas, "Timezone Calculator Mode");
        menusUTDC.addMenu(1, timeZoneMenu);


        Opcao op11, op12, op13;
        op11 = new Opcao("Your time zone    -->  Custom time zone .............", "1");
        op12 = new Opcao("Custom time zone  -->  Your time zone ...............", "2");
        op13 = new Opcao("Custom time zone  -->  Custom time zone .............", "3");
        List<Opcao> linhas1 = Arrays.asList(op11, op12, op13);
        Menu timeZoneConvMenu = new Menu(linhas1, "What type of conversion do you want to perform?");
        menusUTDC.addMenu(2, timeZoneConvMenu);

        Opcao op21, op22, op23;
        op21 = new Opcao("dd-MM-yyyy ...........................................", "1");
        op22 = new Opcao("dd-MM-yyyy HH:mm:ss ..................................", "2");
        op23 = new Opcao("HH:mm:ss .............................................", "3");
        List<Opcao> linhas2;
        linhas2 = Arrays.asList(op21, op22, op23);
        Menu dt_menu = new Menu(linhas2, "Available date/time formats");
        menusUTDC.addMenu(3, dt_menu);

        //return menu
        return menusUTDC;
    }
    public void timeZoneTimePrompt(){
        System.out.println("Which TimeZone would you like to check the time of?");
        System.out.println("(Format hint = Continent/City)");
    }

    public void printColletion(String header, Collection<String> values){
        System.out.println(header);
        for(String val: values){
            System.out.println(val);
        }
    }
}
