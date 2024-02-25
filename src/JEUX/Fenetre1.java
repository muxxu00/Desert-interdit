package JEUX;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class Fenetre1 extends JFrame implements ComponentListener {
    protected JFrame frame;
    private VueDesert Plateau;
    private VueJoueur Player;
    protected int scale;
    public Fenetre1(Game jeu) {
        this.frame = new JFrame();
        frame.addComponentListener(this);
        frame.setSize(1500, 1500);
        frame.setResizable(true);
        frame.setTitle("Desert Interdit");
        Player = new VueJoueur(jeu.getJoueurs(),this);
        Player.setBounds(0,0, frame.getWidth(),frame.getHeight());
        frame.add(Player);
        Plateau = new VueDesert(jeu,this);
        Plateau.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        frame.add(Plateau);
        this.scale = (frame.getHeight()+frame.getWidth())/2;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public void paintComponent(Graphics g){
        Plateau.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        Player.setBounds(0,0,frame.getWidth(),frame.getHeight());
        if (Plateau.jeu.gagner()){
            Player.setVisible(false);
        }
        Plateau.jeu.notifyObservers();
        for (Joueur p : Player.player){
            p.notifyObservers();
        }

    }
    @Override
    public void componentResized(ComponentEvent e) {
        this.scale = (frame.getHeight()+frame.getWidth())/2;
        this.repaint();
    }

    @Override
    public void componentMoved(ComponentEvent e) {}

    @Override
    public void componentShown(ComponentEvent e) {}

    @Override
    public void componentHidden(ComponentEvent e) {}


}