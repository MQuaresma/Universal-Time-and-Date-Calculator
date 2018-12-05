package UTDC.Views;

import java.util.Collection;

public interface ViewInterface {
    public Menus getMenusUTDC();

    public void setMenusUTDC(Menus m);

    public Menu getMenu(int indice);

    public Menus initView();

    public void printColletion(String header, Collection<String> values);
}
