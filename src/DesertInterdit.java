import menu1.*;
import JEUX.* ;

import javax.swing.*;
import java.awt.*;

public class DesertInterdit {
    public static void main(final String[] args){
        Fenetre desertInterdit = new Fenetre("Desert Interdit", new Dimension(1000, 600));
        JPanel rP = new JPanel(new CardLayout());

        menuP m = new menuP(rP);
        rP.add(m, "menu");
        CardLayout cards = (CardLayout)rP.getLayout();
        cards.show(rP, "menu");

        JPanel rP2 = new JPanel(new CardLayout());
        menu_Joueur mcj = new menu_Joueur(rP2);
        rP.add(mcj, "menu_joueur");

        desertInterdit.ajouteElement(rP);
        desertInterdit.dessineFenetre();

        /*if(mcj.gett()){
        desertInterdit.dispose();
        Jeu terrain = new Jeu();
        Fenetre1 f = new Fenetre1(terrain);

        }*/


    }
}
