package fr.formation.puissance4.Joueur;

import fr.formation.puissance4.Board.Board;
import javafx.scene.paint.Color;

import java.util.Scanner;

public class JoueurHumain extends Joueur{
        public JoueurHumain(Color color, Board board) {
            super(color, board);
        }

    @Override
    public String envoyer() {
        Scanner scan=new Scanner(System.in);
        System.out.println("Saisir la colonne : (1-6)");
        int colonne=scan.nextInt();
        for(int i=0;i<6;i++){
            if(board.getJetons()[i][colonne-1]==null){
                board.getJetons()[i][colonne-1].setColor(color);
                break;
            }//faire une protection pour les entrees claviers fausses
        }
        if (Color.RED.equals(color))
            return colonne+",RED";
        else
            return colonne+",YELLOW";
    }

    @Override
    public void recevoir(String messageRecu) {
        String [] strings = messageRecu.split(",");
        int colonne = Integer.parseInt(strings[0]);
//        board.getJetons()[ligne][colonne].setColor(Color.valueOf(strings[2]));
    }


}
