package Clientes;

import Clientes.GUI.Table;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Cliente extends JFrame{
    private String texto;
    public static void main(String[] args) throws Exception {
        String direccion;
        direccion = JOptionPane.showInputDialog("Direccion server IP:PUERTO");
        //Scanner scanner = new Scanner(System.in);
        //String ip = scanner.nextLine();
        String[] address = direccion.split(":");
        Table frame = new Table();
        try (Socket socket = new Socket(address[0],Integer.parseInt(address[1]))) {
            System.out.println("Enter lines of text then Ctrl+D or Ctrl+C to quit");
            var in = new Scanner(socket.getInputStream());
            var out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in2 = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );

            //System.out.println(scanner.hasNextLine());
            System.out.println(socket.isConnected());
            System.out.println("--");
            String entrada = "No Connection";
            String entrada2 = entrada;
            if(socket.getInputStream().read() != -1){
                frame.display(entrada);
            }
            /*
            while (socket.getInputStream().read() != -1) {
                //System.out.println(socket.isConnected());
                //System.out.println(socket.isClosed());
                //System.out.println(socket.isBound());
                //System.out.println(socket.isInputShutdown());
                //System.out.println(socket.isOutputShutdown());
                //System.out.println(in.hasNext());
                while(!in2.ready()){
                    sleep(100);
                }
                //String linea = scanner.nextLine();
                entrada = in.nextLine();
                entrada2 = in2.readLine();
                //System.out.println(linea);
                System.out.println(entrada);
            }*/
            System.out.println("Conexion terminada");
        }catch (ConnectException e){
            System.out.println("Servidor "+direccion+" No encontrado");
        }
    }
}
