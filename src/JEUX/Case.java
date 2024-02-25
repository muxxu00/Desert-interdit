package JEUX;

public class Case {
    protected Terrain terrain;
    private int niveaux_sable = 0;
    private Type content;
    private int l, c;
    private boolean affiche;

    public int getL(){
        return l;
    }
    public void setL(int i){
        l = i;
    }
    public void setC(int i){
        c = i;
    }
    public void setCoord(int xb, int yb){
        this.l = xb;
        this.c = yb;
    }
    public boolean getAffiche(){
        return this.affiche;
    }
    public void Explore(){
        this.affiche = true;
    }

    public  int getC(){
        return c;
    }
    public int get_qte() {
        return this.niveaux_sable;
    }

    public Type get_content() {
        return this.content;
    }
    public Case(Terrain p, int qte, Type content, int x, int y) {
        affiche = true;
        niveaux_sable = qte;
        terrain = p;
        this.content = content;
        l = x;
        c = y;
    }



    public void addQuantité(int i) {
        niveaux_sable += i;
    }

    @Override
    public String toString() {
        return "x : "+ l + " ,y : "+ c +" ,Sable : " + niveaux_sable + " , contenu : " + content;
    }
    public JEUX.Case voisin(String direction){
        if (direction.toLowerCase()=="e"){
            if (terrain.getGrille()[l +1][c].get_content() == Type.OEIL){
                return this;
            }
            return terrain.getGrille()[l +1][c];
        }
        else if (direction.toLowerCase()=="w"){
            if (terrain.getGrille()[l -1][c].get_content() == Type.OEIL ){
                return this;
            }
            return terrain.getGrille()[l -1][c];
        }
        else if (direction.toLowerCase()=="n"){
            if (terrain.getGrille()[l][c -1].get_content() == Type.OEIL){
                return this;
            }
            return terrain.getGrille()[l][c -1];
        }
        else if (direction.toLowerCase()=="s"){
            if (terrain.getGrille()[l][c +1].get_content() == Type.OEIL){
                return this;
            }
            return terrain.getGrille()[l][c +1];
        }
        else if (direction.toLowerCase()=="nw"){
            if (terrain.getGrille()[l -1][c -1].get_content() == Type.OEIL){
                return this;
            }
            return terrain.getGrille()[l -1][c -1];
        }
        else if (direction.toLowerCase()=="ne"){
            if (terrain.getGrille()[l +1][c -1].get_content() == Type.OEIL){
                return this;
            }
            return terrain.getGrille()[l +1][c -1];
        }
        else if (direction.toLowerCase()=="se"){
            if (terrain.getGrille()[l +1][c +1].get_content() == Type.OEIL){
                return this;
            }
            return terrain.getGrille()[l +1][c +1];
        }
        else if (direction.toLowerCase()=="so"){
            if (terrain.getGrille()[l -1][c +1].get_content() == Type.OEIL){
                return this;
            }
            return terrain.getGrille()[l -1][c +1];
        }
        if (content == Type.TUNNEL){
            JEUX.Case[] t = new JEUX.Case[2];
            int i = 0;
            for (JEUX.Case[] a : this.terrain.getGrille()){
                for (JEUX.Case b : a){
                    if (b.get_content() == Type.TUNNEL && b != this){
                        t[i] = b;
                        i+=1;
                    }
                }
            }
            if (direction == "t1"){
                return t[0];
            }
            else if (direction == "t2"){
                return t[1];
            }
        }
        return this;
    }
    public boolean desemsabler(){
        if (niveaux_sable > 0){
            niveaux_sable =  niveaux_sable-1;
            return true;
        }
        return false;
    }
}


