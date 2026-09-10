package Objets;

/**
 * Classe qui représente et gère une main de dés
 */
public class DiceHand {

    private final int NUMBER_OF_DICE = 5;
    private Die[] dice = new Die[NUMBER_OF_DICE];

    void rollDice(){
        for (Die die : dice) {
            die.roll();
        }
    }



}
