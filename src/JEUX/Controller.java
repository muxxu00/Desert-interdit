package JEUX;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class Controller implements MouseListener,ActionListener {

    private int a = (int) (1000+(Math.random()*(10000-1000)));
    private String[] directions = {"e", "n", "w", "s"};
    private String[] actions = {"souffler", "souffler", "souffler", "souffler","dechainer", "dechainer","vague de chaleur","vague de chaleur", "souffler", "souffler", "souffler", "souffler","dechainer", "dechainer","vague de chaleur","vague de chaleur"};
    private Random ale = new Random(a);
    private Game game;
    private Fenetre1 f;
    private int type;
    public Controller(Game jeu, Fenetre1 f, int t){
        type = t;
        this.game = jeu;
        this.f = f;
        this.game.getJoueur(jeu.getJoueur_actuelle()).MajNb_action();
    }
    public void actionPerformed(ActionEvent e) {
        if (type == 1) {
            game.tour_suivant();
            game.getJoueur(game.getJoueur_actuelle()).MajNb_action();
            String act = actions[ale.nextInt(actions.length)];
            if (act == "souffler") {
                game.getTerrain().Vent((int) (0.5 * game.getTerrain().getTempslvl()), directions[ale.nextInt(4)]);
            }
            if (act == "dechainer") {
                game.Tempete_Dechaine();
            }
            if (act == "vague de chaleur") {
                game.Vague();
            }
            //pas reussi a implementer Type 2 avec les les oasis ou le decollage
        }else if (type == 2){
            Case c = game.getJoueur(game.getJoueur_actuelle()).getCase_actuelle();
            if(c.get_qte()<1 && c.getAffiche()==false && game.getJoueur(game.getJoueur_actuelle()).Faire_Action()){
                c.Explore();
                if (c.get_content()==Type.OASIS){
                    for (Joueur p : game.getJoueurs()){
                        if (c == p.getCase_actuelle()){
                            p.modifieEau(2);
                        }
                    }
                }

            }
        }else if (type == 3){
            if (game.Trouver_piece() != "rien"){
                game.getJoueur(game.getJoueur_actuelle()).Faire_Action();
            }
        }
        game.notifyObservers();

    }
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int z = f.scale;
        int x_case = 250*z/1000 + game.getJoueur(game.getJoueur_actuelle()).getPos()[0] * 100*z/1000;
        int y_case = 250*z/1000 + game.getJoueur(game.getJoueur_actuelle()).getPos()[1] * 100*z/1000;
        String deplacement = "ttt";
        Case caseactu = game.getJoueur(game.getJoueur_actuelle()).getCase_actuelle();
        if (!e.isMetaDown()) {

            if (x >= x_case - 100*z/1000 && x <= x_case && y >= y_case && y <= y_case + 100*z/1000) {
                deplacement = "w";
            } else if (x >= x_case + 100*z/1000 && x <= x_case + 200*z/1000 && y >= y_case && y <= y_case + 100*z/1000) {
                deplacement = "e";
            } else if (x >= x_case && x <= x_case + 100*z/1000 && y >= y_case - 100*z/1000 && y <= y_case) {
                deplacement = "n";
            } else if (x >= x_case && x <= x_case + 100*z/1000 && y >= y_case + 100*z/1000 && y <= y_case + 200*z/1000) {
                deplacement = "s";
            } else if (x >= x_case - 100*z/1000 && x <= x_case && y >= y_case - 100*z/1000 && y <= y_case) {
                deplacement = "nw";
            } else if (x >= x_case - 100*z/1000 && x <= x_case && y >= y_case + 100*z/1000 && y <= y_case + 200*z/1000) {
                deplacement = "sw";
            } else if (x >= x_case + 100*z/1000 && x <= x_case + 200*z/1000 && y >= y_case - 100*z/1000 && y <= y_case) {
                deplacement = "ne";
            } else if (x >= x_case + 100*z/1000 && x <= x_case + 200*z/1000 && y >= y_case + 100*z/1000 && y <= y_case + 200*z/1000) {
                deplacement = "se";
            }
            else if (caseactu.get_content() == Type.TUNNEL && caseactu.getAffiche()){
                Case tun = caseactu.voisin("t1");
                x_case = 250*z/1000 + tun.getL() * 100*z/1000;
                y_case = 250*z/1000 + tun.getC() * 100*z/1000;

                if(tun.getAffiche() && x >= x_case && x<=x_case + 100*z/1000 && y>=y_case && y <= y_case+100*z/1000){
                    deplacement = "t1";
                }else{
                    tun = caseactu.voisin("t2");
                    x_case = 250*z/1000 + tun.getL() * 100*z/1000;
                    y_case = 250*z/1000 + tun.getC() * 100*z/1000;
                    if(tun.getAffiche() && x >+ x_case && x<=x_case + 100*z/1000 && y>=y_case && y <= y_case+100*z/1000) {
                        deplacement = "t2";
                    }
                }
            }
            if (deplacement != "oui" && caseactu.voisin(deplacement).get_qte() < 2
                    && game.getJoueur(game.getJoueur_actuelle()).Faire_Action()) {
                game.getJoueur(game.getJoueur_actuelle()).deplacement(deplacement);
            }
        } else {
            if (x >= x_case - 100*z/1000 && x <= x_case && y >= y_case && y <= y_case + 100*z/1000) {
                deplacement = "w";
            } else if (x >= x_case + 100*z/1000 && x <= x_case + 200*z/1000 && y >= y_case && y <= y_case + 100*z/1000) {
                deplacement = "e";
            } else if (x >= x_case && x <= x_case + 100*z/1000 && y >= y_case - 100*z/1000 && y <= y_case) {
                deplacement = "n";
            } else if (x >= x_case && x <= x_case + 100*z/1000 && y >= y_case + 100*z/1000 && y <= y_case + 200*z/1000) {
                deplacement = "s";
            } else if (x >= x_case && x <= x_case + 100*z/1000 && y >= y_case && y <= y_case + 100*z/1000) {

                if (caseactu.get_qte() > 0 && game.getJoueur(game.getJoueur_actuelle()).getNb_action() > 0) {
                    caseactu.desemsabler();
                    game.getJoueur(game.getJoueur_actuelle()).Faire_Action();                }
            }
            if (deplacement != "oui" && caseactu.voisin(deplacement).get_qte() > 0
                    && game.getJoueur(game.getJoueur_actuelle()).getNb_action() > 0){
                caseactu.voisin(deplacement).desemsabler();
                game.getJoueur(game.getJoueur_actuelle()).Faire_Action();
            }
        }
        game.notifyObservers();
    }
    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
