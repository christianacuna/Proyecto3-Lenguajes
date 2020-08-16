import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class JuegosServidor extends Thread
{
    private ServerSocket socket;
    private boolean running = false;
    private int acumulador;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    private int cantJugadores;
    private int numJuego;
    private List<Jugador> litaJugadores;

    public static void main(String[] args) {

        int port = 8080;
        System.out.println("Iniciando servidor en puerto: "+port);
        JuegosServidor server = new JuegosServidor();
        int numeroJuego = menu1();
        int cantJugadores = menu2();
        server.startServer(port,numeroJuego,cantJugadores);
    }
    static private int menu1(){
        System.out.println(ANSI_CYAN+"Seleccione el juego de cartas [1-4]"+ANSI_RESET);
        System.out.println("1 - Hold'em");
        System.out.println("2 - FiveCards");
        System.out.println("3 - Omaha");
        System.out.println("4 - SevenCards");
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        if (n > 0 && n < 5){
            System.out.println(ANSI_GREEN+"Juego reconosido"+ANSI_RESET);
            return n;
        }else{
            System.out.println(ANSI_RED+"Juego no reconsido" +ANSI_RESET);
            return menu1();
        }
    }
    static private int menu2(){
        System.out.println(ANSI_CYAN+"Seleccione la cantidad de jugadores [1-4]"+ANSI_RESET);
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        if (n > 0 && n < 5){
            System.out.println(ANSI_GREEN+"Buscando "+ n +" jugadores"+ANSI_RESET);
            return n;
        }else{
            System.out.println(ANSI_RED+"Cantidad de jugadores no soportado" +ANSI_RESET);
            return menu2();
        }
    }
    public void stopServer()
    {
        System.out.println("Deteniendo");
        this.running = false;
        this.interrupt();
    }
    public void startServer(int port,int juegoId, int cantJugadores)
    {
        try{

            this.socket = new ServerSocket(port);
            this.start();
            this.cantJugadores = cantJugadores;
            this.numJuego = juegoId;
            this.litaJugadores = new ArrayList<>();
            this.running = true;
            this.acumulador = 1;

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void run()
    {
        while(this.cantJugadores >= this.acumulador )
        {
            try
            {
                System.out.println("Esperando Jugadores...");
                Socket NewSocket = this.socket.accept();
                System.out.println("Jugador "+ANSI_BLUE + this.acumulador+ ANSI_RESET+" encontrado");
                Jugador jugador = new Jugador(NewSocket,this.acumulador);
                Thread t = new Thread(jugador);
                t.start();
                this.litaJugadores.add(jugador);
                this.acumulador += 1;
                System.out.println(jugador.toString());
                //System.out.println(jugador.getConeccion().getSocket().getRemoteSocketAddress().toString());
            }
            catch (IOException e)
            {
                e.printStackTrace();
                stopServer();
            }
        }
        System.out.println("Jugadores Encontrados...");
        JuegoCartas juegoCartas = new JuegoCartas();
        this.running = false;
        juegoCartas.iniciarJuego();
        try{
            String line = "Testeo Completo pepe";
            PrintWriter out = new PrintWriter(this.litaJugadores.get(0).getConeccion().getSocket().getOutputStream(), true);
            out.println(line);
        }catch (IOException e){
            e.printStackTrace();
            stopServer();
        }

    }
}