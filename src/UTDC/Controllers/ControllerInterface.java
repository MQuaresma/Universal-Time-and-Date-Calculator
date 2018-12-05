package UTDC.Controllers;

import UTDC.Models.UTDCModel;
import UTDC.Views.ViewInterface;

public interface ControllerInterface {
    void setView(ViewInterface v);
    void setModel(UTDCModel m);
    void startFlow();
}
