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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class UTDCApp {

    public static void main(String[] args) {
        // UTDCModel model = createData();
        UTDCModel model;
        try{
            FileInputStream fi = new FileInputStream("utdc.config");
            ObjectInputStream oi = new ObjectInputStream(fi);
            model = (UTDCModel) oi.readObject();
            oi.close();
            fi.close();
        }catch(IOException | ClassNotFoundException e){
            System.out.println("Unable to load restored previous status, resetting.");
            model = new UTDCModel();
        }

        UTDCView view = new UTDCView();

        UTDCController control = new UTDCController();
        control.setModel(model);
        control.setView(view);

        control.startFlow();

        System.out.println("End of session >> " + java.time.LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));

        try{
            FileOutputStream fo = new FileOutputStream("utdc.config");
            ObjectOutputStream oo = new ObjectOutputStream(fo);

            oo.writeObject(model);
            oo.flush();
            oo.close();
            System.out.println("Current status saved in utdc.config.");
        }catch(IOException e){
            System.out.println("An error has occurred, couldn't save current agenda state.");
        }

        System.exit(0);
    }

}
