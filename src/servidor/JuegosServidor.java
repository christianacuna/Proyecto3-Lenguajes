package servidor;
import servidor.juegosCartas.JuegoCartas;
import servidor.jugadores.Jugador;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

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
    private List<Jugador> listaJugadores;
    private static Map<Integer,String> listajuegos;
    static {
        listajuegos = new HashMap<>();
        listajuegos.put(1, "Hold'em");
        listajuegos.put(2, "FiveCards");
        listajuegos.put(3, "Omaha");
        listajuegos.put(4, "SevenCards");
    }

    public static void main(String[] args) {

        int port = 8080;
        System.out.println("Iniciando servidor en puerto: "+port);
        JuegosServidor server = new JuegosServidor();
        int numeroJuego = menu1();
        int cantJugadores = menu2();
        server.startServer(port,numeroJuego,cantJugadores);
    }
    static private int menu1(){
        int cantJuegos = listajuegos.size();
        int acum = 1;
        System.out.println(ANSI_CYAN+"Seleccione el juego de cartas [1-"+cantJuegos+"]"+ANSI_RESET);
        while(acum <= cantJuegos){
            System.out.println(acum+" - "+listajuegos.get(acum));
            acum++;
        }
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
        System.out.println("Deteniendo..");
        this.running = false;
        flushClients();
        this.interrupt();
    }
    public void flushClients(){
        int largo = this.listaJugadores.size()-1;
        System.out.println("Cerrando Conexion con clientes.");
        try{
            while(largo >= 0){
                this.listaJugadores.get(largo).getConeccion().getSocket().close();
                largo--;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void enviarMensajeClientes(String linea){
        linea = " "+linea;
        int acum = 0;
        try{
            while(acum < cantJugadores){
                PrintWriter out = new PrintWriter(this.listaJugadores.get(acum).getConeccion().getSocket().getOutputStream(), true);
                out.println(linea);
                acum++;
            }
        }catch (IOException e){
            e.printStackTrace();
            stopServer();
        }
    }
    public void startServer(int port,int juegoId, int cantJugadores)
    {
        try{
            this.socket = new ServerSocket(port);
            this.start();
            this.cantJugadores = cantJugadores;
            this.numJuego = juegoId;
            this.listaJugadores = new ArrayList<>();
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
                this.listaJugadores.add(jugador);
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
        System.out.println(listajuegos.get(this.numJuego));
        enviarMensajeClientes(listajuegos.get(this.numJuego));
        JuegoCartas juegoCartas = new JuegoCartas();
        this.running = false;
        juegoCartas.iniciarJuego();
        stopServer();

    }
}