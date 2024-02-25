package JEUX;

public class Game extends Observable{
    private Terrain terrain;
    private int nb_joueur ;
    private Joueur[] joueur ;
    private int joueur_actuelle;
    private String[] piece = {"","","",""};
    protected int nb_piece = 0;

    public Game(int nbyaquoi){
        nb_joueur = nbyaquoi;
        joueur = new Joueur[nb_joueur];
        System.out.println("lalala : " + nb_joueur);
        System.out.println("lalala : " + nbyaquoi);
        joueur_actuelle = 0;
        terrain = new Terrain(this);
        Case c = new Case(terrain,0,Type.OASIS,0,0);
        for( Case[] a: terrain.getGrille()){
            for(Case b : a){
                if (b.get_content()==Type.CRASH){
                    c = b;
                }
            }
        }
        for (int i = 0;i<nb_joueur;i++) {
            joueur[i] = new Joueur(c,i);
        }
    }
    public Terrain getTerrain(){
        return this.terrain;
    }
    public boolean testEnd(){
        if (terrain.getTempslvl() > 7 || terrain.getTotal() > 43){
            return true;
        }
        for (Joueur p : joueur){
            if (p.getEau()==0){            //faire un systeme pour que la personne a 0 meurt et que ça ne finisse pas la partie
                return true;
            }
        }
        return false;
    }
    public Joueur getJoueur(int i){
        return this.joueur[i];
    }
    public Joueur[] getJoueurs(){
        return this.joueur;
    }
    public void Tempete_Dechaine(){
        terrain.add_Tempest(0.5);
        notifyObservers();
    }
    public void Vague(){
        for (Joueur p : joueur){
            if (p.getCase_actuelle().get_content() == Type.TUNNEL && p.getCase_actuelle().getAffiche()){

            }else{
                p.modifieEau(-1);

            }
        }
    }
    public int getJoueur_actuelle(){
        return this.joueur_actuelle;
    }
    public void tour_suivant(){
        this.joueur_actuelle = (this.joueur_actuelle+1)%joueur.length;
    }
    public String Trouver_piece(){
        Case c = joueur[joueur_actuelle].getCase_actuelle();
        String b = "rien";
        if (terrain.indice[0].getAffiche() && terrain.indice[1].getAffiche() && c.getAffiche() && c.getL()== terrain.indice[1].getL() &&
                c.getC() == terrain.indice[0].getC() && piece[0]==""){
            piece[0] = "moteur";
            nb_piece+=1;
            b="moteur";
        }else if (terrain.indice[2].getAffiche() && terrain.indice[3].getAffiche() && c.getAffiche() && c.getL()== terrain.indice[3].getL() &&
                c.getC() == terrain.indice[2].getC() && piece[1] == ""){
            piece[1] = "hélice";
            nb_piece+=1;
            b="hélice";
        }else if (terrain.indice[4].getAffiche() && terrain.indice[5].getAffiche() && c.getAffiche() && c.getL()== terrain.indice[5].getL() &&
                c.getC() == terrain.indice[4].getC() && piece[2] == ""){
            piece[2] = "gouvernail";
            nb_piece+=1;
            b="gouvernail";
        }else if (terrain.indice[6].getAffiche() && terrain.indice[7].getAffiche() && c.getAffiche() && c.getL()== terrain.indice[7].getL() &&
                c.getC() == terrain.indice[6].getC() && piece[3]==""){
            piece[3] = "capteur";
            nb_piece+=1;
            b="capteur";
        }
        return b;
    }
    public String[] getPiece(){
        return piece;
    }
    public boolean gagner(){
        if (joueur[joueur_actuelle].getCase_actuelle().get_qte() != 0){
            return false;                     //pas de fonction pour desemsabler donc pas de victoire
        }
        for (Joueur j : joueur){
            if (j.getCase_actuelle().get_content()!=Type.DECOLLAGE){
                return false;
            }
        }
        return true;
    }
}

