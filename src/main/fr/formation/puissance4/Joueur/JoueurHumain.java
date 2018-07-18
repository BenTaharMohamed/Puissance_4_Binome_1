package fr.formation.puissance4.Joueur;

import fr.formation.puissance4.Board.Board;
import fr.formation.puissance4.Exception.ColonneRemplieException;
import fr.formation.puissance4.Piece.Jeton;
import javafx.scene.paint.Color;

import java.util.Scanner;

public class JoueurHumain extends Joueur {
    public JoueurHumain(Color color, Board board) {
        super(color, board);
    }

    public int calculerColonne(){
        System.out.println("Veuillez entrer un numero de colonne entre 1 et 6: ");
        Scanner scan = new Scanner(System.in);
        return  scan.nextInt()-1;

    }
    public int calculerLigne(int colonne) throws ColonneRemplieException {
        int ligne;
        for (ligne = -1; ligne < board.getJetons().length-1; ligne++) {
            if (!board.getJetons()[ligne+1][colonne].getColor().equals(Color.TRANSPARENT)) {

                if (ligne == -1){
                    throw new ColonneRemplieException();
                }
                return ligne;
            }
        }
        return ligne;
    }
public String calculerColor(){
        if(color.equals(Color.RED))
            return "RED";
        else{
            return "YELLOW";
        }
}


    @Override
    public String envoyer() {
        int colonne = calculerColonne();
        int ligne =0;
        try {
            ligne = calculerLigne(colonne);
        } catch (ColonneRemplieException e) {
            e.printStackTrace();
        }
        board.getJetons()[ligne][colonne].setColor(color);
return ligne +"," + colonne+","+ calculerColor();
    }

    @Override
    public void recevoir(String messageRecu) {
        String[] strings = messageRecu.split(",");
        int colonne = Integer.parseInt(strings[1]);
        int ligne = Integer.parseInt(strings[0]);
       board.getJetons()[ligne][colonne].setColor(Color.valueOf(strings[2]));
    }


}
