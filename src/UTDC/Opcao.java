package UTDC;

public class Opcao {

    private String linha;
    private String letra;

    public Opcao(String linha, String letra) {
        this.linha = linha;
        this.letra = letra;
    }

    public void print(){
        System.out.println(this.linha + "  " + this.letra);
    }

    public String getLinha() {
        return linha;
    }

    public void setLinha(String linha) {
        this.linha = linha;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

}