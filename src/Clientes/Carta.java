package Clientes;

public class Carta {
    private String palo;
    private int numero;
    private boolean visible;
    private String recursoImagen;

    public Carta(String palo,int numero,boolean visible,String recursoImagen){
        this.palo=palo;
        this.numero=numero;
        this.visible=visible;
        this.recursoImagen="cards_png/PNG/"+numero+palo+".png";
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getColor() {
        String color;
        switch(this.palo) {
            case "D":
                color = "red";
                break;
            case "H":
                color = "red";
                break;
            default:
                color = "black";
        }
        return color;
    }

    public String getPalo() {
        return palo;
    }

    public int getNumero() {
        return numero;
    }

    public boolean isVisible() {
        return visible;
    }

    public String getRecursoImagen() {
        String recurso;
        if (this.visible){
            recurso=this.recursoImagen;
        }else{
            recurso="cards_png/PNG/grey_back.png";
        }
        return recurso;
    }

    public int compareTo(Carta c) {
        return this.getNumero() - c.getNumero();
    }
}
