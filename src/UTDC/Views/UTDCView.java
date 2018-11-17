package UTDC.Views;

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
        op11 = new Opcao("Date/Time calculator mode .......","D");
        op12 = new Opcao("Slot Management mode ............","M");
        op13 = new Opcao("Timezone calculator mode ........","T");
        op14 = new Opcao("End application session >>>>>>>>>","E");
        List<Opcao> linhas1 = Arrays.asList(op11, op12, op13, op14);
        Menu menuPrincipal = new Menu(linhas1,"Initial Menu");
        menusUTDC.addMenu(1,menuPrincipal);

        Opcao op21, op22, op23, op24;
        op21 = new Opcao("Duration between dates ..........", "D");
        op22 = new Opcao("Add amount to date ..............", "A");
        op23 = new Opcao("Subtract amount from date .......", "S");
        op24 = new Opcao("Main menu .......................", "M");
        List<Opcao> linhas2 = Arrays.asList(op21, op22, op23, op24);
        Menu dateTimeMenu = new Menu(linhas2, "Date/Time Calculator Mode");
        menusUTDC.addMenu(2, dateTimeMenu);

        //return menu
        return menusUTDC;
    }

}
