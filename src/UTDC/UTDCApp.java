package UTDC;

import UTDC.Controllers.UTDCController;
import UTDC.Models.EventModel;
import UTDC.Models.UTDCModel;
import UTDC.Views.UTDCView;

import java.awt.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class UTDCApp {

    private static UTDCModel createData(){
        List<EventModel> events = new ArrayList<EventModel>();

        EventModel em1 = new EventModel(LocalDateTime.of(2018,12,20,20,0), Arrays.asList("Carlos","Daniela"),"Jantar de natal","Restaurante da Rotunda");
        EventModel em2 = new EventModel(LocalDateTime.of(2018,12,20,23,30), Arrays.asList("Colegas universitários"), "Jogo de cartas","Complexo de jogos");
        EventModel em3 = new EventModel(LocalDateTime.of(2018,12,21,14,45), Arrays.asList("António, Miguel, Fátima"), "Trabalho prático","Universidade do Minho");
        EventModel em4 = new EventModel(LocalDateTime.of(2018,12,31,20,0), Arrays.asList("Silvia","Carlos","Daniela","Antonio"),"Último jantar de 2018","Taberna");
        EventModel em5 = new EventModel(LocalDateTime.of(2018,12,31,23,30), Arrays.asList("Amigos"),"Festa em casa do Manuel","Casa do Manuel");
        EventModel em6 = new EventModel(LocalDateTime.of(2018,11,11,13,15), Arrays.asList("Amigos", "Familia"),"Almoço de Magusto","Parque de Campismo do Gerês");


        events.add(em1);
        events.add(em2);
        events.add(em3);
        events.add(em4);
        events.add(em5);
        events.add(em6);

        return new UTDCModel(events);
    }

    public static void main(String[] args) {
        UTDCModel model = createData();

        UTDCView view = new UTDCView();

        UTDCController control = new UTDCController();
        control.setModel(model);
        control.setView(view);

        control.startFlow();

        System.out.println("End of session >> " + java.time.LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));

        System.exit(0);
    }
}
