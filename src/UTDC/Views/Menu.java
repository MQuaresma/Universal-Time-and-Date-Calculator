package UTDC.Views;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class Menu {

    private List<Opcao> linhas;
    private String nome;

    public Menu(List<Opcao> linhas, String nome) {
        this.linhas = linhas;
        this.nome = nome;
    }

    public Menu() {
    }

    public Menu(Menu m){
        this.linhas = m.getLinhas();
        this.nome = m.getNome();
    }

    public void show(){
        System.out.println("\n\n-----------------------------------------------------");
        System.out.println("Hora atual: " + java.time.LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        System.out.println("-----------------------------------------------------");
        System.out.println("                 " + this.nome);
        System.out.println("-----------------------------------------------------\n");
        for (Opcao o : this.linhas)
            o.print();
        System.out.print("                   Insert your option:");
    }

    public List<Opcao> getLinhas() {
        return linhas;
    }

    public void setLinhas(List<Opcao> linhas) {
        this.linhas = linhas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}