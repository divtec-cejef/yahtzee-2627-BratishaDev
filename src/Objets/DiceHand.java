package Objets;

/**
 * Classe qui représente et gère une main de dés
 */
public class DiceHand {

    private final int NUMBER_OF_DICE = 5;
    private int[] dice = new int[NUMBER_OF_DICE];


    int[] rollDice(){
        for (int index = 0; index < dice.length; index++) {
            dice[index] = roll();
        }
        return dice;
    }
}
