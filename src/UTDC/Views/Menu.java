package UTDC.Views;

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
        System.out.println("--------------------------------------------");
        System.out.println("          " + this.nome);
        System.out.println("--------------------------------------------");
        for (Opcao o : this.linhas)
            o.print();
        System.out.print("          Insert your option:");
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