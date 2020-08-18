package Clientes;

import Clientes.GUI.MsjBox;
import Clientes.GUI.Table;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Cliente {
    public static void main(String[] args) throws Exception {
        System.out.println("Indique cual IP quiere conectarse y a cual puerto");
        Scanner scanner = new Scanner(System.in);
        String ip = scanner.nextLine();
        String[] address = ip.split(":");
        Table frame = new Table();
        MsjBox msjBox = new MsjBox();
        msjBox.display();
        msjBox.waitUntilready();
        System.out.println(msjBox.getCampotexto());
        try (Socket socket = new Socket(address[0],Integer.parseInt(address[1]))) {
            System.out.println("Enter lines of text then Ctrl+D or Ctrl+C to quit");
            var in = new Scanner(socket.getInputStream());
            var out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in2 = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );

            System.out.println(scanner.hasNextLine());
            System.out.println(socket.isConnected());
            System.out.println("--");
            String entrada = "No Connection";
            String entrada2 = entrada;

            while (scanner.hasNextLine() && socket.getInputStream().read() != -1) {
                //System.out.println(socket.isConnected());
                //System.out.println(socket.isClosed());
                //System.out.println(socket.isBound());
                //System.out.println(socket.isInputShutdown());
                //System.out.println(socket.isOutputShutdown());
                //System.out.println(in.hasNext());
                while(!in2.ready()){
                    sleep(100);
                }
                String linea = scanner.nextLine();
                entrada = in.nextLine();
                entrada2 = in2.readLine();
                System.out.println(linea);
                System.out.println(entrada);
            }
            frame.display(entrada);
            System.out.println("Conexion terminada");
        }
    }
}
