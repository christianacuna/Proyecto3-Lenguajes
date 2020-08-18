public class Carta implements Comparable<Carta>{
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

    public boolean esVisible() {
        return visible;
    }

    @Override
    public int compareTo(Carta c) {
        return this.getNumero() - c.getNumero();
    }
}
