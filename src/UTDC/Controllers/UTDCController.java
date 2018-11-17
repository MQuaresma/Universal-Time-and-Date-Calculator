package UTDC.Controllers;

import UTDC.Input;
import UTDC.Views.Menu;
import UTDC.Models.UTDCModel;
import UTDC.Views.UTDCView;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.*;

public class UTDCController {

    private UTDCModel model;
    private UTDCView view;
    private ControllerInterface dateTimeController;
    private ControllerInterface managementController;
    private ControllerInterface timeZoneController;


    public UTDCController(){}

    public void setView(UTDCView v){
        this.view = v;
    }

    public void setModel(UTDCModel m){
        this.model = m;
    }

    public void startFlow(){
        this.initControllers();
        Menu menu = view.getMenu(1);
        String opcao;
        do{
            menu.show();
            opcao = Input.lerString().toUpperCase();
            switch (opcao){
                case "D":
                    this.dateTimeController.startFlow(view.getMenu(2));
                    break;
                case "M":
                    this.managementController.startFlow(view.getMenu(3));
                    break;
                case "T":
                    this.timeZoneController.startFlow(view.getMenu(4));
                    break;
                case "E":
                    break;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        } while (!opcao.equals("E"));
    }

    private void initControllers(){
        this.dateTimeController = new DateTimeModeController();
        this.managementController = new ManagementController();
        this.timeZoneController = new TimeZoneController();
    }

}
