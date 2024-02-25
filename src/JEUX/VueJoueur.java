package JEUX;

import java.awt.*;
import javax.swing.*;
public class VueJoueur extends JComponent implements Observer {
    protected Joueur[] player;
    private Fenetre1 fenetre;
    private Color[] couleur = {Color.BLACK,Color.RED,Color.GREEN,Color.BLUE,Color.WHITE};
    public VueJoueur(Joueur[] player, Fenetre1 fenet){
        this.fenetre = fenet;
        this.player = player;
        for(Joueur p : player){
            p.addObserver(this);
        }
    }
    public void update(){
        this.repaint();
    }

    public void paintComponent(Graphics a) {
        Color c = a.getColor();
        Graphics2D g2 = (Graphics2D) a;

        for (Joueur p : player) {
            g2.setColor(couleur[p.getNum_joueur()]);
            g2.fillRect((260*fenetre.scale + p.getPos()[0] * 100*fenetre.scale + fenetre.scale* 15 * p.getNum_joueur())/1000 , (320*fenetre.scale + p.getPos()[1] * 100 * fenetre.scale) /1000, 10 , 10 );
            g2.setColor(c);
        }
    }
}
