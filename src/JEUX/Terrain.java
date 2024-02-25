package JEUX;

import java.util.Random;

import javax.swing.*;

public class Terrain extends JComponent {
    public static final int LONGUEUR = 5, LARGEUR = 5;
    private Game jeu;
    protected Case SABLE[][] = new Case[LONGUEUR][LARGEUR];
    protected Case decollage;
    protected Case[] indice = new Case[8];
    private int Total;
    //private Case tunnel[] = new Case[3];
    protected int[] Oeil = new int[2];
    private double TempestLvl;
    public Terrain(Game jeu) {
        this.jeu = jeu;
        int a = (int) (1000+(Math.random()*(10000-1000)));
        Random al = new Random(a);
        int[] disp = new int[25];
        for (int i =0;i<25;i++){
            disp[i] = 1;
        }
        disp[12]=0;
        int[] oasis = new int[3];
        int[] tunnel = new int[3];
        int decollage = al.nextInt(0,25);
        int crash = al.nextInt(0,25);
        int[] indi = new int[8];
        oasis[0] = al.nextInt(0,25);
        oasis[1] = al.nextInt(0,25);
        oasis[2] = al.nextInt(0,25);
        while (disp[decollage] == 0){
            decollage = al.nextInt(0,25);
        }
        disp[decollage] = 0;
        while (disp[crash] == 0){
            crash = al.nextInt(0,25);
        }
        disp[crash] = 0;
        while (disp[oasis[0]] == 0 ){
            oasis[0] = al.nextInt(0,25);
        }
        disp[oasis[0]] = 0;
        while (disp[oasis[1]] == 0 ){
            oasis[1] = al.nextInt(0,25);
        }
        disp[oasis[1]] = 0;
        while (disp[oasis[2]] == 0 ){
            oasis[2] = al.nextInt(0,25);
        }
        disp[oasis[2]] = 0;
        while (disp[tunnel[0]] == 0 ){
            tunnel[0] = al.nextInt(0,25);
        }
        disp[tunnel[0]] = 0;
        while (disp[tunnel[1]] == 0 ){
            tunnel[1] = al.nextInt(0,25);
        }
        disp[tunnel[1]] = 0;
        while (disp[tunnel[2]] == 0 ){
            tunnel[2] = al.nextInt(0,25);
        }
        disp[tunnel[2]] = 0;
        for(int i =0;i<8;i++) {
            indi[i] = al.nextInt(0, 25);
            while (disp[indi[i]] == 0) {
                indi[i] = al.nextInt(0, 25);
            }
            disp[indi[i]] = 0;
        }
        int qte;
        this.OeilPos(2, 2);
        for (int i = 0; i < LARGEUR; i++) {
            for (int j = 0; j < LONGUEUR; j++) {
                if (i != this.Oeil[0] || j != this.Oeil[1]) {
                    if (i == 0 && j == 2 || i == 1 && j == 1 || i == 1 && j == 3 || i == 2 && j == 0 || i == 2 && j == 4 || i == 3 && j == 1 || i == 3 && j == 3 || i == 4 && j == 2) {
                        qte = 1;
                    } else {
                        qte = 0;

                    }
                    if (j == oasis[0]%5 && i == oasis[0]/5){
                        this.SABLE[i][j] = new Case(this,qte, Type.OASIS,i,j);
                    }else if (j == oasis[1]%5 && i == oasis[1]/5){
                        this.SABLE[i][j] = new Case(this,qte, Type.OASIS,i,j);
                    }else if (j == oasis[2]%5 && i == oasis[2]/5){
                        this.SABLE[i][j] = new Case(this,qte, Type.OASIS_FAKE,i,j);
                    }else if (j == tunnel[0]%5 && i == tunnel[0]/5){
                        this.SABLE[i][j] = new Case(this,qte,Type.TUNNEL,i,j);
                        //this.tunnel[0] = this.SABLE[i][j];
                    }else if (j==tunnel[1]%5 && i==tunnel[1]/5){
                        this.SABLE[i][j] = new Case(this,qte,Type.TUNNEL,i,j);
                        //this.tunnel[1] = this.SABLE[i][j];
                    }else if (j==tunnel[2]%5 && i == tunnel[2]/5) {
                        this.SABLE[i][j] = new Case(this, qte, Type.TUNNEL, i, j);
                        //this.tunnel[2] = this.SABLE[i][j];
                    }else if (j==crash%5 && i == crash/5) {
                        this.SABLE[i][j] = new Case(this,qte,Type.CRASH,i,j);

                    }else if (j==decollage%5 && i == decollage/5){
                        this.SABLE[i][j] = new Case(this,qte,Type.DECOLLAGE,i,j);
                        this.decollage = this.SABLE[i][j];

                    } else if (j==indi[0]%5 && i == indi[0]/5 ){
                        this.SABLE[i][j] = new Indice(this,qte,i,j);
                        Indice temp = (Indice) this.SABLE[i][j];
                        System.out.println(temp.getId());
                        this.indice[temp.getId()] = this.SABLE[i][j];
                    }else if (j==indi[1]%5 && i == indi[1]/5 ){
                        this.SABLE[i][j] = new Indice(this,qte,i,j);
                        Indice temp = (Indice) this.SABLE[i][j];
                        System.out.println(temp.getId());

                        this.indice[temp.getId()] = this.SABLE[i][j];
                    }else if (j==indi[2]%5 && i == indi[2]/5 ){
                        this.SABLE[i][j] = new Indice(this,qte,i,j);
                        Indice temp = (Indice) this.SABLE[i][j];
                        System.out.println(temp.getId());

                        this.indice[temp.getId()] = this.SABLE[i][j];
                    }else if (j==indi[3]%5 && i == indi[3]/5 ){
                        this.SABLE[i][j] = new Indice(this,qte,i,j);
                        Indice temp = (Indice) this.SABLE[i][j];
                        System.out.println(temp.getId());

                        this.indice[temp.getId()] = this.SABLE[i][j];
                    }else if (j==indi[4]%5 && i == indi[4]/5 ){
                        this.SABLE[i][j] = new Indice(this,qte,i,j);
                        Indice temp = (Indice) this.SABLE[i][j];
                        System.out.println(temp.getId());

                        this.indice[temp.getId()] = this.SABLE[i][j];
                    }else if (j==indi[5]%5 && i == indi[5]/5 ){
                        this.SABLE[i][j] = new Indice(this,qte,i,j);
                        Indice temp = (Indice) this.SABLE[i][j];
                        System.out.println(temp.getId());

                        this.indice[temp.getId()] = this.SABLE[i][j];
                    }else if (j==indi[6]%5 && i == indi[6]/5 ){
                        this.SABLE[i][j] = new Indice(this,qte,i,j);
                        Indice temp = (Indice) this.SABLE[i][j];
                        System.out.println(temp.getId());

                        this.indice[temp.getId()] = this.SABLE[i][j];
                    }else if (j==indi[7]%5 && i == indi[7]/5 ) {
                        this.SABLE[i][j] = new Indice(this, qte, i, j);
                        Indice temp = (Indice) this.SABLE[i][j];
                        System.out.println(temp.getId());

                        this.indice[temp.getId()] = this.SABLE[i][j];
                    }else{
                        this.SABLE[i][j] = new Case(this,qte,Type.CITE,i,j);

                    }
                } else {
                    this.SABLE[i][j] = new Case(this,0, Type.OEIL,i,j);
                }
            }
        }
        this.SetTotalSable();
        this.TempestLvl = 2;
    }
    public void add_Tempest(double i) {
        this.TempestLvl += i;
    }
    protected void SetTotalSable() {
        int sum = 0;
        for (Case[] i : this.SABLE) {
            for (Case j : i) {
                sum += j.get_qte();
            }
        }
        this.Total = sum;
    }
    public double getTempslvl() {
        return this.TempestLvl;
    }
    public Case[][] getGrille() {
        return SABLE;
    }
    public int getTotal() {
        return this.Total;
    }
    protected void OeilPos(int x, int y) {
        this.Oeil[0] = x;
        this.Oeil[1] = y;
    }
    public int[] getOeil(){return this.Oeil;}
    @Override
    public String toString() {
        String s = "totale Sable :" + this.getTotal() + "\n";
        s += "Tempest Level :" + this.getTempslvl() + "\n";
        for (int i = 0; i < LARGEUR; i++) {
            for (int j = 0; j < LONGUEUR; j++) {
                if (this.Oeil[0] == i && this.Oeil[1] == j) {
                    s += " Oeil , ";
                }
                s += this.SABLE[i][j] + " , ";
            }
            s += "\n";
        }
        return s;
    }
    public void Vent(int f, String d) {
        if (d.toLowerCase() == "n") {
            Case tmp;
            int x,y;
            for (int i = 0; i < f; i++) {

                if (Oeil[1] - 1 >= 0) {
                    tmp = SABLE[Oeil[0]][Oeil[1]];
                    SABLE[Oeil[0]][Oeil[1]] = SABLE[Oeil[0]][Oeil[1] - 1];
                    SABLE[Oeil[0]][Oeil[1]].addQuantité(1);
                    SABLE[Oeil[0]][Oeil[1] - 1] = tmp;
                    x = SABLE[Oeil[0]][Oeil[1]].getL();
                    y = SABLE[Oeil[0]][Oeil[1]].getC();
                    SABLE[Oeil[0]][Oeil[1]].setCoord(SABLE[Oeil[0]][Oeil[1]-1].getL(),SABLE[Oeil[0]][Oeil[1]-1].getC());
                    SABLE[Oeil[0]][Oeil[1]-1].setCoord(x,y);
                    Oeil[1] -=1;
                }

            }
        }
        if (d.toLowerCase() == "e") {
            Case tmp;
            int x,y;
            for (int i = 0; i < f; i++) {
                if (Oeil[0] + 1 < 5) {
                    tmp = SABLE[Oeil[0]][Oeil[1]];
                    SABLE[Oeil[0]][Oeil[1]] = SABLE[Oeil[0] + 1][Oeil[1]];
                    SABLE[Oeil[0]][Oeil[1]].addQuantité(1);
                    SABLE[Oeil[0] + 1][Oeil[1]] = tmp;
                    x = SABLE[Oeil[0]][Oeil[1]].getL();
                    y = SABLE[Oeil[0]][Oeil[1]].getC();
                    SABLE[Oeil[0]][Oeil[1]].setCoord(SABLE[Oeil[0]+1][Oeil[1]].getL(),SABLE[Oeil[0]+1][Oeil[1]].getC());
                    SABLE[Oeil[0]+1][Oeil[1]].setCoord(x,y);
                    Oeil[0] +=1;
                }

            }
        }
        if (d.toLowerCase() == "s") {
            Case tmp;
            int x,y;
            for (int i = 0; i < f; i++) {
                if (Oeil[1] + 1 < 5) {
                    tmp = SABLE[Oeil[0]][Oeil[1]];
                    SABLE[Oeil[0]][Oeil[1]] = SABLE[Oeil[0]][Oeil[1] + 1];
                    SABLE[Oeil[0]][Oeil[1]].addQuantité(1);
                    SABLE[Oeil[0]][Oeil[1] + 1] = tmp;
                    x = SABLE[Oeil[0]][Oeil[1]].getL();
                    y = SABLE[Oeil[0]][Oeil[1]].getC();
                    SABLE[Oeil[0]][Oeil[1]].setCoord(SABLE[Oeil[0]][Oeil[1]+1].getL(),SABLE[Oeil[0]][Oeil[1]+1].getC());
                    SABLE[Oeil[0]][Oeil[1]+1].setCoord(x,y);
                    Oeil[1] +=1;
                }

            }
        }
        if (d.toLowerCase() == "w") {
            for (int i = 0; i < f; i++) {
                Case tmp;
                int x,y;
                if (Oeil[0] - 1 >= 0) {
                    tmp = SABLE[Oeil[0]][Oeil[1]];
                    SABLE[Oeil[0]][Oeil[1]] = SABLE[Oeil[0] - 1][Oeil[1]];
                    SABLE[Oeil[0]][Oeil[1]].addQuantité(1);
                    SABLE[Oeil[0] - 1][Oeil[1]] = tmp;
                    x = SABLE[Oeil[0]][Oeil[1]].getL();
                    y = SABLE[Oeil[0]][Oeil[1]].getC();
                    SABLE[Oeil[0]][Oeil[1]].setCoord(SABLE[Oeil[0]-1][Oeil[1]].getL(),SABLE[Oeil[0]-1][Oeil[1]].getC());
                    SABLE[Oeil[0]-1][Oeil[1]].setCoord(x,y);
                    Oeil[0] -= 1;


                }

            }
        }
        for (int i =0;i<5;i++){
            for(int j =0;j<5;j++){
                SABLE[i][j].setL(i);
                SABLE[i][j].setC(j);
            }
        }
        SetTotalSable();
        jeu.notifyObservers();

    }

}

