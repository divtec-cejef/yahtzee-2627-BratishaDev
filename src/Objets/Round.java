package Objets;

public class Round {
    private ConsoleIO consoleIO;
    private DiceHand diceHand;
    private int rolls = 0;
    private final int ROLLS_LIMIT = 2;

    public Round(ConsoleIO consoleIO, DiceHand diceHand) {
        this.consoleIO = consoleIO;
        this.diceHand = diceHand;
    }

    public void playRound() {
        // Premier lancement de dés
        diceHand.rollDice();
        consoleIO.displayNumberRolls(rolls);
        rolls++;
        consoleIO.displayDice(diceHand.getValuesDice());

        // Relancement optionnel de dés
        for (int roll = 0; roll < ROLLS_LIMIT; roll++) {
            int[] userChoice = consoleIO.requestReroll();
            if (userChoice.length != 0) {
                diceHand.reroll(userChoice);
            } else {
                break;
            }
            consoleIO.displayNumberRolls(rolls);
            consoleIO.displayDice(diceHand.getValuesDice());
        }
    }
}
