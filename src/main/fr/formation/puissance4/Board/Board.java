package fr.formation.puissance4.Board;

import fr.formation.puissance4.Piece.Jeton;

public class Board {
    private Jeton[][] jetons;

    public Board(Jeton[][] jetons) {
        this.jetons = jetons;
    }

    public Jeton[][] getJetons() {
        return jetons;
    }

    public int GrilleVide[][] = new int [6][7];

    public void remplirGrille(){

    }
    public void affichageGrile(){

    }
}
