package UTDC.Models;

import java.util.List;

public class UTDCModel {

    List<SlotModel> slots;

    public UTDCModel(List<SlotModel> slots){
        this.slots = slots;
    }

    public void addSlot(SlotModel sm){
        this.slots.add(sm);
    }

    public void removeSlot(SlotModel sm){
        this.slots.remove(sm);
    }

}
