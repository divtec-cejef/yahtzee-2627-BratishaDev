package Objets;

public class Round {
    private ConsoleIO consoleIO;
    private DiceHand diceHand = new DiceHand();
    private int rolls = 0;
    private final int ROLLS_LIMIT = 2;

    public Round(ConsoleIO consoleIO) {
        this.consoleIO = consoleIO;
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
