package JEUX;

public class Joueur extends Observable{
    private Case case_actuelle;
    private int nb_action;
    private final int num_joueur;
    private int eau;
    public Joueur(Case c, int i) {
        this.case_actuelle = c;
        this.nb_action = 4;
        this.num_joueur = i;
        this.eau = 5;
    }
    public int getNb_action(){
        return this.nb_action;
    }
    public int getEau(){
        return eau;
    }
    public void modifieEau(int i){
        eau +=i;

    }
    public Case getCase_actuelle(){
        return this.case_actuelle;
    }
    public int getNum_joueur(){
        return this.num_joueur;
    }
    public void MajNb_action(){
        this.nb_action = 4;
    }
    public boolean Faire_Action(){
        if (nb_action > 0){
            this.nb_action -=1;
            return true;

        }
        return false;
    }
    public int[] getPos() {
        int[] Pos = new int[2];
        Pos[0] = case_actuelle.getL();
        Pos[1] = case_actuelle.getC();
        return Pos;
    }
    public void deplacement(String direction) {

        if (direction.toLowerCase() == "w") {
            if (case_actuelle.getL() != 0 && case_actuelle.voisin(direction).get_qte() < 2) {
                case_actuelle = case_actuelle.voisin(direction);
            }
        } else if (direction.toLowerCase() == "e" && case_actuelle.voisin(direction).get_qte() < 2) {
            if (case_actuelle.getL() != 4) {
                this.case_actuelle = case_actuelle.voisin(direction);
            }
        } else if (direction.toLowerCase() == "n" && case_actuelle.voisin(direction).get_qte() < 2) {
            if (case_actuelle.getC() != 0) {
                case_actuelle = case_actuelle.voisin(direction);
            }
        } else if (direction.toLowerCase() == "s" && case_actuelle.voisin(direction).get_qte() < 2) {
            if (case_actuelle.getC() != 4) {
                case_actuelle  = case_actuelle.voisin(direction);
            }
        }else if (direction == "t1"){
            case_actuelle = case_actuelle.voisin(direction);
        }else if (direction == "t2"){
            case_actuelle=case_actuelle.voisin(direction);
        }
        notifyObservers();

    }
    public boolean Atteignable(Case c) {
        if(c == this.case_actuelle){return false;}
        if(this.case_actuelle.getAffiche() && this.case_actuelle.get_content() == Type.TUNNEL && c.getAffiche() && c.get_content() == Type.TUNNEL){
            return true;
        }else if((c.getL() == this.case_actuelle.getL() - 1 || c.getL() == this.case_actuelle.getL() + 1) && c.getC() == this.case_actuelle.getC()){
            return true;
        }else if((c.getC() == this.case_actuelle.getC() - 1 || c.getC() == this.case_actuelle.getC() + 1) && c.getL() == this.case_actuelle.getL()){
            return true;
        }
        return false;
    }

}

