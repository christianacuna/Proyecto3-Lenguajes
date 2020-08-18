public class Carta implements Comparable<Carta>{
    private String palo;
    private int numero;
    private boolean visible;

    public Carta(String palo,int numero,boolean visible){
        this.palo=palo;
        this.numero=numero;
        this.visible=visible;
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

    public boolean esVisible() {
        return visible;
    }

    @Override
    public int compareTo(Carta c) {
        return this.getNumero() - c.getNumero();
    }
}
