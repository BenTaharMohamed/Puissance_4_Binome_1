package fr.formation.puissance4.Joueur

import fr.formation.puissance4.Board.Board
import javafx.geometry.Pos
import javafx.scene.paint.Color

import java.util.ArrayList

class AI(color: Color, board: Board) : Joueur(color, board) {
    private val myPositions: MutableList<Position>
    private val enemyPositions: MutableList<Position>
    internal var myColor: String

    private val colorMe: String
        get() {
            val color: String
            if (Color.RED == this.color)
                color = "RED"
            else
                color = "YELLOW"
            return color
        }

    init {
        this.myPositions = ArrayList()
        this.enemyPositions = ArrayList()
        myColor = colorMe
    }

    inner class Position internal constructor(val y: Int, val x: Int)

    override fun envoyer(): String? {
        myPositions.clear()
        enemyPositions.clear()
        val ligneMax = ligneTestMax()
        val pointDanger = dangerPoints(ligneMax)
        val pointPositif = positifPoints(ligneMax)
        return null
    }

    private fun checkMethod(positif: Int, danger: Int): Position {
        return if (positif > danger || positif == danger) {
            makeNextStep(myPositions)
        } else {
            makeNextStep(enemyPositions)
        }
    }

    private fun makeNextStep(positions: List<Position>): Position {
        var actualLigne = -1
        var row = 0
        var maxRow = 0
        var maxRowLigne = 0
        for (position in positions) {
            if (actualLigne == -1) actualLigne = position.y
            if (row > maxRow) {
                maxRow = row
                maxRowLigne = actualLigne
            }
            if (position.y == actualLigne)
                row++
            else
                row = 0
        }
    }

    private fun ligneTestMax(): Int {
        for (i in board.jetons.size - 1 downTo 0) {
            var ligne = 0
            var lastLine = true
            for (j in 0..6) {
                if (board.jetons[i][j].color !== Color.TRANSPARENT) {
                    ligne = i
                    lastLine = false
                    break
                }
            }
            if (!lastLine) return ligne
        }
        return 0
    }

    private fun dangerPoints(ligneMax: Int): Int {
        var max = 0
        var maxToCheck = 0
        for (i in board.jetons.size - 1 downTo ligneMax) {
            for (j in 0..6) {
                val colorShort = board.jetons[i][j].color
                if (maxToCheck > max) max = maxToCheck
                if (colorShort !== Color.valueOf(myColor) && colorShort !== Color.TRANSPARENT) {
                    maxToCheck++
                }
                if (maxToCheck >= 2) {
                    if (!enemyPositions.contains(Position(i, j - 1))) enemyPositions.add(Position(i, j - 1))
                    enemyPositions.add(Position(i, j))
                } else {
                    maxToCheck = 0
                }
            }
        }
        return max
    }

    private fun positifPoints(ligneMax: Int): Int {
        var max = 0
        var maxToCheck = 0
        for (i in board.jetons.size - 1 downTo ligneMax) {
            for (j in 0..6) {
                val colorShort = board.jetons[i][j].color
                if (maxToCheck > max) max = maxToCheck
                if (colorShort === Color.valueOf(myColor)) {
                    maxToCheck++
                }
                if (maxToCheck >= 2) {
                    if (!myPositions.contains(Position(i, j - 1))) myPositions.add(Position(i, j - 1))
                    myPositions.add(Position(i, j))
                } else {
                    maxToCheck = 0
                }
            }
        }
        return max
    }


    override fun recevoir(messageRecu: String) {

    }
}
