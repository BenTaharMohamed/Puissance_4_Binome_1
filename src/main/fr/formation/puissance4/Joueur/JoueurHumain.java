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
        System.out.println("Saisir la ligne");
        int ligne=scan.nextInt();
        System.out.println("Saisir la colonne");
        int colonne=scan.nextInt();
        if (Color.RED.equals(color))
            return ligne+","+colonne+",RED";
        else
            return ligne+","+colonne+",YELLOW";
    }

    @Override
    public void recevoir(String messageRecu) {
        String [] strings = messageRecu.split(",");
        int ligne = Integer.parseInt(strings[0]);
        int colonne = Integer.parseInt(strings[1]);
        board.getJetons()[ligne][colonne].setColor(Color.valueOf(strings[2]));
    }


}
