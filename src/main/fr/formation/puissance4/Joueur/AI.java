package fr.formation.puissance4.Joueur;

import puissance4.Board.Board;
import javafx.scene.paint.Color;

public class AI extends Joueur{
    public AI(Color color, Board board) {
        super(color, board);
    }

    @Override
    public String envoyer() {
        int pointDanger=0;
        int pointPositif=0;
//        Horisontal test
        for(int i=0;i<6;i++){
            for(int j=0;j<7;j++){
                if(board.getJetons()[i][j].equals(color)){
                    pointPositif++;
                    pointDanger=0;
                }else if(board.getJetons()[i][j]==null){

                }else{
                    pointDanger++;
                    pointPositif=0;
                }
            }
        }
        if(pointDanger>=3){

        }
        return null;
    }

    @Override
    public void recevoir(String messageRecu) {

    }
}
