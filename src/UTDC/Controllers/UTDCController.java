package UTDC.Controllers;

import UTDC.Input;
import UTDC.Views.Menu;
import UTDC.Models.UTDCModel;
import UTDC.Views.UTDCView;

public class UTDCController {

    private UTDCModel model;
    private UTDCView view;
    private ControllerInterface dateTimeController;
    private ControllerInterface managementController;
    private ControllerInterface timeZoneController;
    protected static final String local_date_format = "dd-MM-yyyy";
    protected static final String local_time_date_format = "dd-MM-yyyy HH:mm:ss";
    protected static final String local_time_format = "HH:mm:ss";

    public UTDCController(){}

    public void setView(UTDCView v){
        this.view = v;
    }

    public void setModel(UTDCModel m){
        this.model = m;
    }

    public void setDateTimeController(ControllerInterface dateTimeController) {
        this.dateTimeController = dateTimeController;
    }

    public void setManagementController(ControllerInterface managementController) {
        this.managementController = managementController;
    }

    public void setTimeZoneController(ControllerInterface timeZoneController) {
        this.timeZoneController = timeZoneController;
    }

    public void startFlow(){
        Menu menu = view.getMenu(1);
        String opcao;
        do{
            menu.show();
            opcao = Input.lerString().toUpperCase();
            switch (opcao){
                case "D":
                    this.dateTimeController.startFlow();
                    break;
                case "M":
                    this.managementController.startFlow();
                    break;
                case "T":
                    this.timeZoneController.startFlow();
                    break;
                case "E":
                    break;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        } while (!opcao.equals("E"));
    }
}
