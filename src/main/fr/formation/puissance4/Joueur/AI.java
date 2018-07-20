package fr.formation.puissance4.Joueur;

import fr.formation.puissance4.Board.Board;
import javafx.scene.paint.Color;

public class AI extends Joueur{
    public AI(Color color, Board board) {
        super(color, board);
    }

    @Override
    public String envoyer() {
        int pointDanger=0;
        int pointPositif=0;
        return null;
    }

    @Override
    public void recevoir(String messageRecu) {

    }
}
