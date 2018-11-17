package UTDC;

import UTDC.Controllers.UTDCController;
import UTDC.Models.EventModel;
import UTDC.Models.SlotModel;
import UTDC.Models.UTDCModel;
import UTDC.Views.UTDCView;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class UTDCApp {

    private static UTDCModel createData(){
        List<SlotModel> slots = new ArrayList<SlotModel>();

        SlotModel sm1 = new SlotModel(LocalDateTime.of(2018,12,20,13,15), Duration.ofDays(2));
        EventModel em1 = new EventModel(LocalDateTime.of(2018,12,20,20,0), Arrays.asList("Carlos","Daniela"),"Jantar de natal","Restaurante da Rotunda");
        EventModel em2 = new EventModel(LocalDateTime.of(2018,12,20,23,30), Arrays.asList("Colegas universitários"), "Jogo de cartas","Complexo de jogos");
        EventModel em3 = new EventModel(LocalDateTime.of(2018,12,21,14,45), Arrays.asList("António, Miguel, Fátima"), "Trabalho prático","Universidade do Minho");
        sm1.addEvent(em1);
        sm1.addEvent(em2);
        sm1.addEvent(em3);

        SlotModel sm2 = new SlotModel(LocalDateTime.of(2018,12,31,15,0), Duration.ofDays(1));
        EventModel em4 = new EventModel(LocalDateTime.of(2018,12,31,20,0), Arrays.asList("Silvia","Carlos","Daniela","Antonio"),"Último jantar de 2018","Taberna");
        EventModel em5 = new EventModel(LocalDateTime.of(2018,12,31,23,30), Arrays.asList("Amigos"),"Festa em casa do Manuel","Casa do Manuel");
        sm2.addEvent(em4);
        sm2.addEvent(em5);

        slots.add(sm1);
        slots.add(sm2);

        return new UTDCModel(new ArrayList<>(slots));
    }

    public static void main(String[] args) {
        UTDCModel model = createData();

        UTDCView view = new UTDCView();

        UTDCController control = new UTDCController();
        control.setModel(model);
        control.setView(view);

        control.startFlow();

        System.out.println("End of session >> " + java.time.LocalDateTime.now());

        System.exit(0);
    }
}
