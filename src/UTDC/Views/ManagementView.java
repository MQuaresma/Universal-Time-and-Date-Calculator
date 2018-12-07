package UTDC.Views;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ManagementView implements ViewInterface{
    private Menus menusUTDC;

    public ManagementView() {
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

        //Menu principal
        Opcao op1, op2, op3, op4, op5, op6;
        op1 = new Opcao("List appointments ...................................", "L");
        op2 = new Opcao("Add appointment .....................................", "N");
        op3 = new Opcao("Remove appointment ..................................", "R");
        op4 = new Opcao("Change appointment ..................................", "C");
        op5 = new Opcao("Appointment details .................................", "D");
        op6 = new Opcao("Back ................................................", "B");
        List<Opcao> linhas = Arrays.asList(op1, op2, op3, op4, op5, op6);
        Menu slotMenu = new Menu(linhas, "Slot Management Mode");
        menusUTDC.addMenu(1, slotMenu);

        Opcao op11, op12, op13;
        op11 = new Opcao("Login ...............................................", "L");
        op12 = new Opcao("New user registration ...............................", "R");
        op13 = new Opcao("Main menu ...........................................", "M");
        List<Opcao> ops1 = Arrays.asList(op11, op12, op13);
        Menu management = new Menu(ops1, "Management initial menu");
        menusUTDC.addMenu(2, management);

        Opcao op21, op22, op23, op24, op25, op26;
        op21 = new Opcao("Check all events ....................................", "A");
        op22 = new Opcao("Check future events .................................", "F");
        op23 = new Opcao("Check events in a given date ........................", "D");
        op24 = new Opcao("Check events in x amount of time ....................", "U");
        op25 = new Opcao("Check past events ...................................", "P");
        op26 = new Opcao("Check all events in x weekday .......................", "W");
        List<Opcao> ops2 = Arrays.asList(op21, op22, op23, op24, op25, op26);
        Menu checkMenu = new Menu(ops2, "Which events would you like to list?");
        menusUTDC.addMenu(3, checkMenu);

        Opcao op31, op32, op33, op34;
        op31 = new Opcao("Title ...............................................", "T");
        op32 = new Opcao("Description .........................................", "D");
        op33 = new Opcao("Location ............................................", "L");
        op34 = new Opcao("Date ................................................", "W");
        List<Opcao> ops3 = Arrays.asList(op31, op32, op33, op34);
        Menu propMenu = new Menu(ops3, "Choose search parameter");
        menusUTDC.addMenu(4, propMenu);

        Opcao op41, op42, op43, op44, op45, op46;
        op41 = new Opcao("Title ...............................................", "1");
        op42 = new Opcao("Date ................................................", "2");
        op43 = new Opcao("Description .........................................", "3");
        op44 = new Opcao("Location ............................................", "4");
        op45 = new Opcao("People envolved .....................................", "5");
        op46 = new Opcao("Duration ............................................", "6");
        List<Opcao> ops4 = Arrays.asList(op41, op42, op43, op44, op45, op46);
        Menu changeMenu = new Menu(ops4, "Choose the property to change");
        menusUTDC.addMenu(5, changeMenu);

        //return menu
        return menusUTDC;
    }

    public void printColletion(String header, Collection<String> values){
        System.out.println(header);
        for(String val: values){
            System.out.println(val);
        }
    }
}

