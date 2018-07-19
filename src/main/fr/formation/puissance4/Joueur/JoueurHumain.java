package fr.formation.puissance4.Joueur;

import fr.formation.puissance4.Board.Board;
import fr.formation.puissance4.Exception.ColonneRemplieException;
import fr.formation.puissance4.Piece.Jeton;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class JoueurHumain extends Joueur {
    private boolean gameDone;

    public JoueurHumain(Color color, Board board) {
        super(color, board);
        this.gameDone = false;
    }

    @Override
    public String envoyer() {
        if (gameDone) return "Fin";
        else {
            String myColor = getColorMe();
            Scanner scan = new Scanner(System.in);
            System.out.println("Saisir la colonne : (1-7)");
            int colonne = scan.nextInt();
            int ligne = 0;
            boolean hasPlace = false;
            while (colonne < 1 || colonne > 7 && !hasPlace) {
                System.out.println("Faut saisir un nombre entre 1 et 6 (1,2,3,4,5,6,7)");
                colonne = scan.nextInt();
                for (int i = 5; i >= 0; i--) {
                    if (board.getJetons()[i][colonne - 1].getColor() == Color.TRANSPARENT) {
                        hasPlace = true;
                        break;
                    }
                }
            }
            colonne--;
            for (int i = 5; i >= 0; i--) {
                if (board.getJetons()[i][colonne].getColor() == Color.TRANSPARENT) {
                    board.getJetons()[i][colonne].setColor(Color.valueOf(myColor));
                    ligne = i;
                    System.out.println("Position de votre Jeton est :\nX : " + colonne + "\nY : " + (i + 1));
                    break;
                }
            }
            if (testWin() || testLose()) gameDone = true;
            return ligne + "," + colonne + "," + myColor;

        }
    }

    @Override
    public void recevoir(String messageRecu) {
        String[] strings = messageRecu.split(",");
        if (strings.length == 1 && strings[0].equals("Fin")) {
            System.out.println("Vous avez perdu!");
            gameDone = true;
            return;
        }
        System.out.println("Recu Y : " + strings[0] + " , X : " + strings[1]);
        int ligne = Integer.parseInt(strings[0]);
        int colonne = Integer.parseInt(strings[1]);
        board.getJetons()[ligne][colonne].setColor(Color.valueOf(strings[2]));
        if (testWin() || testLose()) gameDone = true;
//        board.getJetons()[ligne][colonne].setColor(Color.valueOf(strings[2]));
    }

    private boolean testWin() {
        int ligneMax = ligneTestMax();
        horizontalTest(ligneMax);
        if (board.getJetons().length - ligneMax >= 4) {
            if (verticalTest(ligneMax) || diagonalTest())
                return true;
        }
        if (horizontalTest(ligneMax))
            return true;
        return false;
    }

    private boolean isWinner(Map<Integer,Integer> position) {
        System.out.println(position.size());
        if (position.size()==4) {
            for(Map.Entry<Integer,Integer> pos : position.entrySet()){
                board.getJetons()[pos.getKey()][pos.getValue()].setColor(Color.GREEN);
            }
            System.out.println("Gagné!");
            return true;
        }
        return false;
    }

    private String getColorMe() {
        String color;
        if (Color.RED.equals(this.color)) color = "RED";
        else color = "YELLOW";
        return color;
    }

    private int ligneTestMax() {
        for (int i = board.getJetons().length - 1; i >= 0; i--) {
            int ligne = 0;
            boolean lastLine = true;
            for (int j = 0; j < 7; j++) {
                if (board.getJetons()[i][j].getColor() != Color.TRANSPARENT) {
                    ligne = i;
                    lastLine = false;
                    break;
                }
            }
            if (!lastLine) return ligne;
        }
        return 0;
    }

    private boolean horizontalTest(int val) {
        String myColor = getColorMe();
        Map<Integer,Integer> me=new HashMap<>();
        for (int i = board.getJetons().length - 1; i >= val; i--) {
            me = new HashMap<>();
            for (int j = 0; j < 7; j++) {
                if (isWinner(me)) return true;
                if (board.getJetons()[i][j].getColor() == Color.valueOf(myColor)) me.put(i,j);
                else me = new HashMap<>();
            }
        }
        return false;
    }

    private boolean verticalTest(int val) {
        String myColor = getColorMe();
        Map<Integer,Integer> me=new HashMap<>();
        for (int j = 0; j < 7; j++) {
            me = new HashMap<>();
            for (int i = board.getJetons().length - 1; i < val; i--) {
                if (isWinner(me)) return true;
                if (board.getJetons()[i][j].getColor() == Color.valueOf(myColor)) me.put(i,j);
                else me = new HashMap<>();
            }
        }
        return false;
    }

    private boolean diagonalTest() {
        String myColor = getColorMe();
        for (int i = 3; i <= 5; i++) {
        Map<Integer,Integer> me=new HashMap<>();
            me = new HashMap<>();
            for (int j = 0, k = i; j <= i; j++, k--) {
                if(isWinner(me)) return true;
                if (board.getJetons()[k][j].getColor() == Color.valueOf(myColor)) me.put(k,j);
                else me = new HashMap<>();
            }
        }
        //        Diagonal test mode : / after 5(right half)
        for (int j = 1; j <= 3; j++) {
            Map<Integer,Integer> me=new HashMap<>();
            for (int i = 5, k = j; k <= 6; i--, k++) {
                if (isWinner(me)) return true;
                if (board.getJetons()[i][k].getColor() == Color.valueOf(myColor)) me.put(i,k);
                else me = new HashMap<>();
            }
        }
        //        Diagonal test mode : \ until 5(left half)
        for (int i = 2; i >= 0; i--) {
            Map<Integer,Integer> me=new HashMap<>();
            for (int j = 0, k = i; k <= 5; j++, k++) {
                if (isWinner(me)) return true;
                if (board.getJetons()[k][j].getColor() == Color.valueOf(myColor)) me.put(k,j);
                else me = new HashMap<>();
            }
        }
        //        Diagonal test mode : \ after 5(right half)
        for (int cpt = 0, j = 1; cpt < 3; cpt++, j++) {
            Map<Integer,Integer> me=new HashMap<>();
            for (int i = 0, k = j; k <= 6; i++, k++) {
                if (isWinner(me)) return true;
                if (board.getJetons()[i][k].getColor() == Color.valueOf(myColor)) me.put(i,k);
                else me = new HashMap<>();
            }
        }
        return false;
    }

    private boolean testLose() {
        String myColor = "";
        if (Color.RED.equals(color)) myColor = "RED";
        else myColor = "YELLOW";
        int enemy = 0;
//            Horisontal test
        for (int i = 0; i < 6; i++) {
            enemy = 0;
            for (int j = 0; j < 7; j++) {
                if (enemy == 4) {
                    System.out.println("Perdu!");
                    return true;
                }
                if (board.getJetons()[i][j].getColor() != Color.valueOf(myColor) && board.getJetons()[i][j].getColor() != Color.TRANSPARENT)
                    enemy++;
                else enemy = 0;
            }
        }
//            Vertical test
        for (int j = 0; j < 7; j++) {
            enemy = 0;
            for (int i = 0; i < 6; i++) {
                if (enemy == 4) {
                    System.out.println("Perdu!");
                    return true;
                }
                if (board.getJetons()[i][j].getColor() != Color.valueOf(myColor) && board.getJetons()[i][j].getColor() != Color.TRANSPARENT)
                    enemy++;
                else enemy = 0;
            }
        }
//        Diagonal test mode : / until 5(left half)
        for (int i = 3; i <= 5; i++) {
            enemy = 0;
            for (int j = 0, k = i; j <= i; j++, k--) {
                if (enemy == 4) {
                    System.out.println("Perdu!");
                    return true;
                }
                if (board.getJetons()[k][j].getColor() != Color.valueOf(myColor) && board.getJetons()[k][j].getColor() != Color.TRANSPARENT)
                    enemy++;
                else enemy = 0;
            }
        }
        //        Diagonal test mode : / after 5(right half)
        for (int j = 1; j <= 3; j++) {
            enemy = 0;
            for (int i = 5, k = j; k <= 6; i--, k++) {
                if (enemy == 4) {
                    System.out.println("Perdu!");
                    return true;
                }
                if (board.getJetons()[i][k].getColor() != Color.valueOf(myColor) && board.getJetons()[i][k].getColor() != Color.TRANSPARENT)
                    enemy++;
                else enemy = 0;
            }
        }
        //        Diagonal test mode : \ until 5(left half)
        for (int i = 2; i >= 0; i--) {
            enemy = 0;
            for (int j = 0, k = i; k <= 5; j++, k++) {
                if (enemy == 4) {
                    System.out.println("Perdu!");
                    return true;
                }
                if (board.getJetons()[k][j].getColor() != Color.valueOf(myColor) && board.getJetons()[k][j].getColor() != Color.TRANSPARENT)
                    enemy++;
                else enemy = 0;
            }
        }
        //        Diagonal test mode : \ after 5(right half)
        for (int cpt = 0, j = 1; cpt < 3; cpt++, j++) {
            enemy = 0;
            for (int i = 0, k = j; k <= 6; i++, k++) {
                if (enemy == 4) {
                    System.out.println("Perdu!");
                    return true;
                }
                if (board.getJetons()[i][k].getColor() != Color.valueOf(myColor) && board.getJetons()[i][k].getColor() != Color.TRANSPARENT)
                    enemy++;
                else enemy = 0;
            }
        }
        return false;
    }


}
