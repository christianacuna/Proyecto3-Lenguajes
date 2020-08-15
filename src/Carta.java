public class Carta{
    private String color;
    private String palo;
    private int numero;
    private boolean visible;

    public String getColor() {
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

    public int compareTo(Carta c) {
        return this.getNumero() - c.getNumero();
    }
}
