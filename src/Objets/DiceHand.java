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

    void reroll(Die[] dice, int[] UserChoice){
        for (int indexChoice : UserChoice) {
            dice[indexChoice].roll();
        }
    }

    int[] getValuesDice() {
        int[] values = new int[NUMBER_OF_DICE];
        for (int index = 0; index < dice.length; index++) {
            values[index] = dice[index].getVisibleSide();
        }
        return values;
    }

    /**
     * Calcule le nombre d'occurrences
     * @param dice les dés
     * @return nombre d'occurrences de chaque nombre
     */
     int[] nombreOccurrences(Die[] dice){
        int[] occurrences = new int[dice[0].getSIDE_COUNT()];
         for (Die die : dice) {
             occurrences[die.getVisibleSide() - 1]++;
         }
        return occurrences;
    }

}
