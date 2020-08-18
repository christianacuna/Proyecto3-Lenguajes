package Clientes.GUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MsjBox extends JFrame {
    private String texto;
    private static final long serialVersionUID = 1L;

    public MsjBox() {
        texto = "-1";
        // set flow layout for the frame
        this.getContentPane().setLayout(new FlowLayout());
        JLabel label = new JLabel();
        label.setText("IP :");
        JLabel label2 = new JLabel();
        label2.setText("PUERTO :");
        JTextField campoIP = new JTextField(12);
        JTextField campoPuerto = new JTextField(4);
        JButton boton1 = new JButton("Conectar");
        add(label);
        add(campoIP);
        add(label2);
        add(campoPuerto);
        add(boton1);
        campoIP.getText();
        boton1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                setTexto(campoIP.getText()+":"+campoPuerto.getText());
                setVisible(false);
            }

        });
    }
    public void setTexto(String texto){
        this.texto = texto;
    }
    public Boolean isReady(){
        return !(this.texto.contains("-1"));
    }
    public void waitUntilready(){
        while(!isReady()){
        }
    }
    public String getCampotexto() {
        return this.texto;
    }

    public void display() {

        //Create and set up the window.

        JFrame frame = new MsjBox();

        //Display the window.
        frame.setTitle("Servidor de Juegosinicializar");
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        waitUntilready();
        System.out.println(this.texto);
    }

}