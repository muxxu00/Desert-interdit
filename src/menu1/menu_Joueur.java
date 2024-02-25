package menu1 ;


import JEUX.Fenetre1;
import JEUX.Game;

import javax.swing.*;
import java.awt.*;

public class menu_Joueur extends JPanel{
    private JSlider nbJIG;
    private boolean t;
    private int currentJ;

    private JPanel selecNbJoueur(JPanel r){
        JPanel panneauNbJoueur = new JPanel();
        panneauNbJoueur.setLayout(new GridBagLayout());
        panneauNbJoueur.setPreferredSize(new Dimension(500,500));
        JLabel nbJoueurText = new JLabel("Nombre de joueur :");
        JButton next = new JButton("Suivant");
        final CardLayout[] cards = new CardLayout[1];
        next.addActionListener(e -> {
            currentJ = this.nbJIG.getValue();
            System.out.println(currentJ);
            Game terrain = new Game(currentJ);
            Fenetre1 f = new Fenetre1(terrain);
        });
        (nbJIG = new JSlider(0, 2, 5, 2)).setMajorTickSpacing(1);
        nbJIG.setMinorTickSpacing(1);
        nbJIG.setPaintTicks(true);
        nbJIG.setPaintLabels(true);
        panneauNbJoueur.add(nbJoueurText, new GridBagConstraints());
        panneauNbJoueur.add(nbJIG, new GridBagConstraints());
        panneauNbJoueur.add(next, new GridBagConstraints());
        //System.out.println(currentJ);
        return panneauNbJoueur;
    }

    /*private JPanel terrain(){
        JPanel terrain = new JPanel();
        terrain.setLayout(new GridBagLayout());
        terrain.setPreferredSize(new Dimension(500, 500));

    }*/

    public int getCurrentJ(){
        return currentJ;
    }
    public boolean gett(){
        return t;
    }

    public menu_Joueur(/*Fenetre f, JPanel rP*/JPanel r){
        setLayout(new CardLayout());
        this.t = false;
        //currentJ = 0;
        JPanel panneauSelectionJ = selecNbJoueur(r);
        //System.out.println(currentJ);
        this.add(panneauSelectionJ);
    }
}
