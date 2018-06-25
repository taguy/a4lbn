package view;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import controller.*;
/**
* Classe DebutAnimation
* Animation d'images
*/
public class DebutAnimation{
    JPanel panelTotal;
    JPanel panel;
    JFrame frame;
    JLabel logo;
    JLabel vide;
    JButton quitter;
    JButton Sauvegarder;
    String cheminAnimation;
    /**
    * Constructeur de la classe
    */
    public DebutAnimation(){
        ControleurGlobal controleurGlobal = new ControleurGlobal();
        this.cheminAnimation = controleurGlobal.getCheminAnimation();
        frame = new JFrame();
        frame.setTitle("Arca D nor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,500);
        panelTotal = new JPanel();
        panelTotal.setLayout(new BorderLayout());

        ImageIcon leLogo = new ImageIcon(this.cheminAnimation+1+".png");
        logo = new JLabel(leLogo);
        panelTotal.add(logo, BorderLayout.CENTER);
        frame.add(panelTotal);
        frame.setLocationRelativeTo(null); //Centre la frame sur l'écran
        frame.setVisible(true);
        this.animation();
        this.frame.dispose();
        Menu menu = new Menu(controleurGlobal);
     }
    /**
    * Réalise le changemment d'image de l'animation
    */
    public void animation(){
        try {
            Thread.currentThread().sleep(500);
        }
        catch (InterruptedException ie) {
            System.out.println("Error sleeping");
        }
        for(int i = 1 ; i <= 15; i++){

            logo.setIcon(new ImageIcon(this.cheminAnimation+i+".png"));
        try {
            Thread.currentThread().sleep(30);
        }
        catch (InterruptedException ie) {
            System.out.println("Error sleeping");
        }
            this.frame.repaint();
            this.frame.revalidate();
        }

    }
}
