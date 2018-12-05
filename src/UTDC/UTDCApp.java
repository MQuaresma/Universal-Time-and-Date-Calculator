package UTDC;

import UTDC.Controllers.DateTimeModeController;
import UTDC.Controllers.ManagementController;
import UTDC.Controllers.TimeZoneController;
import UTDC.Controllers.UTDCController;
import UTDC.Models.EventModel;
import UTDC.Models.UTDCModel;
import UTDC.Views.DateTimeView;
import UTDC.Views.ManagementView;
import UTDC.Views.TimeZoneView;
import UTDC.Views.UTDCView;

import java.awt.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
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
        UTDCModel model = loadModel();

        UTDCView view = new UTDCView();
        DateTimeView dt_view = new DateTimeView();
        TimeZoneView tz_view = new TimeZoneView();
        ManagementView sm_view = new ManagementView();

        UTDCController control = new UTDCController();
        DateTimeModeController dt_controller = new DateTimeModeController();
        dt_controller.setView(dt_view);
        TimeZoneController tz_controller = new TimeZoneController();
        tz_controller.setView(tz_view);
        ManagementController m_controller = new ManagementController();
        m_controller.setView(sm_view);
        m_controller.setModel(model);


        control.setModel(model);
        control.setView(view);
        control.setDateTimeController(dt_controller);
        control.setTimeZoneController(tz_controller);
        control.setManagementController(m_controller);
        control.startFlow();

        System.out.println("End of session >> " + java.time.LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));

        storeModel(model);

        System.exit(0);
    }

    public static UTDCModel loadModel(){
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

        return model;
    }

    public static void storeModel(UTDCModel model){
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
    }

}
