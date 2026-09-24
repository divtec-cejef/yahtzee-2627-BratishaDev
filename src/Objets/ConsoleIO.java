package Objets;

import java.util.List;

public class ConsoleIO {

    /**
     * Affiche les dés et les chiffres qui sont sortis
     * @param diceHand dés à afficher
     */
    public void displayDice(int[] diceHand) {
        for (int index = 0; index < diceHand.length; index++) {
            System.out.println("Des numero " + (index + 1) + ": " + diceHand[index]);
        }
        System.out.println();
    }

    /**
     * Affiche des occurrences
     * @param occurrances occurrences à afficher
     */
    public void occurrencesDisplay(int[] occurrances) {
        for (int index = 0; index < occurrances.length; index++) {
            System.out.println("Occurrences de la face " + (index + 1) + ": " + occurrances[index]);
        }
        System.out.println();
    }

    /**
     * Affiche les scores des combinaisons
     * @param category nom des combinaisons
     */
    static void affichageScore(DiceHand diceHand, Scorecard scorecard){
        String alignementDroite = "%2s\n";
        String alignementGauche = "%-17s";
        for (int index = 0; index < Category.values().length; index++) {
            System.out.printf(alignementGauche, (index + 1) + ") " + category.ordinal());
            System.out.printf(alignementDroite, category.Score(diceHand));
        }
        System.out.println();
    }

}
