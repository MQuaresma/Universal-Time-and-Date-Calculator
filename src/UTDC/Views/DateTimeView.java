package UTDC.Views;

import UTDC.Models.Opcao;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class DateTimeView implements ViewInterface{

    private Menus menusUTDC;

    public DateTimeView() {
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

        Opcao op11, op12, op13, op14, op15, op16;
        op11 = new Opcao("Difference between dates/times ......................", "D");
        op12 = new Opcao("Difference between dates/times in specific unities ..", "U");
        op13 = new Opcao("Add amount to date/time .............................", "A");
        op14 = new Opcao("Subtract amount from date/time ......................", "S");
        op15 = new Opcao("Lifetime analytics ..................................", "L");
        op16 = new Opcao("Main menu ...........................................", "M");
        List<Opcao> linhas = Arrays.asList(op11, op12, op13, op14, op15, op16);
        Menu dateTimeMenu = new Menu(linhas, "Date/Time Calculator Mode");
        menusUTDC.addMenu(1, dateTimeMenu);

        Opcao op1, op2, op3, op4, op5;
        op1 = new Opcao("Age at given date ...................................", "A");
        op2 = new Opcao("Day of week of day of next birthday .................", "W");
        op3 = new Opcao("Birthday-Weekday percentage distribuition ...........", "D");
        op4 = new Opcao("Amount of leap years in lifetime ....................", "L");
        op5 = new Opcao("Date/Time Menu ......................................", "M");
        List<Opcao> ops = Arrays.asList(op1, op2, op3, op4, op5);
        Menu lifestat = new Menu(ops, "Available Statistics");
        menusUTDC.addMenu(2, lifestat);

        //return menu
        return menusUTDC;
    }

    //TODO: fix
    public Menu dateFormats(int full){
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

    public void printColletion(String header, Collection<String> values){
        System.out.println(header);
        for(String val: values){
            System.out.println(val);
        }
    }
}

