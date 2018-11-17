package UTDC.Views;

import java.util.Map;
import java.util.HashMap;

public class Menus {

    private Map<Integer,Menu> menus;

    public Menus(Map<Integer, Menu> menus) {
        this.menus = new HashMap<>(menus);
    }

    public Menus() {
        this.menus = new HashMap<>();
    }

    public Map<Integer, Menu> getMenus() {
        return this.menus;
    }

    public void setMenus(Map<Integer, Menu> menus) {
        this.menus = menus;
    }

    public void addMenu(int i, Menu m){
        this.menus.put(i,m);
    }

    public Menu get(int indice){
        return this.menus.get(indice);
    }
}