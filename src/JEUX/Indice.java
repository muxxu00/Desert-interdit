package JEUX;

public class Indice extends Case {
    private String name;
    private String[] ensemble = {"Ligne moteur","Colonne moteur","Ligne hélice","Colonne hélice",
            "Ligne gouvernail","Colonne gouvernail","Ligne capteur","Colonne capteur"};
    private static int num = -1;
    public Indice(Terrain p, int qte, int x, int y){
        super(p,qte,Type.INDICE,x,y);
        num+=1;
        this.name = ensemble[num];

    }
    public String Affiche_Indice(){
        return name;
    }
    public int getId(){
        return num;
    }
}
