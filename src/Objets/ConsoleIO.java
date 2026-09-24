package Objets;

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



}
