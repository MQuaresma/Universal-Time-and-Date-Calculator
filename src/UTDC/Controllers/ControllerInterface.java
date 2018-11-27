package UTDC.Controllers;

import UTDC.Models.UTDCModel;
import UTDC.Views.Menu;
import UTDC.Views.UTDCView;

public interface ControllerInterface {
    void setView(UTDCView v);
    void setModel(UTDCModel m);
    void startFlow(Menu menu);
}
