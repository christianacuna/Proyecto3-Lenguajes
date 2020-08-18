package Clientes.GUI;
import java.awt.FlowLayout;
import javax.swing.*;

public class msjBox extends JFrame {

    private static final long serialVersionUID = 1L;

    public msjBox() {

        // set flow layout for the frame
        this.getContentPane().setLayout(new FlowLayout());

        JTextField campoTexto = new JTextField(17);
        JButton boton1 = new JButton("Conectar");
        add(campoTexto);
        add(boton1);

    }

    private static void inicializar() {

        //Create and set up the window.

        JFrame frame = new msjBox();

        //Display the window.
        frame.setTitle("Servidor de Juegos");
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {

        //Schedule a job for the event-dispatching thread:

        //creating and showing this application's GUI.

        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            public void run() {

                inicializar();

            }

        });
    }

}