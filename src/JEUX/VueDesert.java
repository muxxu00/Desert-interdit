package JEUX;

import javax.swing.*;
import java.awt.*;

public class VueDesert extends JComponent implements Observer {
    protected Game jeu;
    private static int TAILLE;
    private Color[] couleur = {Color.BLACK,Color.RED,Color.GREEN,Color.BLUE,Color.WHITE};
    private Fenetre1 fenetre;
    JButton bouton_avance,bouton_piece;
    public VueDesert(Game jeu, Fenetre1 fenet) {
        fenetre = fenet;
        this.jeu = jeu;
        Controller ctrl = new Controller(jeu,fenetre,1);
        Controller ctrl_piece = new Controller(jeu,fenetre,3);
        bouton_avance = new JButton("Prochain Joueur");
        bouton_piece = new JButton("Fouiller La Case");
        Dimension dim = new Dimension(TAILLE * Terrain.LONGUEUR, TAILLE * Terrain.LARGEUR);

        jeu.addObserver(this);

        this.setPreferredSize(dim);
        this.addMouseListener(ctrl);
        bouton_avance.setBounds(400,5 , 150 ,40 );
        bouton_piece.setBounds(640,5,150,40);
        this.add(bouton_avance);
        bouton_avance.addActionListener(ctrl);
        this.add(bouton_piece);
        this.addMouseListener(ctrl_piece);
        bouton_piece.addActionListener(ctrl_piece);
    }

    public void update() {
        this.repaint();
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        this.TAILLE = fenetre.scale/10;
        Dimension dim = new Dimension(TAILLE * Terrain.LONGUEUR , TAILLE * Terrain.LARGEUR );
        this.setPreferredSize(dim);


        g2.setPaint(Color.BLACK);
        if (jeu.nb_piece == 4 && jeu.gagner()){
            g2.setFont(new Font("Arial",Font.PLAIN,40));
            g2.setPaint(Color.GREEN);
            g2.drawString("Bravo pour votre partie vous êtes sain et sauf", 50/100 * fenetre.scale,45* fenetre.scale/100);
            bouton_avance.setVisible(false);
            bouton_piece.setVisible(false);
            fenetre.paintComponent(g2);
        }
        else if (this.jeu.testEnd()){
            g2.setFont(new Font("Arial",Font.PLAIN,40));
            g2.setPaint(Color.BLACK);
            g2.drawString("Dommage essayer une autre fois",50/100 * fenetre.scale,45* fenetre.scale/100);
            bouton_avance.setVisible(false);
            bouton_piece.setVisible(false);
            fenetre.paintComponent(g2);
        }
        else {
            g2.setFont(new Font("Arial",Font.PLAIN,12));
            g2.drawString("Pièce en votre posséssion : ", 1000,250);
            for (int i =0;i<4;i++){
                g2.drawString(jeu.getPiece()[i],1000,270+20*i);
            }
            g2.setFont(new Font("Arial",Font.PLAIN,9));
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (i != jeu.getTerrain().getOeil()[0] || j != jeu.getTerrain().getOeil()[1]) {
                        if (this.jeu.getJoueur(jeu.getJoueur_actuelle()).getNb_action()==0){
                            g2.setPaint(new Color(255, 127, 0));
                        }
                        else if (this.jeu.getJoueur(jeu.getJoueur_actuelle()).getCase_actuelle()==jeu.getTerrain().getGrille()[i][j] &&
                                jeu.getTerrain().getGrille()[i][j].get_qte()==1){
                            g2.setPaint(Color.CYAN);
                        }
                        else if (this.jeu.getJoueur(jeu.getJoueur_actuelle()).Atteignable(jeu.getTerrain().getGrille()[i][j])){
                            if (jeu.getTerrain().getGrille()[i][j].get_qte() == 0){
                                g2.setPaint(new Color(52, 201, 36));
                            }else if (jeu.getTerrain().getGrille()[i][j].get_qte() == 1){
                                g2.setPaint(Color.RED);
                            }else{
                                g2.setPaint(Color.CYAN);
                            }
                        }else{
                            g2.setPaint(new Color(255, 127, 0));
                        }
                        g2.fillRect((i) * TAILLE + 1 * fenetre.scale/4, (j) * TAILLE + 1* fenetre.scale/4 , TAILLE, TAILLE);
                        g2.setPaint(Color.BLACK);
                        g2.drawRect((i) * TAILLE + 1 * fenetre.scale/4, (j) * TAILLE + 1* fenetre.scale/4 , TAILLE, TAILLE);
                        g2.drawString("Couche de Sable : " + Integer.toString(jeu.getTerrain().getGrille()[i][j].get_qte()), (i) * TAILLE + 275* fenetre.scale/1000 , (j) * TAILLE + 275* fenetre.scale/1000 );
                        if (jeu.getTerrain().getGrille()[i][j].get_content() == Type.OASIS || jeu.getTerrain().getGrille()[i][j].get_content() == Type.OASIS_FAKE){
                            g2.setPaint(Color.BLUE);
                            g2.fillOval((i) * TAILLE + 275* fenetre.scale/1000 , (j) * TAILLE + 275* fenetre.scale/1000,25,25);
                            g2.setPaint(Color.BLACK);
                        }
                        if (jeu.getTerrain().getGrille()[i][j].getAffiche()){
                            g2.drawString(jeu.getTerrain().getGrille()[i][j].get_content().toString(), (i) * TAILLE + 265* fenetre.scale/1000 , +(j) * TAILLE + 315* fenetre.scale/1000 );
                            if (jeu.getTerrain().getGrille()[i][j].get_content() == Type.INDICE){
                                Indice test = (Indice) jeu.getTerrain().getGrille()[i][j];
                                g2.drawString(test.Affiche_Indice(),(i)*TAILLE+255*fenetre.scale/1000
                                        ,(j)*TAILLE+345*fenetre.scale/1000);
                            }
                        }
                    }
                }
            }
            g2.setFont(new Font("Arial",Font.PLAIN,20));
            g2.drawString("Ensablement Total !!!!   " + Integer.toString(jeu.getTerrain().getTotal()), fenetre.scale/3 , 200);
            g2.drawString("l'ouragan a une force de :   " + Double.toString(jeu.getTerrain().getTempslvl()), fenetre.scale/3 , 250 );


            g2.setFont(new Font("Arial",Font.PLAIN,12));
            g2.setPaint(Color.BLACK);
            g2.drawRect(50* fenetre.scale/1000 ,350* fenetre.scale/1000 , 150* fenetre.scale/1000 ,250* fenetre.scale/1000);
            g2.drawString("déshydratation des Joueur :",70* fenetre.scale/1000 ,370* fenetre.scale/1000);
            Joueur[] joueur = jeu.getJoueurs();
            for(int i=0;i<joueur.length;i++){
                g2.setPaint(couleur[i]);
                g2.fillRect(70* fenetre.scale/1000 ,400* fenetre.scale/1000  + 30* fenetre.scale/1000 *i, 20* fenetre.scale/1000,20* fenetre.scale/1000 );
                g2.setPaint(Color.BLACK);
                g2.drawString(Integer.toString(joueur[i].getEau()),100* fenetre.scale/1000 , 400* fenetre.scale/1000 +30* fenetre.scale/1000 *i+10* fenetre.scale/1000 );
            }

            g2.setPaint(couleur[jeu.getJoueur_actuelle()]);
            g2.drawRect(800* fenetre.scale/1000 ,350* fenetre.scale/1000 ,150* fenetre.scale/1000 , 250* fenetre.scale/1000);
            g2.drawString("Info sur le tour actuelle : ",810* fenetre.scale/1000 ,400* fenetre.scale/1000);
            g2.drawString("Action restantes : " + jeu.getJoueur(jeu.getJoueur_actuelle()).getNb_action(),810* fenetre.scale/1000 ,450* fenetre.scale/1000 );
            g2.fillRect(810* fenetre.scale/1000 ,480* fenetre.scale/1000 ,100* fenetre.scale/1000 , 100* fenetre.scale/1000 );




            g2.setPaint(new Color(159, 43, 104));
            g2.drawRoundRect(50* fenetre.scale/1000, 30* fenetre.scale/1000, 150* fenetre.scale/1000, 250* fenetre.scale/1000, 20, 20);
            g2.drawString("Pioche ",110* fenetre.scale/1000 ,50* fenetre.scale/1000);
            g2.drawString("D'équipement !!!!",90* fenetre.scale/1000 ,60* fenetre.scale/1000);
            g2.drawString("Attention",107* fenetre.scale/1000 ,70* fenetre.scale/1000);
            g2.drawString("aux Surprise.",98* fenetre.scale/1000 ,80* fenetre.scale/1000);
        }


    }
}

