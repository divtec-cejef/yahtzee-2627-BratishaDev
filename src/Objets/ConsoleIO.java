package Objets;

public class ConsoleIO {

    public void displayDice(int[] diceHand) {
        for (int index = 0; index < diceHand.length; index++) {
            System.out.println("Des numero " + (index + 1) + ": " + diceHand[index]);
        }
        System.out.println();
    }



}
