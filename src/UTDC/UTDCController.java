package UTDC;

public class UTDCController {

    private UTDCModel model;
    private UTDCView view;

    public UTDCController(){}

    public void setView(UTDCView v){
        this.view = v;
    }

    public void setModel(UTDCModel m){
        this.model = m;
    }

    public void startFlow(){
        Menu menu = view.getMenu(1);
        String opcao;
        do{
            menu.show();
            opcao = Input.lerString().toUpperCase();
            switch (opcao){
                case "D":
                    flowDateTime();
                    break;
                case "M":
                    flowManagement();
                    break;
                case "T":
                    flowTimezone();
                    break;
                case "E":
                    break;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        } while (!opcao.equals("E"));
    }

    //------------Date/Time calculator mode------------

    private void flowDateTime(){
        System.out.println("TO IMPLEMENT!");
    }

    //------------Slot management mode------------

    private void flowManagement(){
        System.out.println("TO IMPLEMENT!");
    }

    //------------Timezone calculator mode------------

    private void flowTimezone(){
        System.out.println("TO IMPLEMENT!");
    }

}
